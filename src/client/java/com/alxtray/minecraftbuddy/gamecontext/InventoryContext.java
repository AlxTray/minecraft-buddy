package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class InventoryContext {
    public String selectedItemName;
    public List<ItemContext> hotbarItems;
    public List<ItemContext> inventoryItems;
    public ItemContext swapSlotItem;
    public List<ItemContext> enderChestItems;

    public InventoryContext(String selectedItemName, List<ItemContext> hotbarItems, List<ItemContext> inventoryItems, ItemContext swapSlotItem, List<ItemContext> enderChestItems) {
        this.selectedItemName = selectedItemName;
        this.hotbarItems = hotbarItems;
        this.inventoryItems = inventoryItems;
        this.swapSlotItem = swapSlotItem;
        this.enderChestItems = enderChestItems;
    }

    public String getSelectedItemName() {
        return selectedItemName;
    }

    public List<ItemContext> getHotbarItems() {
        return hotbarItems;
    }

    public List<ItemContext> getInventoryItems() {
        return inventoryItems;
    }

    public ItemContext getSwapSlotItem() {
        return swapSlotItem;
    }

    public List<ItemContext> getEnderChestItems() {
        return enderChestItems;
    }
}
