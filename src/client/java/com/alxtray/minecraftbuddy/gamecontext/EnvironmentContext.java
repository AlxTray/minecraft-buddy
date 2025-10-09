package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class EnvironmentContext {
    public List<EntityContext> mobsCloseToPLayer;
    public boolean isRaining;
    public long timeOfDay;
    public String biome;

    public EnvironmentContext(List<EntityContext> mobsCloseToPLayer, boolean isRaining, long timeOfDay, String biome) {
        this.mobsCloseToPLayer = mobsCloseToPLayer;
        this.isRaining = isRaining;
        this.timeOfDay = timeOfDay;
        this.biome = biome;
    }

    public List<EntityContext> getMobsCloseToPLayer() {
        return mobsCloseToPLayer;
    }

    public boolean getIsRaining() {
        return isRaining;
    }

    public long getTimeOfDay() {
        return timeOfDay;
    }

    public String getBiome() {
        return biome;
    }
}
