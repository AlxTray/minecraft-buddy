package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class PlayerContext {
    public float health;
    public float maxHealth;
    public float hunger;
    public float saturation;
    public int air;
    public int armour;
    public int experience;
    public int age;
    public String heldItemName;
    public InventoryContext inventory;
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float pitch;
    public float velocityX;
    public float velocityY;
    public float velocityZ;
    public boolean isRiding;
    public boolean isSneaking;
    public boolean isSubmerged;
    public boolean isFlying;
    public boolean isAlive;
    public boolean isCrawling;
    public boolean isInFluid;
    public boolean isInLava;
    public boolean isJumping;
    public boolean isSprinting;
    public boolean isSwimming;
    public boolean isUsingSpyglass;
    public boolean isClimbingLadder;
    public int sleepTimer;
    public String gameMode;
    public List<StatusEffectContext> effects;
    public EntityContext attackingEntity;
    public EntityContext attackedEntity;
}
