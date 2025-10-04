package com.alxtray.minecraftbuddy.tts.implementations;

import com.alxtray.minecraftbuddy.interfaces.ResponseSubscriber;

public class DebugPrintOutResponseTTS implements ResponseSubscriber {
    @Override
    public void onResponse(Object response) {
        System.out.println("\u001B[31m" + response + "\u001B[0m");
    }
}
