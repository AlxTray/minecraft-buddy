package com.alxtray.minecraftbuddy;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class EventOrchestrator implements ClientTickEvents.EndTick {
    private final long periodicInterval;
    private boolean onLoadingScreen = true;
    private long lastCaptureTime = 0;

    public EventOrchestrator(long periodicIntervalSeconds) {
        this.periodicInterval = periodicIntervalSeconds * 1000L;
    }

    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.world == null) return;

        long now = System.currentTimeMillis();
        if (now - lastCaptureTime < periodicInterval) return;
        lastCaptureTime = now;
        if (onLoadingScreen) {
            onLoadingScreen = false;
            return;
        }

        FrameGrabber.captureFrameBufferAsync();
        ConversationHandler.getInstance().runRequestAsync();
    }

    public static void register(long intervalSeconds) {
        ClientTickEvents.END_CLIENT_TICK.register(new EventOrchestrator(intervalSeconds));
    }
}
