package com.anonyser.digitalcompass;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("digitalcompass")
public interface DigitalCompassConfig extends Config
{
	enum DirectionSource
	{
		PLAYER,
		CAMERA
	}

	@ConfigItem(
		keyName = "directionSource",
		name = "Direction source",
		description = "Read the player's facing direction or the camera direction.",
		position = 0
	)
	default DirectionSource directionSource()
	{
		return DirectionSource.PLAYER;
	}

	@Range(min = 8, max = 64)
	@ConfigItem(
		keyName = "fontSize",
		name = "Font size",
		description = "Size of the degree text.",
		position = 1
	)
	default int fontSize()
	{
		return 20;
	}

	@ConfigItem(
		keyName = "bold",
		name = "Bold text",
		description = "Draw the degree text in bold.",
		position = 2
	)
	default boolean bold()
	{
		return true;
	}

	@ConfigItem(
		keyName = "textColor",
		name = "Text colour",
		description = "Colour of the degree text and the facing arrow.",
		position = 3
	)
	default Color textColor()
	{
		return Color.WHITE;
	}

	@ConfigItem(
		keyName = "outline",
		name = "Text outline",
		description = "Draw a dark outline behind the text so it stays readable.",
		position = 4
	)
	default boolean outline()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showRose",
		name = "Show compass dial",
		description = "Show the dial with N/E/S/W markers and a facing arrow.",
		position = 5
	)
	default boolean showRose()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showCardinal",
		name = "Show cardinal label",
		description = "Show the nearest cardinal direction (N, NE, ...) next to the degrees.",
		position = 6
	)
	default boolean showCardinal()
	{
		return true;
	}

	@Range(min = -180, max = 180)
	@ConfigItem(
		keyName = "calibration",
		name = "Calibration offset",
		description = "Nudge the zero point if the reading looks rotated (degrees).",
		position = 7
	)
	default int calibration()
	{
		return 0;
	}
}
