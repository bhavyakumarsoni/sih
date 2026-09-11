/// Lifecycle of an AI suggestion for one capture.
///
/// Note there is no state in which an AI suggestion is simply "true". It stays
/// [suggested] until a human presses Confirm or Reject; nothing downstream
/// reads it as fact before that, and nothing reads it as a priority input ever.
enum AiStatus {
  /// No classification asked for — the default for every capture.
  notRequested,

  /// Waiting for network. Nothing has been sent anywhere yet.
  queued,

  /// A request is in flight.
  running,

  /// A suggestion came back and awaits the field user's judgement.
  suggested,

  /// The attempt failed. The capture is untouched and can be re-queued.
  failed,

  /// A human agreed with the suggestion.
  confirmed,

  /// A human disagreed. Kept for audit, not applied.
  rejected;

  /// An unreadable value falls back to [notRequested], so a bad record can
  /// never present itself as a human-confirmed result.
  static AiStatus parse(String? raw) {
    if (raw == null) return notRequested;
    final key = raw.toLowerCase();
    return AiStatus.values.firstWhere((s) => s.name.toLowerCase() == key, orElse: () => notRequested);
  }
}

/// Self-reported confidence band.
///
/// Three coarse buckets rather than a percentage, deliberately: a model's
/// numeric confidence reads as precision it has not earned, and this app does
/// not manufacture precision anywhere else.
enum AiCertainty {
  high('HIGH CERTAINTY'),
  medium('MEDIUM CERTAINTY'),
  low('LOW CERTAINTY');

  const AiCertainty(this.label);

  final String label;

  /// Unknown values resolve to [low] — understating confidence is the safe
  /// direction.
  static AiCertainty parse(String? raw) {
    switch (raw?.trim().toLowerCase()) {
      case 'high':
        return high;
      case 'medium':
        return medium;
      default:
        return low;
    }
  }
}
