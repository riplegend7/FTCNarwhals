# FTC Team 30681 – Narwhals (2025–2026 Season)

This repository contains the official code for FTC Team 30681's robot for the 2025–2026 season.  
It is based on the [FTC SDK](https://github.com/FIRST-Tech-Challenge/FtcRobotController).

---

## Repository Structure
- **TeamCode/** – our custom code
  - `opmodes/` → TeleOp and Autonomous programs
  - `subsystems/` → drive, arm, intake, shooter, etc.
  - `util/` → math, constants, helpers
  - `tests/` → diagnostic and hardware test OpModes
- **FtcRobotController/** – FTC SDK + our modifications  
  - Includes official SDK code plus team-specific adjustments (resources, activities, extended utilities).  
  - All changes here should be tracked in `docs/sdk-changes.md`.
- **docs/** – documentation for the team
  - `setup.md` → Android Studio + SDK setup
  - `hardware.md` → robot wiring and configuration
  - `controls.md` → driver gamepad mapping
  - `sdk-changes.md` → record of edits made inside `FtcRobotController`
- **README.md** – project overview
- **.gitignore** – ignores build output and system files
- **Gradle files** (`build.gradle`, `settings.gradle`, `gradlew`, `gradlew.bat`, `gradle/`)

---

## Getting Started
1. Clone this repo using [GitHub Desktop](https://desktop.github.com/) or Git.
2. Open the project root (`FTCNarwhals/`) in **Android Studio**.
3. Let Gradle sync (first time may take a few minutes).
4. Connect the Robot Controller phone via USB.
5. Build and deploy an OpMode to test.

---

## Branch Strategy
- **main** → stable, competition-ready code.
- **dev** → active development branch.
- **feature branches** → one branch per subsystem, feature, or bugfix.  
  Examples: `feat/mecanum-drive`, `fix/servo-reverse`, `docs/update-controls`.

All code merges into `main` through pull requests.

---

## Commit Guidelines
Use short, descriptive commit messages:  
- `feat(teamcode): add mecanum drive subsystem`  
- `fix(robotcontroller): reverse intake motor`  
- `docs: update wiring diagram`

---

## Documentation
- [docs/setup.md](docs/setup.md) – environment setup  
- [docs/hardware.md](docs/hardware.md) – hardware ports and wiring  
- [docs/controls.md](docs/controls.md) – driver gamepad mapping  
- [docs/sdk-changes.md](docs/sdk-changes.md) – record of modifications in `FtcRobotController`

---

## Tags and Releases
- We tag major milestones and competition builds.  
- Example: `v1.0-league-meet-1`.

---

## Credits
- **FTC Team 30681 – Narwhals**  
- Mentors and coaches  
- [FIRST Tech Challenge](https://www.firstinspires.org/robotics/ftc)  