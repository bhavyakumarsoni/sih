import 'package:flutter/material.dart';

import '../theme/colors.dart';

/// The intervention pipeline, in order.
enum InterventionStage {
  proposed('Proposed', GWColors.ink500),
  approved('Approved', GWColors.stageApproved),
  underConstruction('Under Construction', GWColors.severityMed),
  completed('Completed', GWColors.green700),
  monitoring('Monitoring', GWColors.clay600);

  const InterventionStage(this.label, this.color);

  final String label;
  final Color color;

  InterventionStage? get next =>
      index + 1 < InterventionStage.values.length ? InterventionStage.values[index + 1] : null;

  /// Whether the physical work is finished. [monitoring] counts: it is a
  /// post-completion check-in, not work still in progress.
  ///
  /// This is deliberately ONE definition. In the Kotlin build the Field and
  /// Governance dashboards each decided this for themselves and disagreed —
  /// one showed "1 completed" while the other showed "0 completed" for the
  /// same record, at the same moment. Do not re-derive this anywhere else.
  ///
  /// Note this is NOT what per-stage pipeline counts use: those ask "how many
  /// are AT this stage", which is a different question.
  bool get isDone => index >= InterventionStage.completed.index;

  /// Reads a stored stage without throwing.
  ///
  /// An unreadable value falls back to [proposed] — the LEAST advanced stage —
  /// deliberately. A corrupt record must never make work look further along
  /// than it is, because "completed" is what triggers verification and payment
  /// downstream.
  static InterventionStage parse(String? raw) {
    if (raw == null) return proposed;
    final key = raw.toLowerCase();
    return InterventionStage.values.firstWhere(
      (s) => s.name.toLowerCase() == key,
      orElse: () => proposed,
    );
  }
}
