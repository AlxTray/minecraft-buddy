package com.alxtray.minecraftbuddy.gamecontext;

public class StatusEffectContext {
    public String name;
    public float timeRemaining;

    public StatusEffectContext(String name, int timeRemaining) {
        this.name = name;
        this.timeRemaining = timeRemaining;
    }

    public String getName() {
        return name;
    }

    public float getCount() {
        return timeRemaining;
    }
}
