# Digital Compass

A small RuneLite overlay that shows which way your character is facing, in degrees.

North reads as 360, east 90, south 180, west 270. The number updates in real time as you turn, and
there's a small dial with N/E/S/W markers and an arrow pointing the way you're facing.

## Options

- **Direction source** — your character's facing direction (default) or the camera.
- **Font size**, **bold**, and **text colour** for the readout.
- **Text outline** for readability over bright backgrounds.
- Toggle the **compass dial** and the **cardinal label** (N, NE, ...).
- **Calibration offset** if you want to nudge the zero point.

The overlay is draggable, so you can put it wherever you like.

## Building

Needs Java 11. From the repo root:

```
./gradlew build
```

To launch the client with the plugin loaded for manual testing, run the `main` method in
`DigitalCompassPluginTest`.

## License

BSD 2-Clause. See [LICENSE](LICENSE).
