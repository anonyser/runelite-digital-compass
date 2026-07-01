package com.anonyser.digitalcompass;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

/**
 * All of the compass drawing and the angle math, with no RuneLite dependencies.
 * The plugin overlay and the standalone preview both draw through this, so they look identical.
 */
public final class CompassRenderer
{
	private static final String[] CARDINALS = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

	/** Plain drawing options (no RuneLite types). */
	public static final class Options
	{
		public int fontSize = 20;
		public boolean bold = true;
		public Color textColor = Color.WHITE;
		public boolean outline = true;
		public boolean showRose = true;
		public boolean showCardinal = true;
		public int dial = 120;
	}

	private CompassRenderer()
	{
	}

	/** Jagex angle units (0-2047, 0 = south) to a compass bearing (north = 0, clockwise). */
	public static double jauToBearing(int units)
	{
		double deg = (units * 360.0) / 2048.0;
		return (deg + 180.0) % 360.0;
	}

	/** Draws the compass at the current transform origin. Returns the space used. */
	public static Dimension draw(Graphics2D g, double bearing, Options o)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		bearing = ((bearing % 360) + 360) % 360;
		int shown = (int) Math.round(bearing) % 360;
		if (shown == 0)
		{
			shown = 360; // north reads as 360 per spec
		}

		int top = 0;
		if (o.showRose)
		{
			drawDial(g, bearing, o);
			top = o.dial;
		}
		int textHeight = drawReadout(g, shown, bearing, top, o);
		int width = o.showRose ? o.dial : Math.max(64, textHeight * 3);
		return new Dimension(width, top + textHeight + 4);
	}

	private static void drawDial(Graphics2D g, double bearing, Options o)
	{
		final int cx = o.dial / 2;
		final int cy = o.dial / 2;
		final int r = o.dial / 2 - 14;

		g.setColor(new Color(0, 0, 0, 140));
		g.fill(new Ellipse2D.Double(cx - r - 8, cy - r - 8, (r + 8) * 2, (r + 8) * 2));
		g.setColor(new Color(255, 255, 255, 90));
		g.setStroke(new BasicStroke(2f));
		g.draw(new Ellipse2D.Double(cx - r, cy - r, r * 2, r * 2));

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		drawCentered(g, "N", cx, cy - r + 4, Color.RED);
		drawCentered(g, "S", cx, cy + r - 4, Color.WHITE);
		drawCentered(g, "E", cx + r - 4, cy, Color.WHITE);
		drawCentered(g, "W", cx - r + 4, cy, Color.WHITE);

		final double rad = Math.toRadians(bearing);
		final int tipX = (int) Math.round(cx + Math.sin(rad) * (r - 8));
		final int tipY = (int) Math.round(cy - Math.cos(rad) * (r - 8));
		final double leftRad = rad + Math.toRadians(150);
		final double rightRad = rad - Math.toRadians(150);

		final Polygon arrow = new Polygon();
		arrow.addPoint(tipX, tipY);
		arrow.addPoint((int) Math.round(cx + Math.sin(leftRad) * 9), (int) Math.round(cy - Math.cos(leftRad) * 9));
		arrow.addPoint((int) Math.round(cx + Math.sin(rightRad) * 9), (int) Math.round(cy - Math.cos(rightRad) * 9));

		g.setColor(o.textColor);
		g.fill(arrow);
		g.setColor(new Color(0, 0, 0, 160));
		g.setStroke(new BasicStroke(1f));
		g.draw(arrow);
	}

	private static int drawReadout(Graphics2D g, int shown, double bearing, int top, Options o)
	{
		final int style = o.bold ? Font.BOLD : Font.PLAIN;
		g.setFont(new Font(Font.SANS_SERIF, style, o.fontSize));
		final FontMetrics fm = g.getFontMetrics();

		String text = shown + "°";
		if (o.showCardinal)
		{
			text += "  " + CARDINALS[(int) Math.round(bearing / 45.0) % 8];
		}

		final int x = 2;
		final int baseline = top + fm.getAscent();

		if (o.outline)
		{
			g.setColor(Color.BLACK);
			for (int dx = -1; dx <= 1; dx++)
			{
				for (int dy = -1; dy <= 1; dy++)
				{
					if (dx != 0 || dy != 0)
					{
						g.drawString(text, x + dx, baseline + dy);
					}
				}
			}
		}

		g.setColor(o.textColor);
		g.drawString(text, x, baseline);
		return fm.getHeight();
	}

	private static void drawCentered(Graphics2D g, String s, int x, int y, Color c)
	{
		final FontMetrics fm = g.getFontMetrics();
		g.setColor(c);
		g.drawString(s, x - fm.stringWidth(s) / 2, y + fm.getAscent() / 2);
	}
}
