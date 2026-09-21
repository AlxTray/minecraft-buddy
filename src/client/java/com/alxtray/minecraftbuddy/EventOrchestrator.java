package com.alxtray.minecraftbuddy;

import com.alxtray.minecraftbuddy.gamecontext.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerInventory;

import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static com.alxtray.minecraftbuddy.Minecraftbuddy.LOGGER;

public class EventOrchestrator implements ClientTickEvents.StartTick {
    private final long periodicInterval;
    private boolean onLoadingScreen = true;
    private long lastCaptureTime = 0;

    public EventOrchestrator(long periodicIntervalSeconds) {
        this.periodicInterval = periodicIntervalSeconds * 1000L;
    }

    @Override
    public void onStartTick(MinecraftClient client) {
        if (client.world == null) return;

        long now = System.currentTimeMillis();
        if (now - lastCaptureTime < periodicInterval) return;
        lastCaptureTime = now;
        if (onLoadingScreen) {
            onLoadingScreen = false;
            return;
        }

        ClientPlayerEntity player = client.player;
        PlayerInventory playerInventory = player.getInventory();
        PlayerContext playerContext = new PlayerContext(
                player.getHealth(),
                player.getMaxHealth(),
                player.getHungerManager().getFoodLevel(),
                player.getHungerManager().getSaturationLevel(),
                player.getAir(),
                player.getArmor(),
                player.experienceLevel,
                player.age,
                playerInventory.getStack(playerInventory.getSelectedSlot()).getItemName().getString(),
                new InventoryContext(
                        playerInventory.getStack(playerInventory.getSelectedSlot()).getItemName().getString(),
                        IntStream.range(0, 9)
                                .mapToObj(playerInventory::getStack)
                                .filter(stack -> !stack.isEmpty())
                                .map(stack -> new ItemContext(stack.getItemName().getString(), stack.getCount()))
                                .toList(),
                        IntStream.range(10, 39)
                                .mapToObj(playerInventory::getStack)
                                .filter(stack -> !stack.isEmpty())
                                .map(stack -> new ItemContext(stack.getItemName().getString(), stack.getCount()))
                                .toList(),
                        new ItemContext(
                                playerInventory.getStack(playerInventory.getSwappableHotbarSlot()).getItemName().getString(),
                                playerInventory.getStack(playerInventory.getSwappableHotbarSlot()).getCount()
                        ),
                        IntStream.range(0, 26)
                                .mapToObj(i -> player.getEnderChestInventory().getStack(i))
                                .filter(stack -> !stack.isEmpty())
                                .map(stack -> new ItemContext(stack.getItemName().getString(), stack.getCount()))
                                .toList()
                ),
                player.getX(),
                player.getY(),
                player.getZ(),
                player.getYaw(),
                player.getPitch(),
                player.getVelocity().x,
                player.getVelocity().y,
                player.getVelocity().z,
                player.isRiding(),
                player.isSneaking(),
                player.isSubmergedInWater(),
                player.isGliding(),
                player.isAlive(),
                player.isCrawling(),
                player.isInFluid(),
                player.isInLava(),
                player.isJumping(),
                player.isSprinting(),
                player.isSwimming(),
                player.isUsingSpyglass(),
                player.isClimbing(),
                player.getSleepTimer(),
                player.getGameMode().toString(),
                player.getStatusEffects()
                        .stream()
                        .map(effect -> new StatusEffectContext(effect.getEffectType().getType().name(), effect.getAmplifier(), effect.getDuration()))
                        .toList()
        );
        ClientWorld world = client.world;
        EnvironmentContext environmentContext = new EnvironmentContext(
                StreamSupport.stream(world.getEntities().spliterator(), false)
                        .filter(entity -> entity instanceof MobEntity mob
                                && mob.squaredDistanceTo(player) <= 15 * 15)
                        .map(MobEntity.class::cast)
                        .map(entity -> new EntityContext(
                                entity.getName().getString(),
                                new ItemContext(entity.getActiveItem().getItemName().getString(), entity.getActiveItem().getCount()),
                                entity.distanceTo(player)))
                        .toList(),
                world.isRaining(),
                world.getTimeOfDay(),
                world.getBiome(player.getBlockPos()).getIdAsString().toString()
        );
        GameContext context = new GameContext(playerContext, environmentContext, world.getTime());

        ObjectMapper objectMapper = new ObjectMapper();
        String contextJson;
        try {
            contextJson = objectMapper.writeValueAsString(context);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info(contextJson);

        FrameGrabber.captureFrameBufferAsyncAndAwait();
        ConversationHandler.getInstance().runRequestAsync(contextJson);
    }

    public static void register(long intervalSeconds) {
        ClientTickEvents.START_CLIENT_TICK.register(new EventOrchestrator(intervalSeconds));
    }
}
