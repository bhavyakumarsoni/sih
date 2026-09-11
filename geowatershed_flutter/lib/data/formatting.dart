/// Shared display formatting for a real (or missing) GPS fix.
String formatCoords(double? latitude, double? longitude, double? accuracyMeters) {
  if (latitude == null || longitude == null) return 'GPS unavailable';
  final accuracy = accuracyMeters == null ? '' : ' · ±${accuracyMeters.round()} m';
  return '${latitude.toStringAsFixed(5)} N, ${longitude.toStringAsFixed(5)} E$accuracy';
}
