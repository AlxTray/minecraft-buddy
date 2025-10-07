package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class EntityContext {
    public String name;
    public ItemContext heldItem;
    public List<String> armour;

    public EntityContext(String name, ItemContext heldItem, List<String> armour) {
        this.name = name;
        this.heldItem = heldItem;
        this.armour = armour;
    }

    public String getName() {
        return name;
    }

    public ItemContext getHeldItem() {
        return heldItem;
    }

    public List<String> getArmour() {
        return armour;
    }
}
