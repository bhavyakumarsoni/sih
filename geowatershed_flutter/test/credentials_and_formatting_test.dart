import 'package:flutter_test/flutter_test.dart';
import 'package:geowatershed/data/formatting.dart';
import 'package:geowatershed/data/governance_credentials.dart';

void main() {
  group('governance login', () {
    test('accepts the issued officer credentials', () {
      expect(GovernanceCredentials.authenticate('bdo.kolar', 'kolar@2026'), 'BDO Kolar');
      expect(
        GovernanceCredentials.authenticate('officer1', 'chinnahalli1'),
        'Field Officer · Chinnahalli',
      );
    });

    test('officer id is case-insensitive and trimmed; password is not', () {
      expect(GovernanceCredentials.authenticate('  BDO.Kolar ', 'kolar@2026'), 'BDO Kolar');
      expect(GovernanceCredentials.authenticate('bdo.kolar', 'Kolar@2026'), isNull);
    });

    test('rejects unknown officers and wrong passwords', () {
      expect(GovernanceCredentials.authenticate('nobody', 'kolar@2026'), isNull);
      expect(GovernanceCredentials.authenticate('bdo.kolar', ''), isNull);
    });
  });

  group('coordinate formatting', () {
    test('formats a real fix with accuracy', () {
      expect(formatCoords(13.13624, 78.13291, 4.2), '13.13624 N, 78.13291 E · ±4 m');
    });

    test('says so plainly when there is no fix', () {
      expect(formatCoords(null, null, null), 'GPS unavailable');
      expect(formatCoords(13.1, null, 4), 'GPS unavailable');
    });

    test('omits accuracy rather than inventing one', () {
      expect(formatCoords(13.13624, 78.13291, null), '13.13624 N, 78.13291 E');
    });
  });
}
