import 'package:geowatershed/data/capture.dart';
import 'package:geowatershed/data/intervention.dart';
import 'package:geowatershed/models/intervention_stage.dart';
import 'package:geowatershed/models/observation_type.dart';

Capture capture({
  String siteCode = 'SITE-201',
  int score = 50,
  ObservationType type = ObservationType.soilErosion,
  double? lat = 13.13624,
  double? lon = 78.13291,
}) =>
    Capture(
      id: 1,
      siteCode: siteCode,
      timestamp: 0,
      observationType: type,
      description: '',
      latitude: lat,
      longitude: lon,
      accuracyMeters: 4,
      priorityScore: score,
    );

Intervention intervention({
  String siteCode = 'SITE-201',
  required InterventionStage stage,
  int createdAt = 0,
}) =>
    Intervention(
      id: 1,
      type: 'Check Dam',
      siteCode: siteCode,
      siteDetail: '$siteCode · test',
      stage: stage,
      createdAt: createdAt,
    );
