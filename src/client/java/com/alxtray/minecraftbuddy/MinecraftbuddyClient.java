package com.alxtray.minecraftbuddy;

import net.fabricmc.api.ClientModInitializer;

import static com.alxtray.minecraftbuddy.EventOrchestrator.*;

public class MinecraftbuddyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ConversationHandler.getInstance().init();
		register(10);
	}
}