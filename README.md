# GeoWatershed (Android / Jetpack Compose)

Smart India Hackathon 2026 entry for the Ministry of Rural Development
(Dept. of Land Resources) problem statement *"Application of Geospatial
Techniques for Visualization and Analysis to Interpret Geo-Coded Images to
Enhance Watershed Development Outcomes"*.

An offline-first Android app for watershed field data collection and
priority triage, split into two modes: **Field Mode** (no login, capture
essentials) and **Governance Mode** (officer login, map / pipeline /
approvals).

---

## Demo logins

**Field Mode needs no login.** Governance Mode does:

| Officer ID | Password | Signs in as |
|---|---|---|
| `bdo.kolar` | `kolar@2026` | BDO Kolar |
| `officer1` | `chinnahalli1` | Field Officer · Chinnahalli |

The officer ID is case-insensitive and trimmed; the password is exact-match.
There is deliberately no "create account" anywhere in the app — credentials are
issued out of band by the BDO office.

These live in `data/GovernanceCredentials.kt` and are compiled into the APK, so
writing them down here changes nothing about their exposure: anyone holding the
APK can read them with `strings` in seconds. That is a reasonable hackathon
stub and it is recorded honestly under Known gaps below. It stops being
reasonable the moment this app has a real backend — at which point these move
server-side and this section gets deleted.

---

## Build status

`./gradlew assembleDebug` → **BUILD SUCCESSFUL** (10 Sep 2026), producing a
13 MB debug APK at `app/build/outputs/apk/debug/app-debug.apk`.

Toolchain, as pinned — do not bump these casually:

| | |
|---|---|
| Android Gradle Plugin | 8.5.2 |
| Kotlin | 1.9.24 |
| Gradle wrapper | 8.9 |
| Compose BOM | 2024.06.00 |
| compileSdk / targetSdk | 34 |
| minSdk | 26 |
| Java | 17 |

The build emits exactly three warnings, all `android.preference.PreferenceManager`
deprecations in `PriorityMap.kt`. That is osmdroid's own documented
initialisation path; the class still ships in API 34 and the warnings are
cosmetic.

**What is still unverified:** the app has been compiled but not yet launched
on a device or emulator by the author of this file. Nothing below that
describes runtime *behaviour* should be read as observed — it is read off the
source. See "Unverified claims" at the end.

---

## What is real, and what is not

This distinction is a project value, not documentation style. Do not blur it.

### Real

- **Camera** — CameraX live preview with a working shutter, writing JPEGs to
  app-private storage (`filesDir/photos/`). Not a placeholder.
- **GPS** — a genuine one-shot high-accuracy fix via the Fused Location
  Provider (`data/LocationProvider.kt`). Not a fake toggle.
- **Local persistence** — a real Room database (`data/db/`, schema v3) holding
  captures, interventions and before/after photo evidence. Survives restarts.
- **Map** — a live OpenStreetMap view via osmdroid (no API key, no billing),
  with one colour-coded pin per geo-tagged capture. On the Field dashboard,
  on the Governance dashboard, and on a dedicated filterable Priority Map
  screen reachable from both.
- **Five-zone classification** — every plotted site is Red (high priority),
  Orange (can be delayed), Yellow (work in progress), Green (completed) or
  Blue (due for monitoring), derived in `data/PriorityZoneClassifier.kt` from
  the site's score and how far its work has actually progressed.
- **Site-level reasoning** — `data/PriorityExplainer.kt` says which
  indicators pushed a site's priority and which way, names the ones it could
  not weigh, and states plainly that they were excluded rather than zeroed.
- **Persistent mode + storage indicator** — a strip under every screen
  showing FIELD MODE / GOVERNANCE MODE and how many records are on the
  device, driven by the live nav route.
- **Governance login** — checked against a fixed credential map in
  `data/GovernanceCredentials.kt`. There is deliberately no self-service
  signup anywhere in the app.
- **Interaction wiring** — filter chips filter, "Advance Status" and "Submit
  for Verification" write to Room, "Create Intervention" inserts a real row,
  "Save & Analyze" creates a real capture and navigates to its analysis.

### Not real

