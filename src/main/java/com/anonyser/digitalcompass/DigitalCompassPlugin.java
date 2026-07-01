package com.anonyser.digitalcompass;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Digital Compass",
	description = "A clean on-screen compass that shows which way you are facing, in degrees",
	tags = {"compass", "direction", "overlay", "degrees", "digital"}
)
public class DigitalCompassPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private DigitalCompassOverlay overlay;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

	@Provides
	DigitalCompassConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DigitalCompassConfig.class);
	}
}
