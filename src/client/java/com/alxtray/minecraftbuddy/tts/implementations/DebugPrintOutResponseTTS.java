package com.alxtray.minecraftbuddy.tts.implementations;

import com.alxtray.minecraftbuddy.ExecutorsRegistry;
import com.alxtray.minecraftbuddy.ModelResponse;
import com.alxtray.minecraftbuddy.interfaces.ResponseSubscriber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletableFuture;

import static com.alxtray.minecraftbuddy.Minecraftbuddy.LOGGER;

public class DebugPrintOutResponseTTS implements ResponseSubscriber {
    @Override
    public void onResponseAsync(ModelResponse modelResponse) {
        CompletableFuture.runAsync(() -> onResponse(modelResponse), ExecutorsRegistry.TTS_EXECUTOR)
                .exceptionally(ex -> {
                    LOGGER.error("Failed to use debug TTS", ex);
                    return null;
                });
    }

    @Override
    public void onResponse(ModelResponse modelResponse) {
        LOGGER.info("\u001B[31m{}\u001B[0m", modelResponse.message);

        ObjectMapper objectMapper = new ObjectMapper();
        String modelResponseJson;
        try {
            modelResponseJson = objectMapper.writeValueAsString(modelResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info(modelResponseJson);
    }
}
