import 'package:flutter_test/flutter_test.dart';
import 'package:geowatershed/data/priority_zone_classifier.dart';
import 'package:geowatershed/models/intervention_stage.dart';
import 'package:geowatershed/models/priority_zone.dart';

import '_fixtures.dart';

void main() {
  test('no work yet, high score -> red', () {
    expect(PriorityZoneClassifier.zoneFor(capture(score: 78), []), PriorityZone.red);
  });

  test('no work yet, low score -> orange', () {
    expect(PriorityZoneClassifier.zoneFor(capture(score: 62), []), PriorityZone.orange);
  });

  test('a merely proposed intervention does not count as work under way', () {
    final zone = PriorityZoneClassifier.zoneFor(
      capture(score: 78),
      [intervention(stage: InterventionStage.proposed)],
    );
    expect(zone, PriorityZone.red);
  });

  test('approved and under construction -> yellow', () {
    for (final stage in [InterventionStage.approved, InterventionStage.underConstruction]) {
      expect(
        PriorityZoneClassifier.zoneFor(capture(score: 78), [intervention(stage: stage)]),
        PriorityZone.yellow,
      );
    }
  });

  test('completed -> green, monitoring -> blue', () {
    expect(
      PriorityZoneClassifier.zoneFor(
          capture(score: 78), [intervention(stage: InterventionStage.completed)]),
      PriorityZone.green,
    );
    expect(
      PriorityZoneClassifier.zoneFor(
          capture(score: 78), [intervention(stage: InterventionStage.monitoring)]),
      PriorityZone.blue,
    );
  });

  test('the furthest-advanced intervention at a site decides the zone', () {
    final zone = PriorityZoneClassifier.zoneFor(capture(score: 78), [
      intervention(stage: InterventionStage.proposed),
      intervention(stage: InterventionStage.completed),
      intervention(stage: InterventionStage.approved),
    ]);
    expect(zone, PriorityZone.green);
  });

  test('interventions at OTHER sites are ignored', () {
    final zone = PriorityZoneClassifier.zoneFor(
      capture(siteCode: 'SITE-201', score: 78),
      [intervention(siteCode: 'SITE-999', stage: InterventionStage.completed)],
    );
    expect(zone, PriorityZone.red, reason: 'another site being finished says nothing about this one');
  });
}
