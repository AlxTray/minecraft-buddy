package com.alxtray.minecraftbuddy;

import com.alxtray.minecraftbuddy.tts.implementations.DebugPrintOutResponseTTS;
import net.fabricmc.api.ClientModInitializer;

import static com.alxtray.minecraftbuddy.EventOrchestrator.*;

public class MinecraftbuddyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ConversationHandler conversationHandler = ConversationHandler.getInstance();
        conversationHandler.init();
        conversationHandler.subscribe(new DebugPrintOutResponseTTS());

		register(30);
	}
}