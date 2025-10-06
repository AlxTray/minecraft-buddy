package com.alxtray.minecraftbuddy.gamecontext;

public class InventoryItemContext {
    public String name;
    public int count;

    public InventoryItemContext(String name, int count) {
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
