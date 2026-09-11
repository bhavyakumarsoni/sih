/// Governance Mode login.
///
/// There is no backend, so this checks a fixed set of officer credentials
/// issued out of band by the BDO office. There is deliberately no self-service
/// account creation anywhere in the app.
///
/// This is NOT authentication. These strings compile into the binary and can
/// be read out of it. It is an acceptable hackathon stub and it is documented
/// as one; it must move server-side before this app goes anywhere real.
class GovernanceCredentials {
  const GovernanceCredentials._();

  static const _officers = <String, (String password, String displayName)>{
    'bdo.kolar': ('kolar@2026', 'BDO Kolar'),
    'officer1': ('chinnahalli1', 'Field Officer · Chinnahalli'),
  };

  /// Returns the officer's display name on success, null on a bad ID/password.
  static String? authenticate(String officerId, String password) {
    final entry = _officers[officerId.trim().toLowerCase()];
    if (entry == null) return null;
    return entry.$1 == password ? entry.$2 : null;
  }
}
