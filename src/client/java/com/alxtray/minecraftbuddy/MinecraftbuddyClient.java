package com.alxtray.minecraftbuddy;

import net.fabricmc.api.ClientModInitializer;

import static com.alxtray.minecraftbuddy.FrameGrabber.*;

public class MinecraftbuddyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        AIResponseHandler.getInstance().init();
		register(10);
	}
}