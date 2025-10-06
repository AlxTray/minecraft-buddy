package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class InventoryContext {
    public String selectedItemName;
    public List<InventoryItemContext> hotbarItems;
    public List<InventoryItemContext> inventoryItems;
    public InventoryItemContext swapSlotItem;
    public List<InventoryItemContext> enderChestItems;
}
