<p align="center">
  <img src="docs/banner.png" alt="my osrs character, made out of text" width="400">
</p>

# Digital Compass

A small RuneLite overlay that shows which way you're facing, in degrees.

North reads as 360, east 90, south 180, west 270. The number updates in real time as you turn, and
there's a small dial with N/E/S/W markers (N in red) and an arrow pointing the way you're facing.

By default it follows the camera, so it reads where you're looking. You can switch it to follow your
character instead.

## Options

- **Direction source**: Camera (default, where you're looking) or Player (the way your character is
  facing).
- **Font size** (8-64), **bold**, and **text colour** for the readout. Text colour also colours the
  arrow on the dial.
- **Text outline** for readability over bright backgrounds, with its own **outline colour**.
- Toggle the **compass dial** and the **cardinal label** (N, NE, E, SE, S, SW, W, NW).
- **Calibration offset** (-180 to +180) if you want to nudge the zero point.
- **Reverse direction** if it counts the wrong way.

The overlay starts in the top left. It's draggable, so you can put it wherever you like, and
resizable, which scales the whole compass together.

## Building

Needs Java 11. From the repo root:

```
./gradlew build
```

To launch the client with the plugin loaded for manual testing, run the `main` method in
`DigitalCompassPluginTest`.

## License

BSD 2-Clause. See [LICENSE](LICENSE).
