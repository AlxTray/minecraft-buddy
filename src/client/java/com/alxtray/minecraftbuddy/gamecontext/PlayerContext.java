package com.alxtray.minecraftbuddy.gamecontext;

import java.util.List;

public class PlayerContext {
    public float health;
    public float maxHealth;
    public int hunger;
    public float saturation;
    public int air;
    public int armour;
    public int experience;
    public int age;
    public String heldItemName;
    public InventoryContext inventory;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;
    public double velocityX;
    public double velocityY;
    public double velocityZ;
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

    public PlayerContext(float health, float maxHealth, int hunger, float saturation, int air, int armour, int experience, int age, String heldItemName, InventoryContext inventory, double x, double y, double z, float yaw, float pitch, double velocityX, double velocityY, double velocityZ, boolean isRiding, boolean isSneaking, boolean isSubmerged, boolean isFlying, boolean isAlive, boolean isCrawling, boolean isInFluid, boolean isInLava, boolean isJumping, boolean isSprinting, boolean isSwimming, boolean isUsingSpyglass, boolean isClimbingLadder, int sleepTimer, String gameMode, List<StatusEffectContext> effects) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.hunger = hunger;
        this.saturation = saturation;
        this.air = air;
        this.armour = armour;
        this.experience = experience;
        this.age = age;
        this.heldItemName = heldItemName;
        this.inventory = inventory;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.isRiding = isRiding;
        this.isSneaking = isSneaking;
        this.isSubmerged = isSubmerged;
        this.isFlying = isFlying;
        this.isAlive = isAlive;
        this.isCrawling = isCrawling;
        this.isInFluid = isInFluid;
        this.isInLava = isInLava;
        this.isJumping = isJumping;
        this.isSprinting = isSprinting;
        this.isSwimming = isSwimming;
        this.isUsingSpyglass = isUsingSpyglass;
        this.isClimbingLadder = isClimbingLadder;
        this.sleepTimer = sleepTimer;
        this.gameMode = gameMode;
        this.effects = effects;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public int getHunger() {
        return hunger;
    }

    public float getSaturation() {
        return saturation;
    }

    public int getAir() {
        return air;
    }

    public int getArmour() {
        return armour;
    }

    public int getExperience() {
        return experience;
    }

    public int getAge() {
        return age;
    }

    public String getHeldItemName() {
        return heldItemName;
    }

    public InventoryContext getInventory() {
        return inventory;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getVelocityZ() {
        return velocityZ;
    }

    public boolean isRiding() {
        return isRiding;
    }

    public boolean isSneaking() {
        return isSneaking;
    }

    public boolean isSubmerged() {
        return isSubmerged;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isCrawling() {
        return isCrawling;
    }

    public boolean isInFluid() {
        return isInFluid;
    }

    public boolean isInLava() {
        return isInLava;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isSprinting() {
        return isSprinting;
    }

    public boolean isSwimming() {
        return isSwimming;
    }

    public boolean isClimbingLadder() {
        return isClimbingLadder;
    }

    public boolean isUsingSpyglass() {
        return isUsingSpyglass;
    }

    public int getSleepTimer() {
        return sleepTimer;
    }

    public String getGameMode() {
        return gameMode;
    }

    public List<StatusEffectContext> getEffects() {
        return effects;
    }
}
