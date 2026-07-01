package com.anonyser.digitalcompass;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DigitalCompassPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DigitalCompassPlugin.class);
		RuneLite.main(args);
	}
}
