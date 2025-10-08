package com.alxtray.minecraftbuddy.gamecontext;

public class StatusEffectContext {
    public String name;
    public int amplifier;
    public float timeRemaining;

    public StatusEffectContext(String name, int amplifier, float timeRemaining) {
        this.name = name;
        this.amplifier = amplifier;
        this.timeRemaining = timeRemaining;
    }

    public String getName() {
        return name;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public float getCount() {
        return timeRemaining;
    }
}
