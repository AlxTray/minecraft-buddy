package com.alxtray.minecraftbuddy;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

public class FrameGrabber implements ClientTickEvents.EndTick {

    private final long intervalMillis;
    private long lastCaptureTime = 0;

    public FrameGrabber(long intervalSeconds) {
        this.intervalMillis = intervalSeconds * 1000L;
    }

    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.world == null) return;

        long now = System.currentTimeMillis();
        if (now - lastCaptureTime < intervalMillis) return;

        lastCaptureTime = now;
        captureFramebuffer(client);
    }

    private void captureFramebuffer(MinecraftClient client) {
        Framebuffer framebuffer = client.getFramebuffer();

        int width = framebuffer.textureWidth;
        int height = framebuffer.textureHeight;

        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        System.out.println("Captured framebuffer at " + System.currentTimeMillis());
    }

    public static void register(long intervalSeconds) {
        ClientTickEvents.END_CLIENT_TICK.register(new FrameGrabber(intervalSeconds));
    }
}