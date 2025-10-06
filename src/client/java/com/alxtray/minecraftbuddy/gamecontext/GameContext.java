package com.alxtray.minecraftbuddy.gamecontext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameContext {
    @JsonProperty("Player Information")
    public PlayerContext playerContext;
    @JsonProperty("Environment Information")
    public EnvironmentContext environmentContext;

    public GameContext(PlayerContext playerContext, EnvironmentContext environmentContext) {
        this.playerContext = playerContext;
        this.environmentContext = environmentContext;
    }

    public PlayerContext getPlayerContext() {
        return playerContext;
    }

    public EnvironmentContext getEnvironmentContext() {
        return environmentContext;
    }
}
