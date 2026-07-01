# Publishing to the RuneLite Plugin Hub

RuneLite plugins aren't uploaded through a website or API. You get a plugin listed by opening a pull
request against RuneLite's Plugin Hub repo, and a maintainer reviews and merges it. Here's the process
for this plugin.

## Where it goes
- This repo (public) holds the plugin source.
- The listing is a small marker file added to `runelite/plugin-hub` via a pull request.
- Once merged, the plugin shows up in the client under **Configuration > Plugin Hub**.

## Steps

1. Make sure the plugin builds and has been tested in the client (`./gradlew build`, then run
   `DigitalCompassPluginTest` and check it in-game).
2. Push the final commit to this repo and copy the **full 40-character commit hash**
   (`git rev-parse HEAD`).
3. Fork `https://github.com/runelite/plugin-hub`.
4. In the fork, add a file at `plugins/digital-compass` (no extension) with:
   ```
   repository=https://github.com/anonyser/runelite-digital-compass.git
   commit=<full 40-char hash>
   ```
5. Open a pull request from the fork to `runelite/plugin-hub`.
6. Their CI builds the plugin and runs checks. A maintainer then reviews it — mainly that it isn't
   malicious, doesn't break Jagex's rules, and isn't a disallowed feature type.
7. If they ask for changes: fix them here, push, copy the new commit hash, and update the `commit=`
   line in the **same** PR. Don't open a new PR for each change.
8. After it's merged it becomes installable in the client, usually within a short while.

## Rules this plugin needs to keep to
- Display only. It reads orientation and draws an overlay. It never sends input or acts for the player.
- No automation or botting behaviour.
- Keep dependencies minimal and `build=standard`.

## Tracking after submission
Use the Project Tracker app's RuneLite panel for this project:
- commit hash used in the marker file
- CI status on the PR
- review status (open / changes requested / merged)
- notes from the reviewers and what was changed in response

## Note on the degree readout
The facing math converts Jagex angle units to a compass bearing (north = 360, east 90, south 180,
west 270). It hasn't been verified live in-game yet. If the reading looks rotated, use the
**Calibration offset** config option to line it up — no code change needed.
