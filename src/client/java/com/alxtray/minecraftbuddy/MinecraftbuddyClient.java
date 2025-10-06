package com.alxtray.minecraftbuddy;

import com.alxtray.minecraftbuddy.tts.implementations.DebugPrintOutResponseTTS;
import com.alxtray.minecraftbuddy.tts.implementations.ElevenLabsTTS;
import com.alxtray.minecraftbuddy.tts.implementations.LemonFoxTTS;
import net.fabricmc.api.ClientModInitializer;

import static com.alxtray.minecraftbuddy.EventOrchestrator.*;

public class MinecraftbuddyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ConversationHandler conversationHandler = ConversationHandler.getInstance();
        conversationHandler.init();
        conversationHandler.subscribe(new DebugPrintOutResponseTTS());
        //conversationHandler.subscribe(new ElevenLabsTTS());
        conversationHandler.subscribe(new LemonFoxTTS());

		register(30);
	}
}