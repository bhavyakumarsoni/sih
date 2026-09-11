import '../models/intervention_stage.dart';

/// A proposed or in-flight watershed work item.
class Intervention {
  const Intervention({
    this.id,
    required this.type,
    required this.siteCode,
    required this.siteDetail,
    this.latitude,
    this.longitude,
    this.accuracyMeters,
    required this.stage,
    required this.createdAt,
    this.sourceCaptureId,
    this.verificationSubmitted = false,
  });

  final int? id;
  final String type;
  final String siteCode;
  final String siteDetail;
  final double? latitude;
  final double? longitude;
  final double? accuracyMeters;
  final InterventionStage stage;
  final int createdAt;
  final int? sourceCaptureId;
  final bool verificationSubmitted;

  static const stalledMs = 30 * 24 * 60 * 60 * 1000;

  /// No stage change in 30+ days and not yet finished.
  bool isStalled([int? nowMs]) {
    final now = nowMs ?? DateTime.now().millisecondsSinceEpoch;
    return !stage.isDone && now - createdAt > stalledMs;
  }

  Intervention copyWith({int? id, InterventionStage? stage, bool? verificationSubmitted}) =>
      Intervention(
        id: id ?? this.id,
        type: type,
        siteCode: siteCode,
        siteDetail: siteDetail,
        latitude: latitude,
        longitude: longitude,
        accuracyMeters: accuracyMeters,
        stage: stage ?? this.stage,
        createdAt: createdAt,
        sourceCaptureId: sourceCaptureId,
        verificationSubmitted: verificationSubmitted ?? this.verificationSubmitted,
      );

  Map<String, Object?> toMap() => {
        if (id != null) 'id': id,
        'type': type,
        'siteCode': siteCode,
        'siteDetail': siteDetail,
        'latitude': latitude,
        'longitude': longitude,
        'accuracyMeters': accuracyMeters,
        'stage': stage.name,
        'createdAt': createdAt,
        'sourceCaptureId': sourceCaptureId,
        'verificationSubmitted': verificationSubmitted ? 1 : 0,
      };

  factory Intervention.fromMap(Map<String, Object?> m) => Intervention(
        id: m['id'] as int?,
        type: m['type'] as String? ?? 'Unknown',
        siteCode: m['siteCode'] as String? ?? 'SITE-????',
        siteDetail: m['siteDetail'] as String? ?? '',
        latitude: (m['latitude'] as num?)?.toDouble(),
        longitude: (m['longitude'] as num?)?.toDouble(),
        accuracyMeters: (m['accuracyMeters'] as num?)?.toDouble(),
        stage: InterventionStage.parse(m['stage'] as String?),
        createdAt: (m['createdAt'] as num?)?.toInt() ?? 0,
        sourceCaptureId: (m['sourceCaptureId'] as num?)?.toInt(),
        verificationSubmitted: (m['verificationSubmitted'] as num?)?.toInt() == 1,
      );
}
