import 'package:flutter_test/flutter_test.dart';
import 'package:geowatershed/data/priority_explainer.dart';
import 'package:geowatershed/data/site_analysis_calculator.dart';
import 'package:geowatershed/models/observation_type.dart';

import '_fixtures.dart';

/// These tests exist to protect the project's core principle rather than any
/// particular screen: missing is not zero, not false, and not low-risk.
///
/// If a future refactor starts defaulting an absent GIS value, these fail.
void main() {
  group('missing is not zero', () {
    test('a field capture has no GIS indicators at all — they are null, not 0', () {
      final indicators = SiteAnalysisCalculator.indicatorsFor(capture());
      final gis = indicators.where((i) => i.name != 'Soil Erosion');

      expect(gis.every((i) => i.value == null), isTrue);
      expect(gis.any((i) => i.value == '0' || i.value == '0.0' || i.value == 'false'), isFalse);
    });

    test('only the observation the worker actually reported gets filled in', () {
      final erosion = SiteAnalysisCalculator.indicatorsFor(
        capture(type: ObservationType.soilErosion),
      ).firstWhere((i) => i.name == 'Soil Erosion');
      expect(erosion.value, 'Reported');

      final waterlogged = SiteAnalysisCalculator.indicatorsFor(
        capture(type: ObservationType.waterlogging),
      ).firstWhere((i) => i.name == 'Soil Erosion');
      expect(waterlogged.value, isNull, reason: 'the worker did not report erosion here');
    });

    test('the seeded demo record keeps its full dataset', () {
      final indicators = SiteAnalysisCalculator.indicatorsFor(
        capture(siteCode: SiteAnalysisCalculator.seedSiteCode),
      );
      expect(indicators.where((i) => i.isAvailable).length, 4);
      expect(indicators.firstWhere((i) => i.name == 'Slope').value, '14.2%');
    });
  });

  group('the explainer never treats absence as safety', () {
    test('unavailable indicators are named, not silently dropped', () {
      final c = capture();
      final explanation = PriorityExplainer.explain(c, SiteAnalysisCalculator.indicatorsFor(c));

      expect(explanation.unavailable, contains('Slope'));
      expect(explanation.unavailable, contains('Vegetation'));
      expect(explanation.caveat, isNotNull);
      expect(explanation.caveat, contains('not counted'));
    });

    test('a site with nothing measured gets a survey recommendation, not a verdict', () {
      final c = capture(type: ObservationType.waterlogging);
      final explanation = PriorityExplainer.explain(c, SiteAnalysisCalculator.indicatorsFor(c));

      expect(explanation.contributing, isEmpty);
      expect(explanation.insight.toLowerCase(), contains('field survey'));
    });

    test('no caveat when every indicator is present', () {
      final c = capture(siteCode: SiteAnalysisCalculator.seedSiteCode);
      final full = SiteAnalysisCalculator.indicatorsFor(c).where((i) => i.isAvailable).toList();
      expect(PriorityExplainer.explain(c, full).caveat, isNull);
    });
  });

  group('the explainer reads real values', () {
    test('the seeded site produces factors that raise priority', () {
      final c = capture(siteCode: SiteAnalysisCalculator.seedSiteCode, score: 78);
      final explanation = PriorityExplainer.explain(c, SiteAnalysisCalculator.indicatorsFor(c));

      final raising =
          explanation.contributing.where((f) => f.direction == FactorDirection.raises).toList();

      // 14.2% slope, 82 m to a stream, NDVI 0.21, severe erosion.
      expect(raising.length, greaterThanOrEqualTo(3));
      expect(explanation.headline, contains('HIGH'));
      expect(explanation.insight.toLowerCase(), contains('prioritise a site visit'));
    });

    test('numbers are parsed out of display strings', () {
      final slope = PriorityExplainer.explain(
        capture(siteCode: SiteAnalysisCalculator.seedSiteCode),
        SiteAnalysisCalculator.indicatorsFor(
          capture(siteCode: SiteAnalysisCalculator.seedSiteCode),
        ),
      ).contributing.firstWhere((f) => f.name == 'Slope');

      expect(slope.reading, '14.2%');
      expect(slope.direction, FactorDirection.raises);
    });
  });
}
