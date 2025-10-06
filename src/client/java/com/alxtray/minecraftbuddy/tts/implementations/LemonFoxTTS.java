package com.alxtray.minecraftbuddy.tts.implementations;

import com.alxtray.minecraftbuddy.ExecutorsRegistry;
import com.alxtray.minecraftbuddy.interfaces.ResponseSubscriber;
import javazoom.jl.player.Player;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.elevenlabs.ElevenLabsTextToSpeechModel;
import org.springframework.ai.elevenlabs.ElevenLabsTextToSpeechOptions;
import org.springframework.ai.elevenlabs.api.ElevenLabsApi;

import java.io.ByteArrayInputStream;
import java.util.concurrent.CompletableFuture;

public class LemonFoxTTS implements ResponseSubscriber {
    private final ElevenLabsApi elevenLabsApi;

    public LemonFoxTTS() {
        elevenLabsApi = ElevenLabsApi.builder()
                .apiKey(System.getenv("LEMONFOX_API_KEY"))
                .baseUrl("https://api.lemonfox.ai")
                .build();
    }

    @Override
    public void onResponseAsync(Object response) {
        CompletableFuture.runAsync(() -> onResponse(response), ExecutorsRegistry.TTS_EXECUTOR);
    }

    @Override
    public void onResponse(Object response) {
        ElevenLabsTextToSpeechModel elevenLabsTextToSpeechModel = ElevenLabsTextToSpeechModel.builder()
                .elevenLabsApi(elevenLabsApi)
                .defaultOptions(ElevenLabsTextToSpeechOptions.builder()
                        .voiceId("bella")
                        .outputFormat("mp3")
                        .build())
                .build();

        TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt((String)response);
        TextToSpeechResponse speechResponse = elevenLabsTextToSpeechModel.call(speechPrompt);

        byte[] ttsChunk = speechResponse.getResult().getOutput();
        try (ByteArrayInputStream ttsBytes = new ByteArrayInputStream(ttsChunk)) {
            Player player = new Player(ttsBytes);
            System.out.println("STARTING TTS");
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