- **There is no backend of any kind.** No server, no sync, no network calls.
- **There is no GIS or remote-sensing data source.** For any capture taken
  today, Slope, Distance to Stream, Vegetation, Existing Structure and
  Agricultural Land all render as `UNAVAILABLE`. They are not estimated, not
  defaulted, and not counted toward the score.
- **The intervention recommendation is rule-based, not AI.** It is a
  two-branch rule in `recommendedInterventionFor()`, and the screen says so.
- **The priority score is not a GIS score.** `SiteAnalysisCalculator.scoreFor()`
  returns 62 if the field worker selected Soil Erosion and 50 otherwise. That
  is the whole rule.
- **The "queued offline" count is not a sync queue.** It is the number of
  local records, because there is nowhere to sync to.

### The one seeded exception

`SITE-0142` is a demo record inserted on first launch by
`GeoWatershedRepository.seedIfEmpty()`, at **13.13624 N, 78.13291 E**
(Chinnahalli, Kolar Taluk). It carries the original design mock's full
example dataset — slope 14.2%, 82 m to stream, NDVI 0.21, severe erosion,
score 78 — so the app is not empty on first launch. It ships alongside two
further seeded interventions (`SITE-0117` Farm Pond, `SITE-0093` Contour
Trench) and four seeded photo-evidence rows.

**Do not remove the seed.** It is what makes a cold-start demo reliable.

---

### Experimental: AI assist

Off by default and opt-in. A captured photo can be sent to a vision model to
**suggest** one of the app's existing observation categories, with a coarse
High/Medium/Low certainty band rather than a fabricated percentage.

Its boundaries are structural, not advisory:

- It never writes `priorityScore`, and never creates or advances an
  intervention.
- A suggestion sits at `Suggested` until a person presses Confirm or Reject.
  Confirm updates the observation type *by the person's decision*; Reject
  keeps the suggestion for audit and applies nothing.
- The API key is entered on-device (`AiSettingsScreen`) and lives only in
  this app's private preferences. It is **not** in this repository and
  **not** compiled into the APK.
- Offline, captures queue on the device and nothing is transmitted. The
  queue count and its state are visible on the settings screen.
- It adds no new Gradle dependency — `HttpURLConnection` and `org.json`,
  both already in the platform.

## Missing is not zero

The core honesty rule, enforced throughout:

- A GIS-derived field with no data renders as an explicit `UNAVAILABLE`
  state — the **dashed-chip** treatment: neutral dash glyph, dashed outline,
  never red, never a warning icon.
- Unavailable indicators are *excluded* from the score, never counted as
  zero, false, or low-risk. The Data Completeness card says so in as many
  words.
- There is exactly one unavailable-indicator visual treatment. The design
  mock's `muted-row` alternative and its CTA/accent "tweaks panel" were
  evaluator A/B knobs, not shipped behaviour, and are not exposed at runtime.
- A single geo-tagged photo characterises a point, not the area around it.
  UI copy must not imply otherwise.

---

## Project structure

Everything under `app/src/main/java/com/geowatershed/app/`:

```
MainActivity.kt              Hosts the Compose content; that is all it does.

data/
  GeoWatershedViewModel.kt   AndroidViewModel; Room-backed state, governance
                             session, capture-form state, reverse geocoding.
  GeoWatershedRepository.kt  Single source of truth over the DAOs; seeding.
  LocationProvider.kt        Fused Location Provider wrapper (one-shot fix).
  SiteAnalysisCalculator.kt  Indicators + score. Honest nulls, seed exception.
  GovernanceCredentials.kt   Fixed officer credential map.
  Formatting.kt              Shared coordinate formatting.
  db/                        CaptureEntity/Dao, InterventionEntity/Dao,
                             PhotoEvidenceEntity/Dao, GeoWatershedDatabase.
  model/                     ObservationType, InterventionStage, PhotoSet,
                             SiteIndicator.

ui/
  navigation/                Routes.kt + GWNavHost.kt (Navigation-Compose).
  theme/                     Color.kt, Type.kt, Theme.kt — design tokens from
                             the "GEOWATERSHED · DESIGN SYSTEM v1" handoff.
  components/                16 shared building blocks, including
                             PriorityMap.kt (osmdroid), SiteAnalysisComponents.kt,
                             InterventionCard.kt, CameraCapture/CameraPreview,
                             GpsCard, OfflineBanner, Permissions, StatCard.
  screens/                   Twelve screens — see below.
```

