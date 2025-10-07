package com.alxtray.minecraftbuddy.gamecontext;

public class ItemContext {
    public String name;
    public int count;

    public ItemContext(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
