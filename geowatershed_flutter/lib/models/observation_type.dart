/// What a field worker reports seeing at a capture point.
///
/// This is a POINT observation. It describes what is visible at one location,
/// not a characterisation of the surrounding area — no UI copy should imply
/// otherwise.
enum ObservationType {
  soilErosion('Soil Erosion'),
  waterlogging('Waterlogging'),
  vegetationLoss('Vegetation Loss'),
  drainageProblem('Drainage Problem'),
  waterScarcity('Water Scarcity'),
  existingStructure('Existing Structure'),
  agriculturalCondition('Agricultural Condition'),
  landDegradation('Land Degradation'),
  runoff('Runoff'),
  other('Other');

  const ObservationType(this.label);

  final String label;

  /// Reads a stored value without throwing. Matching is case-insensitive so
  /// records written by the Kotlin build (`SoilErosion`) still resolve.
  static ObservationType parse(String? raw) {
    if (raw == null) return other;
    final key = raw.toLowerCase();
    return ObservationType.values.firstWhere(
      (t) => t.name.toLowerCase() == key || t.label.toLowerCase() == key,
      orElse: () => other,
    );
  }
}
