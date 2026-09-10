# GeoWatershed — Setup Guide

Companion to `README.md`. That file says what the app *is*; this one gets it
running on your machine.

> **Status of this document.** Every step below has been re-derived from the
> current source, not copied forward from an earlier version of this guide.
> The build steps (§1–§3) are **verified** — `./gradlew assembleDebug` really
> does succeed. The first-run walkthrough (§4) is **derived from the code and
> not yet confirmed on a device**. Expected values are stated precisely so
> that the first person to run it can check them; if what you see disagrees,
> the app is wrong or this file is, and either way it needs correcting.

---

## 1. Prerequisites

- **Android Studio** Iguana or later. On first run, accept the SDK setup
  defaults and make sure **Android SDK Platform 34** and **Android SDK
  Build-Tools** are installed.
- **JDK 17** — Android Studio's bundled JBR is fine. The build sets
  `sourceCompatibility`/`targetCompatibility` to 17 and `jvmTarget = "17"`.
- Roughly 2 GB free for Gradle caches on a first sync.

`local.properties` in the repo root points `sdk.dir` at the SDK. It is
machine-specific and should not be committed; if your SDK lives elsewhere,
edit that line.

---

## 2. Open and build

```
git clone https://github.com/bhavyakumarsoni/sih.git
cd sih
./gradlew assembleDebug
```

Or open the repository root in Android Studio and let Gradle sync. The first
sync pulls Compose, Room, CameraX, osmdroid and play-services-location and
can take several minutes; subsequent builds are around 15 seconds.

**Expected result:** `BUILD SUCCESSFUL`, and a debug APK at
`app/build/outputs/apk/debug/app-debug.apk` (~13 MB).

**Expected warnings:** exactly three, all
`'PreferenceManager' is deprecated` in `PriorityMap.kt`. That is osmdroid's
documented init path. Ignore them.

If Android Studio offers to upgrade the Android Gradle Plugin, **decline.**
The versions in `build.gradle.kts` are pinned deliberately and version churn
before a deadline is a risk, not a cleanup.

---

## 3. Run it

### On a real Android phone — strongly preferred

Camera and GPS are the two things this app actually rides on, and both are
poor on emulators.

1. On the phone: Settings → About Phone → tap **Build Number** seven times.
2. Settings → Developer Options → enable **USB Debugging**.
3. Connect by USB, accept the "Allow USB debugging" prompt.
4. `./gradlew installDebug`, or pick the phone in Android Studio's device
   dropdown and hit **Run ▶**.

### On an emulator

1. Tools → Device Manager → Create Device → a Pixel profile → an API 34
   system image.
2. Run ▶.
3. The emulator camera shows a synthetic test pattern — fine for walking the
   flow, useless for real photos.
4. **Set a location or GPS will never resolve.** Extended Controls (`...`) →
   Location → set something near the seeded demo site, `13.13624, 78.13291`
   (Chinnahalli, Kolar Taluk), and click Send.

Without a simulated location the GPS card correctly settles on
`WAITING FOR FIX — TAP TO RETRY`. That is not a bug.

---

## 4. First-run walkthrough

Expected values are computed from `GeoWatershedRepository.seedIfEmpty()` and
the screen code. Treat any mismatch as a finding.

**1. Launch → Entry screen.** The app does *not* open on the Dashboard. It
opens on `EntryScreen`, which immediately requests location permission.
Expect:

- A heading that starts as "Locating your watershed…" and, once a fix and
  reverse geocode land, becomes a real place name. With no fix it becomes
  "Location unavailable"; with a fix but no geocoder result, "Unnamed
  watershed". All three are correct behaviour — none of them is a fabricated
  place name.
- An **ATTENTION REQUIRED TODAY** card reading "1 site is HIGH priority —
  action overdue" (the seeded `SITE-0142`, score 78).
- Two mode cards: **Field Mode** (no login) and **Governance Mode**.

> If this screen is empty or the app dies on launch, Room seeding is the
> first place to look — start there before anything else.

**2. Field Mode → Dashboard.** Expect exactly these four stats:

| Stat | Expected |
|---|---|
| IMAGES CAPTURED | 1 |
| HIGH PRIORITY SITES | 1 |
| INTERVENTIONS PROPOSED | 3 |
| INTERVENTIONS COMPLETED | 1 |

"Completed" counts any stage at or past Completed, so the seeded Contour
Trench (stage Monitoring) counts. Below the actions, an offline banner reads
**"1 record queued offline"**.

