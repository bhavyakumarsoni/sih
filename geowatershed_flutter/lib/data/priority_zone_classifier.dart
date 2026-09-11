import '../models/intervention_stage.dart';
import '../models/priority_zone.dart';
import 'capture.dart';
import 'intervention.dart';

/// Assigns each captured site one of the five [PriorityZone] values.
///
/// The rule, in order of precedence:
///
///  - work at this site reached Monitoring      -> blue   (due for monitoring)
///  - work at this site is Completed            -> green  (work completed)
///  - work is Approved or Under Construction    -> yellow (work in progress)
///  - no work yet (or only Proposed), score >=70 -> red    (high priority)
///  - no work yet (or only Proposed), score <70  -> orange (can be delayed)
///
/// Sites match interventions by site code; where a site has several, the
/// furthest-advanced one decides the zone.
///
/// What this deliberately does NOT do: it never treats an unavailable
/// indicator as a low score, and it never reads "no intervention recorded" as
/// "nothing needed here". Both would be the missing-equals-zero mistake in
/// a different hat.
class PriorityZoneClassifier {
  const PriorityZoneClassifier._();

  /// Matches the HIGH threshold used by the priority level pills.
  static const highPriorityScore = 70;

  static PriorityZone zoneFor(Capture capture, List<Intervention> interventions) {
    final atSite = interventions.where((i) => i.siteCode == capture.siteCode);

    InterventionStage? furthest;
    for (final intervention in atSite) {
      if (furthest == null || intervention.stage.index > furthest.index) {
        furthest = intervention.stage;
      }
    }

    switch (furthest) {
      case InterventionStage.monitoring:
        return PriorityZone.blue;
      case InterventionStage.completed:
        return PriorityZone.green;
      case InterventionStage.underConstruction:
      case InterventionStage.approved:
        return PriorityZone.yellow;
      case InterventionStage.proposed:
      case null:
        return capture.priorityScore >= highPriorityScore
            ? PriorityZone.red
            : PriorityZone.orange;
    }
  }
}
