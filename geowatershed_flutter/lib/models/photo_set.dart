/// Which half of a before/after evidence pair a photo belongs to.
enum PhotoSet {
  before('PRE-WORK PHOTO'),
  after('POST-WORK PHOTO');

  const PhotoSet(this.note);

  final String note;

  static PhotoSet parse(String? raw) {
    if (raw == null) return before;
    final key = raw.toLowerCase();
    return PhotoSet.values.firstWhere((p) => p.name.toLowerCase() == key, orElse: () => before);
  }
}
