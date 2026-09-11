import 'package:flutter/material.dart';

/// GeoWatershed design tokens — deep-green and clay on a warm neutral ground.
///
/// Values are carried over verbatim from the Kotlin app's `ui/theme/Color.kt`,
/// which took them from the "GEOWATERSHED · DESIGN SYSTEM v1" handoff. They are
/// not re-picked here: the two apps must look like the same product.
class GWColors {
  const GWColors._();

  // Core tokens
  static const green900 = Color(0xFF14342A); // app bar, headers
  static const green700 = Color(0xFF1F5C46); // primary, confirm, checkmarks
  static const green100 = Color(0xFFE9EFE9); // success surface
  static const clay600 = Color(0xFFA9502B); // field action CTA
  static const clay100 = Color(0xFFF3E6D8);
  static const severityHigh = Color(0xFFA9402A);
  static const severityMed = Color(0xFFB9822B);
  static const neutralBg = Color(0xFFF4F1EA); // screen background
  static const neutralSurface = Color(0xFFFFFFFF); // cards
  static const neutralBorder = Color(0xFFD8D2C4); // hairlines, 1dp
  static const ink900 = Color(0xFF16201A); // primary text
  static const ink500 = Color(0xFF7B807A); // labels, unavailable
  static const ink300 = Color(0xFFA5A9A2);

  // Supporting tones
  static const greenTextOnDark = Color(0xFF8FBBA6);
  static const greenSubtleOnDark = Color(0xFFA8C7B7);
  static const gpsOnDot = Color(0xFF79C79B);
  static const ink700 = Color(0xFF3D4A41);
  static const ink600 = Color(0xFF5A6159);
  static const ink400 = Color(0xFF8A8F86);
  static const greenBorder = Color(0xFFBFD3C4);
  static const clayBorder = Color(0xFFE0C9B2);
  static const clayTextDark = Color(0xFF7A4423);
  static const clayTextDark2 = Color(0xFF8F4523);

  /// The unavailable-indicator outline. Never red, never a warning colour —
  /// missing data is a gap, not an error.
  static const dashedBorder = Color(0xFFB5B0A3);
  static const dashedBorderStrong = Color(0xFFC2BCAC);
  static const afterDashedBorder = Color(0xFF9FB8A6);
  static const afterSurfaceTint = Color(0xFFEDF2ED);
  static const selectedRowBg = Color(0xFFF0F4F0);
  static const mutedRowBg = Color(0xFFF1EEE5);
  static const mutedChipBg = Color(0xFFE4E0D5);
  static const progressTrack = Color(0xFFEAE5DA);
  static const dividerHairline = Color(0xFFE9E5DB);
  static const dividerHairline2 = Color(0xFFE4DFD3);
  static const dividerHairline3 = Color(0xFFDED8CB);
  static const afterDividerHairline = Color(0xFFDDE6DC);
  static const stageApproved = Color(0xFF4A7C63);

  // Camera / photo placeholder tones
  static const viewfinderDark1 = Color(0xFF242A24);
  static const beforePlaceholder1 = Color(0xFFDAD4C6);
  static const beforePlaceholder2 = Color(0xFFE4DFD3);
  static const afterPlaceholder1 = Color(0xFFCBD9CC);
  static const afterPlaceholder2 = Color(0xFFD8E2D7);
  static const afterCaption = Color(0xFF5E7A66);
}
