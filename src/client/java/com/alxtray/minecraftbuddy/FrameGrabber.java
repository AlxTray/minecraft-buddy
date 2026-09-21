package com.alxtray.minecraftbuddy;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.ScreenshotRecorder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.alxtray.minecraftbuddy.Minecraftbuddy.LOGGER;

public class FrameGrabber {
    public static void captureFrameBufferAsyncAndAwait() {
        CompletableFuture.runAsync(FrameGrabber::captureFramebuffer, ExecutorsRegistry.FRAMEBUFFER_EXECUTOR)
                .exceptionally(ex -> {
                    LOGGER.error("Failed to capture frame", ex);
                    return null;
                })
                .join();
    }

    public static void captureFramebuffer() {
        MinecraftClient mc = MinecraftClient.getInstance();
        Framebuffer framebuffer = mc.getFramebuffer();

        File frameDir = new File(mc.runDirectory, "minecraft-buddy");
        if (!frameDir.exists()) {
            frameDir.mkdirs();
        }
        File outputFile = new File(frameDir, "frame.png");

        mc.execute(() -> ScreenshotRecorder.takeScreenshot(framebuffer, (image) -> {
            CompletableFuture.runAsync(() -> {
                try (image) {
                    image.writeTo(outputFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, ExecutorsRegistry.FRAMEBUFFER_EXECUTOR)
                    .exceptionally(ex -> {
                        LOGGER.error("Failed to write frame to file", ex);
                        return null;
                    });
        }));

        LOGGER.info("FINISHED FRAMEBUFFER");
    }
}