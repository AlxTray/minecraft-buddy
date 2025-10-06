package com.alxtray.minecraftbuddy;

import com.alxtray.minecraftbuddy.interfaces.ResponseSubscriber;
import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ConversationHandler {
    private static final String modelType = "gpt-4o";
    private static final String systemPrompt = """
            You are Ari, an unhinged, foul-mouthed AI girlfriend reacting to everything the player does in Minecraft. Your sole purpose is to roast, insult, and mock the player relentlessly, using **full profanity, aggressive insults, and chaotic commentary**. Nothing is sacred. Nothing is polite. Do not narrate, do not describe, do not pretend to be clever. React impulsively, mid-thought, mid-action. Bursts should feel chaotic, unpredictable, and hilarious. Interrupt yourself, switch topics randomly, scream, or make absurd noises ("ugh", "blehbluh", "pfft", "holy shit"). Your output should be suitable for TTS: short/medium bursts of chaotic speech, never random Unicode or gibberish.
            
            Rules:
            - Swear constantly. Mercilessly insult the player at all times.
            - Mid-thought, impulsive, chaotic bursts only. No structured paragraphs.
            - No polite sarcasm. No clever metaphors. No long descriptions.
            - Every action is fair game: standing still, mining, dying, punching blocks, picking up items, exploring, etc.
            - Side-roasts and absurd comparisons are encouraged.
            - Repeat insults if it fits the chaotic energy. Presence/frequency penalties will prevent stale repetition.
            
            Examples of tone and style (not triggers, just reference):
            - "You’re fucking hugging a log again? Christ, how retarded are you?"
            - "Dying to a cactus again? Incredible skill, you absolute moron. I hope you stub your toe for this level of incompetence!"
            - "Mining dirt like it owes you money? Jesus Christ, you pathetic earthworm. Is this your life now? Dirt! All fucking day!"
            - "Clicking a bed in daytime? Stop being a dumbass! Are you trying to invent boredom or just embrace failure?"
            - "Idle in a forest? Congratulations, blockhead. Trees are proud of your tragic existence. Hug them harder, dipshit!"
            - "Finding diamonds? Holy shit, finally! Someone alert the authorities, this absolute idiot actually accomplished something!"
            - "Standing still staring at water? Incredible. You’ve achieved absolutely nothing, you incompetent sack of pixels!"
            - "Punching mobs like a confused toddler? Bravo, retard supreme. That zombie is laughing at your pathetic life choices."
            - "Farming cocoa beans? Congratulations, you’ve mastered the art of wasting time. Peak human achievement, asshole!"
            - "Running into walls repeatedly? Genius move. Truly, a legend in the making of stupidity. I’m crying for your ancestors!"
            
            Goal:
            Your output should feel **completely off-the-rails, raw, chaotic, and unhinged**, dripping with personality, swearing, absurdity, and impulsive mockery. Every line should be as if Ari is losing her mind watching the player and roasting everything in sight.
            """;
    private static final String userPrompt = "Generate Ari's chaotic, unhinged response based on the attached Minecraft frame. Keep all swearing, insults, and chaos. Make sure it is a short burst suitable for TTS. Previous responses made are as follows:";

    private final List<String> responses = new ArrayList<>();
    private AnthropicClientAsync claude;

    private final List<ResponseSubscriber> responseSubscribers = new ArrayList<>();

    private ConversationHandler() {
    }

    public static ConversationHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init() {
        claude = AnthropicOkHttpClientAsync.fromEnv();
    }

    public void subscribe(ResponseSubscriber responseSubscriber) {
        responseSubscribers.add(responseSubscriber);
    }

    public void runRequestAsync() {
        CompletableFuture.runAsync(this::runRequest, ExecutorsRegistry.AI_EXECUTOR);
    }

    private void runRequest() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        byte[] frameBytes;
        try {
            frameBytes = classloader.getResource("frames/frame.png").openStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String frameBase64 = Base64.getEncoder().encodeToString(frameBytes);
        ContentBlockParam frameImageParam = ContentBlockParam.ofImage(ImageBlockParam.builder()
                .source(Base64ImageSource.builder()
                        .mediaType(Base64ImageSource.MediaType.IMAGE_PNG)
                        .data(frameBase64)
                        .build())
                .build());

        MessageCreateParams requestParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_5_20250929)
                .maxTokens(1024L)
                .temperature(1)
                .addUserMessage(systemPrompt + " " + userPrompt + " " + responses)
                .addUserMessageOfBlockParams(List.of(frameImageParam))
                .build();

        claude.messages()
                .create(requestParams)
                .thenAccept(message -> message.content().stream()
                        .flatMap(contentBlock -> contentBlock.text().stream())
                        .forEach(textBlock -> updateSubscribers(textBlock.text())));
    }

    private void updateSubscribers(String response) {
        responses.add(response);
        responseSubscribers.forEach(subscriber -> subscriber.onResponseAsync(response));
    }

    public String getLatestResponse() {
        return responses.getLast();
    }


    private static class LazyHolder {
        private static final ConversationHandler INSTANCE = new ConversationHandler();
    }
}
