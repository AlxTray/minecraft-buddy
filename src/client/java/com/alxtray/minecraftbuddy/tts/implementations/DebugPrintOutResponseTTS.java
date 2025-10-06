package com.alxtray.minecraftbuddy.tts.implementations;

import com.alxtray.minecraftbuddy.ExecutorsRegistry;
import com.alxtray.minecraftbuddy.interfaces.ResponseSubscriber;

import java.util.concurrent.CompletableFuture;

public class DebugPrintOutResponseTTS implements ResponseSubscriber {
    @Override
    public void onResponseAsync(Object response) {
        CompletableFuture.runAsync(() -> onResponse(response), ExecutorsRegistry.TTS_EXECUTOR);
    }

    @Override
    public void onResponse(Object response) {
        System.out.println("\u001B[31m" + response + "\u001B[0m");
    }
}
