package com.alxtray.minecraftbuddy;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.texture.NativeImage;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

public class FrameGrabber {
    public static void captureFramebuffer() {
        Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();

        int width = framebuffer.textureWidth;
        int height = framebuffer.textureHeight;

        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        NativeImage image = new NativeImage(width, height, false);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = ((height - 1 - y) * width + x) * 4;
                int r = buffer.get(index) & 0xFF;
                int g = buffer.get(index + 1) & 0xFF;
                int b = buffer.get(index + 2) & 0xFF;
                int a = buffer.get(index + 3) & 0xFF;

                image.setColor(x, y, (a << 24) | (b << 16) | (g << 8) | r);
            }
        }

        String path = FrameGrabber.class.getResource("/frames").getPath();
        try {
            image.writeTo(Paths.get(path, "frame.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            image.close();
        }
    }
}