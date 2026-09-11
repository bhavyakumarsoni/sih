import '../models/site_indicator.dart';
import 'capture.dart';

/// Which way an indicator pushes the priority of a site.
enum FactorDirection { raises, lowers, context }

/// One indicator's contribution to a site's classification, in plain words.
class PriorityFactor {
  const PriorityFactor({
    required this.name,
    required this.reading,
    required this.effect,
    required this.direction,
  });

  final String name;

  /// The measured value, as displayed.
  final String reading;

  /// What that value means for priority.
  final String effect;
  final FactorDirection direction;
}

/// A full account of why a site scored what it scored.
///
/// [unavailable] is a first-class part of the answer, not an afterthought: a
/// site is never explained as if the data it lacks were evidence of safety.
class PriorityExplanation {
  const PriorityExplanation({
    required this.headline,
    required this.contributing,
    required this.unavailable,
    required this.insight,
    this.caveat,
  });

  final String headline;
  final List<PriorityFactor> contributing;
  final List<String> unavailable;
  final String insight;
  final String? caveat;
}

/// Rule-based explainer: turns a capture and its indicators into the reasons
/// behind its priority classification.
///
/// Deliberately rule-based and deliberately on-device. It runs with no
/// network, no backend and no model, so it cannot fail during a demo, and
/// every sentence it produces traces to a threshold in this file.
///
/// Thresholds below are conventional watershed-planning rules of thumb, not
/// values calibrated against a validated dataset — a defensible starting
/// point a domain expert should review, not settled science.
class PriorityExplainer {
  const PriorityExplainer._();

  // --- thresholds, all in one place so they are easy to argue with ---
  static const _steepSlopePercent = 15.0;
  static const _moderateSlopePercent = 8.0;
  static const _nearStreamMetres = 150.0;
  static const _farStreamMetres = 500.0;
  static const _sparseNdvi = 0.30;
  static const _healthyNdvi = 0.50;

  static PriorityExplanation explain(Capture capture, List<SiteIndicator> indicators) {
    final contributing = <PriorityFactor>[];
    final unavailable = <String>[];

    for (final indicator in indicators) {
      final value = indicator.value;
      if (value == null) {
        unavailable.add(indicator.name);
        continue;
      }
      final factor = _factorFor(indicator.name, value);
      if (factor != null) contributing.add(factor);
    }

    final raising = contributing.where((f) => f.direction == FactorDirection.raises).length;
    final score = capture.priorityScore;
    final level = score >= 70 ? 'HIGH' : (score >= 40 ? 'MEDIUM' : 'LOW');

    final String headline;
    if (contributing.isEmpty) {
      headline = 'Scored $level ($score/100) on the field observation alone — '
          'no site measurements were available to weigh.';
    } else if (raising == 0) {
      headline = 'Scored $level ($score/100). Nothing measured at this site raises its priority.';
    } else if (raising == 1) {
      headline = 'Scored $level ($score/100), driven by one measured factor.';
    } else {
      headline = 'Scored $level ($score/100), driven by $raising measured factors acting together.';
    }

    final caveat = unavailable.isEmpty
        ? null
        : '${unavailable.length} of ${indicators.length} indicators have no data '
            '(${unavailable.join(', ')}). They are excluded from this score, not counted '
            'as zero and not treated as low risk. This site could rank higher once they '
            'are available.';

    return PriorityExplanation(
      headline: headline,
      contributing: contributing,
      unavailable: unavailable,
      insight: _insightFor(raising, unavailable.length, indicators.length),
      caveat: caveat,
    );
  }

