import '../models/ai_classification.dart';
import '../models/observation_type.dart';

/// A single field capture: geo-tagged photo + observation.
///
/// The `ai*` fields sit BESIDE the capture, never folded into it. None of them
/// feed [priorityScore], and [aiStatus] stays [AiStatus.suggested] until a
/// human confirms or rejects.
class Capture {
  const Capture({
    this.id,
    required this.siteCode,
    required this.timestamp,
    required this.observationType,
    required this.description,
    this.latitude,
    this.longitude,
    this.accuracyMeters,
    this.photoPath,
    required this.priorityScore,
    this.aiStatus = AiStatus.notRequested,
    this.aiSuggestedType,
    this.aiCertainty,
    this.aiRationale,
    this.aiDecidedAt,
  });

  final int? id;
  final String siteCode;
  final int timestamp;
  final ObservationType observationType;
  final String description;

  /// Null when no GPS fix was recorded. Such a capture is not plotted at all —
  /// never at 0,0 and never at the watershed centroid, which would invent a
  /// location the app does not have.
  final double? latitude;
  final double? longitude;
  final double? accuracyMeters;
  final String? photoPath;
  final int priorityScore;

  final AiStatus aiStatus;
  final String? aiSuggestedType;
  final AiCertainty? aiCertainty;
  final String? aiRationale;
  final int? aiDecidedAt;

  bool get hasFix => latitude != null && longitude != null;

  Capture copyWith({
    int? id,
    String? observationType,
    AiStatus? aiStatus,
    String? aiSuggestedType,
    AiCertainty? aiCertainty,
    String? aiRationale,
    int? aiDecidedAt,
    ObservationType? observation,
  }) {
    return Capture(
      id: id ?? this.id,
      siteCode: siteCode,
      timestamp: timestamp,
      observationType: observation ?? this.observationType,
      description: description,
      latitude: latitude,
      longitude: longitude,
      accuracyMeters: accuracyMeters,
      photoPath: photoPath,
      priorityScore: priorityScore,
      aiStatus: aiStatus ?? this.aiStatus,
      aiSuggestedType: aiSuggestedType ?? this.aiSuggestedType,
      aiCertainty: aiCertainty ?? this.aiCertainty,
      aiRationale: aiRationale ?? this.aiRationale,
      aiDecidedAt: aiDecidedAt ?? this.aiDecidedAt,
    );
  }

  Map<String, Object?> toMap() => {
        if (id != null) 'id': id,
        'siteCode': siteCode,
        'timestamp': timestamp,
        'observationType': observationType.name,
        'description': description,
        'latitude': latitude,
        'longitude': longitude,
        'accuracyMeters': accuracyMeters,
        'photoPath': photoPath,
        'priorityScore': priorityScore,
        'aiStatus': aiStatus.name,
        'aiSuggestedType': aiSuggestedType,
        'aiCertainty': aiCertainty?.name,
        'aiRationale': aiRationale,
        'aiDecidedAt': aiDecidedAt,
      };

  factory Capture.fromMap(Map<String, Object?> m) => Capture(
        id: m['id'] as int?,
        siteCode: m['siteCode'] as String? ?? 'SITE-????',
        timestamp: m['timestamp'] as int? ?? 0,
        observationType: ObservationType.parse(m['observationType'] as String?),
        description: m['description'] as String? ?? '',
        latitude: (m['latitude'] as num?)?.toDouble(),
        longitude: (m['longitude'] as num?)?.toDouble(),
        accuracyMeters: (m['accuracyMeters'] as num?)?.toDouble(),
        photoPath: m['photoPath'] as String?,
        priorityScore: (m['priorityScore'] as num?)?.toInt() ?? 0,
        aiStatus: AiStatus.parse(m['aiStatus'] as String?),
        aiSuggestedType: m['aiSuggestedType'] as String?,
        aiCertainty:
            m['aiCertainty'] == null ? null : AiCertainty.parse(m['aiCertainty'] as String?),
        aiRationale: m['aiRationale'] as String?,
        aiDecidedAt: (m['aiDecidedAt'] as num?)?.toInt(),
      );
}
