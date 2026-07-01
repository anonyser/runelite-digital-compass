package com.anonyser.digitalcompass;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class DigitalCompassOverlay extends Overlay
{
	private final Client client;
	private final DigitalCompassConfig config;

	@Inject
	DigitalCompassOverlay(Client client, DigitalCompassConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
	}

	@Override
	public Dimension render(Graphics2D g)
	{
		final Player local = client.getLocalPlayer();
		if (local == null)
		{
			return null;
		}

		final int units = local.getOrientation();
		final double bearing = CompassRenderer.jauToBearing(units) + config.calibration();

		final CompassRenderer.Options o = new CompassRenderer.Options();
		o.fontSize = config.fontSize();
		o.bold = config.bold();
		o.textColor = config.textColor();
		o.outline = config.outline();
		o.showRose = config.showRose();
		o.showCardinal = config.showCardinal();

		return CompassRenderer.draw(g, bearing, o);
	}
}