Note: the header's "GPS ON" dot is currently hardcoded on and does not
reflect real GPS state. Known defect, recorded in `README.md`.

**3. Capture screen.** Camera and location permissions are requested on
entry. Expect a live viewfinder with corner brackets and a clay shutter on
the right (or a dark "tap to grant permission" panel if camera was denied);
a GPS card moving `ACQUIRING FIX…` → real coordinates; a ten-option
observation list; and a 280-character description field.

Tap the shutter — a green **"✓ PHOTO CAPTURED — tap shutter again to
retake"** banner should appear. Then **Save & Analyze**.

**4. Site Analysis.** For a newly captured site, expect:

- Priority score **62** if you selected Soil Erosion, **50** otherwise.
- Data completeness **1 of 6** if you selected Soil Erosion, **0 of 6**
  otherwise — with the line "…they are excluded from the score, not counted
  as zero."
- Five or six indicators showing dashed `UNAVAILABLE` chips.
- A recommendation of **Field Survey**, with cost "COST · PENDING SURVEY"
  and eligibility "ELIGIBILITY · TBD".

This is the "missing ≠ zero" behaviour, and it is the honest default for
every real capture — there is no GIS backend. Open the seeded `SITE-0142`
instead to see a fully populated site (score 78, 4 of 6 indicators, Check
Dam, "EST. ₹48,000", "MGNREGA ELIGIBLE").

**5. Create Intervention** → returns to the Interventions list with a new
Proposed row.

**6. Interventions.** Four filter chips with live counts. Against the seed
alone: `ALL 3`, `PROPOSED 1`, `ACTIVE 1`, `DONE 1`. Chips should actually
change which cards show. **Advance Status** should step the pipeline and
survive backing out and returning. **Monitoring** opens the before/after
screen for that intervention.

**7. Before / After Monitoring.** Two columns. The seeded Check Dam starts
with 2 BEFORE and 1 AFTER photo, so the header reads **"3 / 4 PHOTOS"** and
**Submit for Verification** is disabled — the minimum is two per set. Add one
AFTER photo and it should enable. Tapping it relabels to
"✓ Submitted for Verification" and stays disabled.

**8. Restart the app.** Everything from steps 3–7 should still be there.
That is Room, and it is real.

---

## 5. Governance Mode

From the Entry screen, choose **Governance Mode**. There is no self-service
signup by design; credentials are issued out of band. For the demo, the two
accounts in `data/GovernanceCredentials.kt` are:

| Officer ID | Password | Shows as |
|---|---|---|
| `bdo.kolar` | `kolar@2026` | BDO Kolar |
| `officer1` | `chinnahalli1` | Field Officer · Chinnahalli |

After login, expect a **live OpenStreetMap map** with one pin per geo-tagged
capture — this needs network for tiles the first time; osmdroid caches them
to `cacheDir` afterwards. Tapping a pin opens that site's analysis with an
officer-only **Approve** action, which creates the intervention directly in
the Approved stage.

Pins are currently all identical default markers. The five-zone colour
classification is not built yet.

---

## 6. Demoing "missing ≠ zero" deliberately

Simply capture any photo. Because there is no GIS backend at all, every
newly captured site shows dashed `UNAVAILABLE` chips for Slope, Distance to
Stream, Vegetation, Existing Structure and Agricultural Land, and a
completeness bar well under full.

> Earlier versions of this guide told you to capture near `19.85, 73.43` to
> land in an uncovered zone of a synthetic GIS layer. **That is obsolete.**
> There is no synthetic GIS layer and no `MockGisRepository` — those were
> removed. The demo watershed also moved from Nashik to Kolar.

---

## 7. Troubleshooting

**Gradle can't resolve dependencies.** The build needs network access to
`dl.google.com` and `repo1.maven.org`. On a restricted network nothing will
resolve and the failure looks like a code problem but is not.

**Map is blank grey.** osmdroid needs network for its first tile fetch.
`Configuration.osmdroidBasePath` and `osmdroidTileCache` are both pointed at
`cacheDir`; if you see storage-permission errors from osmdroid, that
redirect is what to check.

**GPS never resolves on an emulator.** Expected — set a simulated location,
see §3.

**Dashboard is empty on first launch.** Seeding failed. The database is
`geowatershed.db`, schema v2, with `fallbackToDestructiveMigration()`, so
clearing app data and relaunching will re-seed.
