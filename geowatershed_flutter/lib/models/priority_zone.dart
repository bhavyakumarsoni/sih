import 'package:flutter/material.dart';

/// The five map zones from the design mock's ZONES table, with its exact
/// colour values. These are the only five states a site pin can be in.
///
/// A zone is a function of two things that are actually known: the site's
/// priority score, and how far its work has genuinely progressed. A zone is
/// never derived from missing data — a site with no GIS coverage is not
/// thereby "low priority", and absence of an intervention is not evidence
/// that none is needed.
enum PriorityZone {
  red('High priority', 'act now · overdue', 'HIGH', Color(0xFFB3261E), Colors.white),
  orange('Can be delayed', 'plan next quarter', 'LATER', Color(0xFFE4671B), Colors.white),
  yellow('Work in progress', 'under construction', 'WIP', Color(0xFFF2C230), Color(0xFF3A2A02)),
  green('Work completed', 'verified evidence', 'DONE', Color(0xFF1F5C46), Colors.white),
  blue('Due for monitoring', 'revisit this week', 'MONI', Color(0xFF2C5F7A), Colors.white);

  const PriorityZone(this.label, this.sub, this.short, this.color, this.ink);

  final String label;
  final String sub;
  final String short;
  final Color color;

  /// Text colour that stays readable on [color] — yellow needs dark ink.
  final Color ink;
}