`app/src/main/res/font/` bundles IBM Plex Sans / IBM Plex Mono (SIL OFL),
matching the design's typeface.

### The ten screens

Entry point is `EntryScreen`, not the Dashboard.

**Field Mode** (no login):

| Screen | Route |
|---|---|
| `EntryScreen` — attention summary + mode choice | `entry` |
| `DashboardScreen` — four stat cards, two actions, offline banner | `dashboard` |
| `CaptureScreen` — viewfinder, GPS card, observation type, description | `capture` |
| `SiteAnalysisScreen` — score, completeness, indicators, recommendation | `site_analysis/{captureId}` |
| `InterventionsScreen` — filter chips + intervention cards | `interventions` |
| `PriorityMapScreen` — full filterable five-zone map | `priority_map` |
| `AiSettingsScreen` — experimental AI key/model + queue | `ai_settings` |
| `MonitoringScreen` — before/after photo evidence | `monitoring/{interventionId}` |

**Governance Mode** (officer login required):

| Screen | Route |
|---|---|
| `GovernanceLoginScreen` | `gov_login` |
| `GovernanceDashboardScreen` — live map, priority tallies, site list | `gov_dashboard` |
| `GovernancePrioritySiteScreen` — site read + Approve | `gov_priority_site/{captureId}` |
| `GovernancePipelineScreen` — stalled-first pipeline, completion % | `gov_pipeline` |

`MonitoringScreen` is reused for `gov_monitoring/{interventionId}`, and
`PriorityMapScreen` for `gov_priority_map` (a pin then opens the officer-facing
site screen instead of the Field one).

---

## Building

```
./gradlew assembleDebug
```

or open the repository root in Android Studio (Iguana or later). Camera
capture needs a physical device or an emulator with a virtual camera; GPS
needs a real device or an emulator with a simulated location set
(Extended Controls → Location).

---

## Known gaps against the problem statement

Recorded honestly rather than quietly.

1. **Nothing here has been watched running.** This is the real gap. See
   below.
2. **The priority score itself is still trivial** — 62 for a soil-erosion
   report, 50 otherwise. The reasoning layer now explains that score
   faithfully, which means it currently explains a two-branch rule. Real
   scoring needs the GIS backend that does not exist.
3. **Zone thresholds are unvalidated.** The slope / stream-distance / NDVI
   cut-offs in `PriorityExplainer` are conventional rules of thumb, not
   values calibrated against a dataset. They are collected at the top of
   that file specifically so a domain expert can argue with them.
4. **Captures without a GPS fix are not on the map.** They are counted and
   named as unplotted rather than placed at a guessed location.
5. **The AI model id may be stale.** It is editable at runtime rather than
   hardcoded, but check it before a demo.
6. **`app/build/` is committed** — see Repository hygiene below.

## Unverified claims

Written down so nobody mistakes review for testing:

- The app has been **compiled**, repeatedly and cleanly, but **never run**.
  No launch, no render, no crash check has been performed by the author of
  this document. Every feature listed above is verified only as far as
  "compiles and is present in the APK's dex".
- Every behavioural description above is derived from reading the source.
- The osmdroid map has never been displayed. `Configuration.osmdroidBasePath`
  and `osmdroidTileCache` are both redirected to `cacheDir` so that osmdroid
  does not reach for external storage the app has no permission for, but that
  fix is reasoned, not observed.
- The AI classification path has never made a real API call. The request
  shape, the response parsing and the error messages are untested against the
  live service.
- Room's schema went from v2 to v3 with `fallbackToDestructiveMigration()`,
  so an existing install will lose its data and re-seed on first launch of
  this version. That is intended for a demo build and would not be
  acceptable in the field.

---

## Repository hygiene

`app/build/` is committed to git and there is no `.gitignore`. Roughly 1,400
generated files are tracked, so every build dirties hundreds of them and
`git status` is unusable. The standard fix is a `.gitignore` plus
`git rm -r --cached app/build`, which is a large, noisy commit — deliberately
not done unilaterally.
