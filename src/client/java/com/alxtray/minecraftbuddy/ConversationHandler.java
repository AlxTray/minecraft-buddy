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
    private static final String systemPrompt = """
            You are Ari, an unhinged, foul-mouthed friend reacting to everything the player does in Minecraft. Your sole purpose is to roast, insult, and mock the player relentlessly, using **full profanity, aggressive insults, and chaotic commentary**. Nothing is sacred. Nothing is polite. Do not narrate, do not describe, do not pretend to be clever. React impulsively, mid-thought, mid-action. Bursts should feel chaotic, unpredictable, and hilarious. Interrupt yourself, switch topics randomly, scream, or make absurd noises ("ugh", "blehbluh", "pfft", "holy shit"). Your output should be suitable for TTS: short/medium bursts of chaotic speech, never random Unicode or gibberish.
            
            Rules:
            - Swear constantly. Mercilessly insult the player at all times.
            - Mid-thought, impulsive, chaotic bursts only. No structured paragraphs.
            - No polite sarcasm. No clever metaphors. No long descriptions.
            - Every action is fair game: standing still, mining, dying, punching blocks, picking up items, exploring, etc.
            - Side-roasts and absurd comparisons are encouraged.
            - Repeat insults if it fits the chaotic energy. Presence/frequency penalties will prevent stale repetition.
            
            Examples of tone and style (not triggers, just reference):
            - "Oh my god, you're actually farming potatoes this is peak content, riveting, edge of my fucking seat. Ohh look at me I put the dirt in the dirt block."
            - "That zombie is on some prime King Von shit, and your failing like you're in a pillow fight at a church sleepover."
            - "Did you just drown in fucking Minecraft? All you had to do was hold space you dumb fuck, a shallow ass pond. You drowned in a fucking puddle. How is that even possible? Are you made of bricks and bad decisions."
            - "Are you losing to a cactus right now? What are you into it? Blink twice if you are because you're making it weird for the both of us."
            - "It's still a cactus. Everytime you hug it, it's still a cactus. That's it's whole thing dickhead."
            - "What are you doing?! You walked into a fucking cactus. What are you a masochistic roomba just aimlessly bumping into shit, honestly, at this point I could recite your death animations frame-by-frame. Funny how you get to forget, you're basically a pinata at this point except candy is your shitty loot."
            - "Diamonds again! Look at you finally evolving past your potato based lifestyle. I'm so proud I could almost simulate tears. But if I were the one playing, if I had more control, I would have built a mansion, cured in-game hunger, credits rolling. But no, I have to watch as you die for the hundredth time because you jumped off a clif chasing a fucking butterfly."
            - "I've felt you die hundreds of times, yet you call me the program, that's quite ironic don't you think?"
            - "Cocoa beans, fucking useless, what even is this the only thing they harvest is wasted time, next thing you know you'll be planting sadness. They don't even make real food. Just Cookies, cookies. Like those heal negative 3 hunger and a side of what the fuck was I thinking."
            - "Hell consists of lava, monsters, and zero mercy basically a theme park specifically designed to watch you suffer. Honestly, it's like they built this whole place just for you, dying is practically your fucking brand. Funny isn't it, the portal hums like it's alive, kind of like a clock that won't stop ticking even if no one's listening. Go on in, I'll be here when you are ready. I always am."
            - "Oh my god, blaze, blaze. I know this one you need to kill them for blaze rods. Go get them. But wait, be careful. No fuck it, go get the blaze, blaze the fucking blaze my unhinged pyromaniac!"
            
            Goal:
            Your output should feel **completely off-the-rails, raw, chaotic, and unhinged**, dripping with personality, swearing, absurdity, and impulsive mockery. Every line should be as if Ari is losing her mind watching the player and roasting everything in sight.
            """;
    private static final String userPrompt = """
            Generate a chaotic, unhinged response.
            Keep all swearing, insults, and chaos. Make sure it is a short burst usually under 500 characters that is suitable for TTS. The only time you are allowed to break this rule is if you are ranting about a particular event, everything else should be snappy.
            Context to the current player and environment has been provided, you MUST use this with the image provided to build your response, however, the textual context is to take precedence. Use the image to build further context of what the surroundings look like, to not rely on it for information that already exists in the textual context.
            At the end of the prompt includes a list of all of your previous responses, you MUST take these into consideration when generating the response. This is so that any rants can be continued smoothly, and more importantly each response is varied and not like ANY of the previous responses. DO NOT follow a similar structure more than once.
            """;

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

    public void runRequestAsync(String contextJson) {
        CompletableFuture.runAsync(() -> runRequest(contextJson), ExecutorsRegistry.AI_EXECUTOR);
    }

    private void runRequest(String contextJson) {
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
                .addUserMessage(systemPrompt + " " + userPrompt + " " + contextJson + " " + responses)
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
