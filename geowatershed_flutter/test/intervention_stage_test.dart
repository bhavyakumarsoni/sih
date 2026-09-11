import 'package:flutter_test/flutter_test.dart';
import 'package:geowatershed/models/intervention_stage.dart';

void main() {
  group('parse', () {
    test('reads its own stored names', () {
      expect(InterventionStage.parse('monitoring'), InterventionStage.monitoring);
    });

    test('accepts Kotlin-cased values so old records still resolve', () {
      expect(InterventionStage.parse('UnderConstruction'), InterventionStage.underConstruction);
      expect(InterventionStage.parse('Completed'), InterventionStage.completed);
    });

    test('falls back to the LEAST advanced stage, never a later one', () {
      // A corrupt record must not make work look further along than it is —
      // "completed" is what triggers verification and payment downstream.
      expect(InterventionStage.parse('garbage'), InterventionStage.proposed);
      expect(InterventionStage.parse(null), InterventionStage.proposed);
      expect(InterventionStage.parse(''), InterventionStage.proposed);
    });
  });

  group('isDone', () {
    test('monitoring counts as done — it is a post-completion check-in', () {
      // This is the bug that shipped in the Kotlin build: the Field dashboard
      // counted `>= completed` and Governance compared `== completed`, so the
      // same record showed as 1 completed on one screen and 0 on the other.
      expect(InterventionStage.monitoring.isDone, isTrue);
      expect(InterventionStage.completed.isDone, isTrue);
    });

    test('anything before completion is not done', () {
      expect(InterventionStage.proposed.isDone, isFalse);
      expect(InterventionStage.approved.isDone, isFalse);
      expect(InterventionStage.underConstruction.isDone, isFalse);
    });
  });

  test('next walks the pipeline and stops at the end', () {
    expect(InterventionStage.proposed.next, InterventionStage.approved);
    expect(InterventionStage.monitoring.next, isNull);
  });
}
