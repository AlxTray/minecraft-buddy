package com.alxtray.minecraftbuddy;

import com.alxtray.minecraftbuddy.tts.implementations.DebugPrintOutResponseTTS;
import com.alxtray.minecraftbuddy.tts.implementations.ElevenLabsTTS;
import net.fabricmc.api.ClientModInitializer;

import static com.alxtray.minecraftbuddy.EventOrchestrator.*;

public class MinecraftbuddyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ConversationHandler conversationHandler = ConversationHandler.getInstance();
        conversationHandler.init();
        conversationHandler.subscribe(new DebugPrintOutResponseTTS());
        conversationHandler.subscribe(new ElevenLabsTTS());

		register(30);
	}
}