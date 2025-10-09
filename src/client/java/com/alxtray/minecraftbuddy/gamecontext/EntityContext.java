package com.alxtray.minecraftbuddy.gamecontext;

public class EntityContext {
    public String name;
    public ItemContext heldItem;
    public float distanceFromPlayer;

    public EntityContext(String name, ItemContext heldItem, float distanceFromPlayer) {
        this.name = name;
        this.heldItem = heldItem;
        this.distanceFromPlayer = distanceFromPlayer;
    }

    public String getName() {
        return name;
    }

    public ItemContext getHeldItem() {
        return heldItem;
    }

    public float getDistanceFromPlayer() {
        return distanceFromPlayer;
    }
}