  static PriorityFactor? _factorFor(String name, String value) {
    switch (name) {
      case 'Slope':
        final slope = _firstNumber(value);
        if (slope == null) return null;
        if (slope >= _steepSlopePercent) {
          return PriorityFactor(
            name: name,
            reading: value,
            effect: 'Steep enough that runoff gains velocity before it can infiltrate.',
            direction: FactorDirection.raises,
          );
        }
        if (slope >= _moderateSlopePercent) {
          return PriorityFactor(
            name: name,
            reading: value,
            effect: 'Moderate gradient — runoff builds speed over longer slope lengths.',
            direction: FactorDirection.raises,
          );
        }
        return PriorityFactor(
          name: name,
          reading: value,
          effect: 'Gentle gradient; slope alone is not driving erosion here.',
          direction: FactorDirection.lowers,
        );

      case 'Distance to Stream':
        final metres = _firstNumber(value);
        if (metres == null) return null;
        if (metres <= _nearStreamMetres) {
          return PriorityFactor(
            name: name,
            reading: value,
            effect: 'Close to a channel, so eroded sediment reaches water with little '
                'chance to settle.',
            direction: FactorDirection.raises,
          );
        }
        if (metres >= _farStreamMetres) {
          return PriorityFactor(
            name: name,
            reading: value,
            effect: 'Far from the nearest channel; sediment has distance to drop out '
                'before reaching water.',
            direction: FactorDirection.lowers,
          );
        }
        return PriorityFactor(
          name: name,
          reading: value,
          effect: 'Mid-range from the nearest channel.',
          direction: FactorDirection.context,
        );

      case 'Vegetation':
        final ndvi = _firstNumber(value);
        if (ndvi == null) return null;
        if (ndvi < _sparseNdvi) {
          return PriorityFactor(
            name: name,
            reading: value,
            effect: 'Sparse cover — little root structure holding soil against rainfall.',
            direction: FactorDirection.raises,
          );
        }
        if (ndvi >= _healthyNdvi) {
          return PriorityFactor(
            name: name,
            reading: value,
            effect: 'Healthy cover already binding the surface.',
            direction: FactorDirection.lowers,
          );
        }
        return PriorityFactor(
          name: name,
          reading: value,
          effect: 'Partial cover; some but not full protection.',
          direction: FactorDirection.context,
        );

      case 'Soil Erosion':
        return PriorityFactor(
          name: name,
          reading: value,
          effect: value.toLowerCase() == 'severe'
              ? 'Severe erosion observed directly at this point by a field worker.'
              : 'Erosion reported at this point by a field worker.',
          direction: FactorDirection.raises,
        );

      case 'Existing Structure':
        final lower = value.toLowerCase();
        final none = lower.contains('none') || lower.contains('no ');
        return PriorityFactor(
          name: name,
          reading: value,
          effect: none
              ? 'No existing structure is protecting this site.'
              : 'A structure already exists here.',
          direction: none ? FactorDirection.raises : FactorDirection.lowers,
        );

      case 'Agricultural Land':
        return PriorityFactor(
          name: name,
          reading: value,
          effect: 'Cultivated land downstream of the problem raises what is at stake.',
          direction: FactorDirection.context,
        );

      default:
        return null;
    }
  }

  static String _insightFor(int raising, int missing, int total) {
    if (missing == total) {
      return 'No site measurements are available yet, so no structure can be responsibly '
          'recommended. A field survey is the correct next step.';
    }
    if (missing > total / 2) {
      return 'More than half this site\'s indicators are missing. Treat any recommendation '
          'as provisional and confirm on the ground before committing budget.';
    }
    if (raising >= 3) {
      return 'Several independent factors point the same way, which is the strongest signal '
          'this app can produce from measurements alone. Prioritise a site visit.';
    }
    if (raising > 0) {
      return 'There is a measured basis for acting here, but it rests on few factors. '
          'A field visit should confirm severity before a structure is chosen.';
    }
    return 'Nothing measured here raises priority. Any action should be justified by the '
        'field observation rather than by site measurements.';
  }

  /// Pulls the first number out of a display string like "14.2%", "82 m",
  /// "0.21 sparse".
  static double? _firstNumber(String text) {
    final match = RegExp(r'-?\d+(\.\d+)?').firstMatch(text);
    return match == null ? null : double.tryParse(match.group(0)!);
  }
}
