import '../models/photo_set.dart';

/// One before/after monitoring photograph attached to an intervention.
class PhotoEvidence {
  const PhotoEvidence({
    this.id,
    required this.interventionId,
    required this.photoSet,
    this.photoPath,
    required this.date,
    required this.meta,
    required this.createdAt,
  });

  final int? id;
  final int interventionId;
  final PhotoSet photoSet;
  final String? photoPath;
  final String date;
  final String meta;
  final int createdAt;

  /// Matched pairs are what make before/after read as proof, so both sets need
  /// this many photos before verification can be submitted.
  static const minimumPerSet = 2;

  Map<String, Object?> toMap() => {
        if (id != null) 'id': id,
        'interventionId': interventionId,
        'photoSet': photoSet.name,
        'photoPath': photoPath,
        'date': date,
        'meta': meta,
        'createdAt': createdAt,
      };

  factory PhotoEvidence.fromMap(Map<String, Object?> m) => PhotoEvidence(
        id: m['id'] as int?,
        interventionId: (m['interventionId'] as num?)?.toInt() ?? 0,
        photoSet: PhotoSet.parse(m['photoSet'] as String?),
        photoPath: m['photoPath'] as String?,
        date: m['date'] as String? ?? '',
        meta: m['meta'] as String? ?? '',
        createdAt: (m['createdAt'] as num?)?.toInt() ?? 0,
      );
}
