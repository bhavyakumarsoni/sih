# GeoWatershed (Android / Jetpack Compose)

Implementation of the `GeoWatershed.dc.html` Claude Design handoff — a
field-data collection and decision-support app for watershed management
professionals and NGO field workers in rural India (Smart India Hackathon
project). See `/README.md` and `/chats/chat1.md` at the repo root for the
original design brief.

## Scope

The app is **functionally real, backend not yet connected**:

- **Camera capture** — CameraX live preview + real shutter, saving photos to
  app-private storage.
- **GPS** — a real one-shot device location fix via the Fused Location
  Provider (`data/LocationProvider.kt`), not a fake toggle.
- **Local persistence** — a Room database (`data/db/`) backs everything:
  captures, interventions, and before/after photo evidence all survive app
  restarts.
- **Every button does something real**: the Interventions filter chips
  actually filter; "Advance Status" and "Submit for Verification" persist;
  "Create Intervention" inserts a real row; "Save & Analyze" creates a real
  capture record and takes you to its analysis.
- **No backend yet** — there's no server, so GIS-derived site indicators
  (Slope, Distance to Stream, Vegetation NDVI) are honest gaps
  (UNAVAILABLE) for any newly captured site, using the app's own
  "excluded, not zero" language rather than faking precision. The
  dashboard's "queued offline" count is simply the number of local records
  that have never been synced anywhere, since there's nowhere to sync to
  yet. The one exception is the seeded demo record (`SITE-0142`, wired in
  `data/SiteAnalysisCalculator.kt`), which keeps the original design mock's
  full example dataset so the app isn't empty on first launch.

## Project structure

- `app/src/main/java/com/geowatershed/app/`
  - `MainActivity.kt` — hosts the Compose content
  - `ui/theme/` — `Color.kt`, `Type.kt`, `Theme.kt`: the color tokens, type
    scale, and Material3 theme wrapper from the handoff spec ("1f")
  - `ui/components/` — shared building blocks (stat cards, buttons, the GPS
    card, camera preview/capture, permission handling, observation radio
    list, indicator list, intervention card, photo display, etc.)
  - `ui/screens/` — the five screens: Dashboard, Capture, Site Analysis,
    Interventions, Before/After Monitoring
  - `ui/navigation/` — `Routes.kt` + `GWNavHost.kt` (Navigation-Compose;
    Site Analysis and Monitoring take a real `Long` capture/intervention id)
  - `data/` — `GeoWatershedViewModel.kt` (Room-backed state),
    `GeoWatershedRepository.kt`, `LocationProvider.kt`,
    `SiteAnalysisCalculator.kt`, `Formatting.kt`, `data/db/` (Room entities
    + DAOs + database), `data/model/` (plain enum/data classes)
- `app/src/main/res/font/` — bundled IBM Plex Sans / IBM Plex Mono TTF files
  (SIL Open Font License), matching the design's typeface

## Building

This was written and reviewed in an environment without network access to
`dl.google.com`, so the Android Gradle Plugin and AndroidX artifacts could
not be resolved and **the project has not been built or run** here. To
build it on a normal machine with the Android SDK installed:

```
./gradlew assembleDebug
```

or open the `android/` folder in Android Studio (Iguana or later — it
targets AGP 8.5.2, Kotlin 1.9.24, Compose BOM 2024.06.00, compileSdk 34,
minSdk 26). Camera capture requires a physical device or an emulator with
a virtual camera enabled; GPS requires either a real device or an emulator
with a simulated location set (Extended Controls → Location).

Since no build could be run, the source was checked by hand and with two
independent review passes (before and after adding the camera/GPS/Room
layer) for signature mismatches, unresolved references, wrong import
paths, and Compose layout-constraint issues. A few real bugs were caught
and fixed this way (a wrong `FontWeight` import path, a missing entity
property, a `fillMaxHeight()` under an unbounded constraint, a `::request`
callable-reference mistake on a lambda-typed property, and several places
where `.border()` was misused to draw a divider on all four sides instead
of one). If something still doesn't compile, it's most likely a small
import or signature slip worth a quick `./gradlew assembleDebug` + fix
cycle rather than a structural problem.

## Test checklist (first run on a device/emulator)

1. **First launch** — Dashboard should show non-zero stats (the seeded
   demo: 1 image captured, 1 high-priority site, 1 intervention proposed
   [Check Dam, stage Proposed], 0 completed) and an offline-queue banner
   showing "1 record queued offline". If Room's schema or seeding is
   broken, this screen will look empty or crash on launch — start here.
2. **Capture screen** — grants CAMERA + location permission prompts on
   entry; live camera preview should appear (or a "tap to grant" card if
   permission is denied); the GPS card should move from "ACQUIRING FIX…"
   to real coordinates within a few seconds outdoors (indoors/emulator
   without a simulated location, it will settle on "WAITING FOR FIX — TAP
   TO RETRY", which is also correct behavior — tap it to retry). Tap the
   shutter to take a real photo (a green "PHOTO CAPTURED" banner should
   appear); tap "Save & Analyze".
3. **Site Analysis** — the new capture's indicators should show mostly
   UNAVAILABLE (dashed chips) except Soil Erosion if that's what you
   selected; tapping "Create Intervention" should return you to
   Interventions with a new row.
4. **Interventions** — filter chips (ALL/PROPOSED/ACTIVE/DONE) should
   actually change which cards are shown and their counts should match;
   "Advance Status" should visibly step the pipeline and persist after
   backing out and back in; "Monitoring" opens the Before/After screen for
   that specific intervention.
5. **Before/After Monitoring** — "Add Photo" opens a full-screen camera
   overlay (✕ to cancel, tap the white circle to capture); once 2+ photos
   exist in both Before and After, "Submit for Verification" should
   enable, and tapping it should relabel to "✓ Submitted for Verification"
   and stay disabled.
6. **Restart the app** — everything from steps 2–5 should still be there
   (Room persistence).

## Design fidelity notes

- The "unavailable indicator" treatment is fixed to the mock's default,
  **dashed-chip** (neutral dash glyph, dashed outline — never red, never a
  warning icon). The mock's `muted-row` alternative and its CTA-color /
  accent "tweaks panel" were design-exploration knobs for evaluator A/B
  comparisons, not shipped product behavior, so they aren't exposed as
  runtime toggles here.
- The mock's `1f` artboard ("Jetpack Compose handoff" spec sheet) is
  reference documentation for implementing this app, not a screen inside
  the app itself, so it has no corresponding Compose screen.
