package com.alxtray.minecraftbuddy.gamecontext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameContext {
    @JsonProperty("Player Information")
    public PlayerContext playerContext;
    @JsonProperty("Environment Information")
    public EnvironmentContext environmentContext;
    @JsonProperty("Minecraft Day")
    public int day;
    @JsonProperty("Response Given From This Context")
    public int response;

    public GameContext(PlayerContext playerContext, EnvironmentContext environmentContext, int day, int response) {
        this.playerContext = playerContext;
        this.environmentContext = environmentContext;
        this.day = day;
        this.response = response;
    }

    public PlayerContext getPlayerContext() {
        return playerContext;
    }

    public EnvironmentContext getEnvironmentContext() {
        return environmentContext;
    }

    public int getDay() {
        return day;
    }

    public int getResponse() {
        return response;
    }
}
