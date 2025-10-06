package com.alxtray.minecraftbuddy;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class ExecutorsRegistry {
    public static final ExecutorService TTS_EXECUTOR =
            Executors.newFixedThreadPool(20, named("TTS thread"));
    public static final ExecutorService FRAMEBUFFER_EXECUTOR =
            Executors.newFixedThreadPool(4, named("Framebuffer thread"));
    public static final ExecutorService AI_EXECUTOR =
            Executors.newCachedThreadPool(named("AI thread"));

    private static ThreadFactory named(String pattern) {
        return new ThreadFactoryBuilder().setNameFormat(pattern).build();
    }
}
