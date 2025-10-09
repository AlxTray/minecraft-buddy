package com.alxtray.minecraftbuddy.gamecontext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameContext {
    @JsonProperty("Player Information")
    public PlayerContext playerContext;
    @JsonProperty("Environment Information")
    public EnvironmentContext environmentContext;
    @JsonProperty("How Long World Has Existed")
    public long day;

    public GameContext(PlayerContext playerContext, EnvironmentContext environmentContext, long day) {
        this.playerContext = playerContext;
        this.environmentContext = environmentContext;
        this.day = day;
    }

    public PlayerContext getPlayerContext() {
        return playerContext;
    }

    public EnvironmentContext getEnvironmentContext() {
        return environmentContext;
    }

    public long getDay() {
        return day;
    }
}
