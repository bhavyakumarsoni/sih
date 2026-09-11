/// A single site indicator row.
///
/// [value] is null when the data source has no record for this parcel — the
/// honest gap. Never a placeholder zero, never an error, and never counted as
/// if it lowered risk.
class SiteIndicator {
  const SiteIndicator({required this.name, required this.sub, this.value});

  final String name;
  final String sub;
  final String? value;

  bool get isAvailable => value != null;
}
