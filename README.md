# Pool 8 - 8-ball pool for Android (libGDX + Kotlin)

## Get the APK without installing anything (GitHub Actions)
1. Make a free account at github.com and create a new repository (any name, public or private).
2. Upload ALL files from this folder, including the hidden `.github` folder.
   (If `.github/workflows/build.yml` is missing after upload: Add file > Create new file,
   type `.github/workflows/build.yml` as the name and paste the contents of that file.)
3. Open the repo's **Actions** tab > "Build APK" > wait ~5-8 minutes for the green check.
   (If it didn't start: Actions > Build APK > Run workflow.)
4. Open the finished run, scroll to **Artifacts**, download `pool8-debug-apk`, unzip it.
5. Copy `android-debug.apk` to the Honor X6b, tap it, allow "install unknown apps", install.

## Or build in Android Studio
Open this folder, let Gradle sync (JDK 17), then Run, or Build > Build APK(s).

## How to play
- Drag on the table to aim (the cue points at your finger). FINE slider (right) trims the angle.
- Pull the POWER slider (left) down and release to shoot.
- SPIN ball (right): move the red dot. Up = follow, down = draw, sides = english.
- After a foul you get ball-in-hand: drag the cue ball, tap OK.
- When only the 8-ball is left, tap a pocket to call it before shooting.
- Rules: open table after the break; groups are assigned on the first legal pocketed ball after it.

## Tunables (core/.../Tuning.kt)
| Constant | Meaning |
|---|---|
| MAX_SPEED / MIN_SPEED | cue ball speed at 100% / 0% power (cm/s) |
| BALL_RESTITUTION | ball-ball bounce (0.95) |
| CUSHION_RESTITUTION | speed kept after hitting a rail (0.78) |
| ROLL_DECEL, ROLL_DRAG | constant + proportional rolling friction |
| STOP_SPEED | speed under which a ball stops |
| POCKET_FORGIVE | extra pocket capture radius |
| FOLLOW_FACTOR, SIDE_FACTOR | strength of top/back spin and side spin |
| SIDE_RAIL_K, SIDE_BALL_K | how spin changes the path off rails / after ball contact |
| FINE_RANGE | range of the fine-aim slider (radians) |

AI levels: Easy (aim error ~4 deg, picks among top 3 shots), Medium (~1.6 deg), Hard (~0.35 deg).
Run unit tests: `gradle :core:test`.
