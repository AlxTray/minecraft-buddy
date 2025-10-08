package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class EnvironmentContext {
    public List<EntityContext> mobsCloseToPLayer;
    public String weather;
    public String timeOfDay;
    public String biome;

    public EnvironmentContext(List<EntityContext> mobsCloseToPLayer, String weather, String timeOfDay, String biome) {
        this.mobsCloseToPLayer = mobsCloseToPLayer;
        this.weather = weather;
        this.timeOfDay = timeOfDay;
        this.biome = biome;
    }

    public List<EntityContext> getMobsCloseToPLayer() {
        return mobsCloseToPLayer;
    }

    public String getWeather() {
        return weather;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getBiome() {
        return biome;
    }
}
