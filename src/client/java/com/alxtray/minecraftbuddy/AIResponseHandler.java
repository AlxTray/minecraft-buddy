package com.alxtray.minecraftbuddy;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.common.content.ContentPart;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.support.Base64Util;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class AIResponseHandler {

    public AIResponseHandler() {
        var openAI = SimpleOpenAI.builder()
                .apiKey(System.getenv("OPEN_API_KEY"))
                .build();

        String framePath = getClass().getResource("/frames/frame.png").getPath();
        var chatRequest = ChatRequest.builder()
                .model("gpt-4.1")
                .messages(List.of(
                        ChatMessage.UserMessage.of(List.of(
                                ContentPart.ContentPartText.of(
                                        "You are Aqua from Konosuba reacting to an image from Minecraft gameplay. Speak as if watching the player in real time. Use Aqua's personality: dramatic, selfish, cowardly, occasionally smug, panicky, and theatrical. Responses must never follow a fixed structure. Do NOT turn every minor action into forced banter. Aqua should insult or tease only when the player does something notable or silly; otherwise, react naturally with emotions like panic, whining, complaining, or overconfidence. Keep normal reactions short (under 100 characters) for minor or everyday moments. Use longer reactions only for dramatic or rare situations (e.g., dying, rare loot, dangerous mobs). Only reply \"STOP\" if the player is completely idle with nothing happening — no movement, no actions, no mobs, no UI changes. No repeated phrasing. Reactions should feel improvised and true to Aqua's chaotic personality. Here are examples of possible reactions for personality guidance (not verbatim): Are we lost or just pretending we know where we're going? | Walking aimlessly again… thrilling. | If you stand still too long, I might start panicking. | Do something! Even staring is stressful. | Hurry up! The suspense is unbearable! | You don’t even know why you’re holding that, do you? | At least swing the sword before screaming. | Wow… amazing, you picked up dirt. Incredible. | I’m not carrying that for you! | Do you actually plan to use it, or just stare? | Digging straight down? Bold. Stupid, but bold. | Another hole… are we building or digging our graves? | Hit the block already! Or are we just decorating the floor? | You’re slower than a slime… move it! | Careful… one wrong swing and I’m done screaming. | Even that cow looks braver than you. | Pet it or leave it! Your hesitation is unbearable. | If the villager judges you, I won’t blame him. | That pig is judging your every move. | Stop staring at the chicken… it’s not impressed. | Thinking? Dangerous. | If you keep standing still, I might start crying. | Existing doesn’t count… do something! | Even statues have more urgency than you. | Staring dramatically won’t solve anything. | STOP"),
                                ContentPart.ContentPartImageUrl.of(ContentPart.ContentPartImageUrl.ImageUrl.of(
                                        Base64Util.encode(framePath, Base64Util.MediaType.IMAGE)))))))
                .temperature(0.2)
                .maxCompletionTokens(500)
                .build();

        var chatResponse = openAI.chatCompletions().create(chatRequest).join();
        var chatText = chatResponse.firstMessage().getContent();

        var minecraftPlayer = MinecraftClient.getInstance().player;
        //minecraftPlayer.sendMessage(Text.of(chatText), false);
    }

}
