import '../models/observation_type.dart';
import '../models/site_indicator.dart';
import 'capture.dart';

/// Turns a [Capture] into the six site indicators shown on Site Analysis, and
/// scores it.
///
/// There is no GIS/remote-sensing backend, so Slope, Distance to Stream,
/// Vegetation, Existing Structure and Agricultural Land are honest gaps for
/// any capture taken in the field today. Only Soil Erosion can be filled in,
/// and only when that is what the field worker actually reported.
///
/// The seeded demo record is the one exception, pre-populated with the full
/// dataset from the original design mock so a cold start is never empty.
class SiteAnalysisCalculator {
  const SiteAnalysisCalculator._();

  static const seedSiteCode = 'SITE-0142';

  static List<SiteIndicator> indicatorsFor(Capture capture) {
    if (capture.siteCode == seedSiteCode) return _seedIndicators;

    final erosionReported =
        capture.observationType == ObservationType.soilErosion ? 'Reported' : null;

    return [
      const SiteIndicator(name: 'Slope', sub: 'pending GIS backend'),
      const SiteIndicator(name: 'Distance to Stream', sub: 'pending GIS backend'),
      const SiteIndicator(name: 'Vegetation', sub: 'pending remote-sensing backend'),
      SiteIndicator(name: 'Soil Erosion', sub: 'field observation', value: erosionReported),
      const SiteIndicator(name: 'Existing Structure', sub: 'pending survey backend'),
      const SiteIndicator(name: 'Agricultural Land', sub: 'pending cadastral backend'),
    ];
  }

  /// A provisional score from only the indicators available today — never a
  /// fake precise GIS score.
  static int scoreFor(ObservationType type) =>
      type == ObservationType.soilErosion ? 62 : 50;

  static const _seedIndicators = <SiteIndicator>[
    SiteIndicator(name: 'Slope', sub: 'DEM derived · 30 m', value: '14.2%'),
    SiteIndicator(name: 'Distance to Stream', sub: 'nearest seasonal channel', value: '82 m'),
    SiteIndicator(name: 'Vegetation', sub: 'NDVI · Sentinel-2, Aug', value: '0.21 sparse'),
    SiteIndicator(name: 'Soil Erosion', sub: 'field observation', value: 'Severe'),
    SiteIndicator(name: 'Existing Structure', sub: 'no survey record for this parcel'),
    SiteIndicator(name: 'Agricultural Land', sub: 'cadastral layer not published'),
  ];
}
