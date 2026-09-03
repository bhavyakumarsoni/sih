# GeoWatershed — Setup Guide (V1 scaffold)

## What's in this build

The **core demo loop** end to end, on synthetic/mock GIS data:

```
Dashboard → Capture (camera + real GPS) → Site Analysis (priority score,
completeness %, unavailable-parameter flags, rule-based recommendation)
→ Create Intervention → Before/After Monitoring
```

What's real:
- Live camera capture (CameraX) and live device GPS (fused location) for field images.
- A real Room (SQLite) database — data persists across app restarts, fully offline.
- The priority scoring engine and rule-based recommendation engine run for real,
  against synthetic GIS values (see below).
- The "missing ≠ zero" behavior is real and demoable: a small zone in the demo
  watershed deliberately has no GIS coverage, so capturing a photo there will show
  `UNAVAILABLE` on some indicators and a completeness % under 100.

What's mocked (intentionally, per our scoping conversation):
- All GIS layers (DEM/slope, drainage, land-use, soil) are synthetic values
  generated per-coordinate, not a real dataset. Swapping in a real DEM/shapefile
  later only requires replacing `MockGisRepository.lookup()` — nothing else in
  the app needs to change, since the rest of the pipeline just consumes whatever
  that function returns.
- No AI/image classification (kept out of V1 per the "AI is optional, not
  mandatory" principle in your docs).
- No map rendering yet (Screen 3 "GIS Map" isn't built — see Next Steps).

---

## 1. Install Android Studio

1. Download Android Studio from https://developer.android.com/studio
2. Install it (Windows/Mac/Linux all supported) and run it once — it will prompt
   you to install the Android SDK, platform tools, and an emulator image. Accept
   the defaults.
3. During setup, make sure **Android SDK Platform 34** and **Android SDK
   Build-Tools** are checked (Studio usually selects these automatically).

## 2. Open the project

1. Unzip the `GeoWatershed` folder you received.
2. Open Android Studio → **File → Open** → select the unzipped `GeoWatershed` folder.
3. Let Gradle sync (bottom status bar). First sync can take a few minutes — it's
   downloading dependencies (Compose, Room, CameraX, etc.).
4. If Studio prompts to "Upgrade Gradle Plugin" or similar, you can accept —
   the versions pinned in `build.gradle.kts` are current as of this scaffold but
   Studio may suggest newer patch versions, which is fine.

## 3. Run it

**Fastest: on a real Android phone** (recommended — camera + GPS work properly on hardware, poorly on emulators):
1. On your phone: Settings → About Phone → tap "Build Number" 7 times to enable Developer Options.
2. Settings → Developer Options → enable "USB Debugging".
3. Connect the phone via USB. Accept the "Allow USB debugging" prompt on the phone.
4. In Android Studio, select your phone from the device dropdown (top toolbar) and click **Run ▶**.

**Alternative: emulator**
1. Tools → Device Manager → Create Device → pick a Pixel profile → download a system image (API 34) → Finish.
2. Select the emulator from the device dropdown and click **Run ▶**.
3. Camera on emulator shows a synthetic test pattern (fine for demoing the flow, not for real photos).
   For GPS, use the emulator's "Extended Controls" (`...` icon) → Location, to set a fake coordinate —
   set it near `19.8745, 73.4513` to land inside the demo watershed's covered zone.

## 4. First-run walkthrough (mirrors your 10-step SIH demo scenario)

1. Launch the app → Dashboard (stats start at 0).
2. Tap **Capture Field Image** → grant Camera + Location permissions when prompted.
3. Wait for "GPS Status: Acquired ✓" (on a real phone, may take a few seconds outdoors;
   indoors GPS can be slow/unreliable — step outside or near a window if it hangs).
4. Tap **Take Photo**.
5. Select an observation type (e.g. "Soil Erosion"), optionally add a description.
6. Tap **Save & Analyze** → you land on the Site Analysis screen.
7. You'll see: priority score, HIGH/MEDIUM/LOW classification, a data-completeness bar,
   each site indicator (or "UNAVAILABLE" if that GIS layer didn't cover the point),
   and a recommended intervention with reasons.
8. Tap **Create Intervention** → lands on the Monitoring screen for that intervention.
9. Tap **Add BEFORE Photo**, take a photo. Later, tap **Add AFTER Photo** to simulate
   post-intervention monitoring.
10. Back out to Dashboard — stats (Images Captured, High Priority Sites, etc.) update live.

**To demo the "missing data" behavior deliberately:** capture a photo roughly
0.02–0.03° south-west of `19.8745, 73.4513` (e.g. try `19.85, 73.43`) — that
range has no synthetic GIS coverage by design, so the Analysis screen will show
`UNAVAILABLE` for several indicators and completeness under 100%, exactly as
described in your "Missing ≠ Zero" principle.

---

## 5. Project structure

```
app/src/main/java/com/geowatershed/app/
├── data/
│   ├── db/            Room entities, DAOs, database
│   ├── mock/           MockGisRepository — swap this out for real GIS later
│   └── repository/     GeoWatershedRepository — single source of truth for the UI
├── engine/
│   └── PriorityScoreEngine.kt   Scoring + rule-based recommendation logic
├── ui/
│   ├── screens/         One package per screen (dashboard, capture, analysis,
│   │                    intervention, monitoring)
│   ├── nav/              Navigation graph + a lightweight ViewModel factory
│   └── theme/            Compose theme/colors
├── MainActivity.kt        NavHost wiring
└── GeoWatershedApp.kt      Application class holding the repository singleton
```

## 6. Suggested next build priorities (matching your MVP phases)

Given the core loop above is stable, in priority order:
1. **GIS Map screen** (Screen 3) — even a simple marker map (e.g. osmdroid or
   Google Maps Compose) showing captured field-image points would close the
   biggest visible gap versus the proposal doc.
2. **Existing image import with EXIF GPS extraction** (section 8 of the proposal) —
   lets you seed the demo with more than just live-captured photos before showtime.
3. Polish pass: replace the mock launcher icon situation (currently none —
   Android will use a default), add a splash screen, tidy empty states.

Deliberately **not** next: buffer/neighborhood GIS analysis, data-vintage UI,
cloud sync, AI classification — all correctly scoped as V1.5+/future in your
docs, and should stay that way unless V1's core loop is rock-solid on a real
device first.
