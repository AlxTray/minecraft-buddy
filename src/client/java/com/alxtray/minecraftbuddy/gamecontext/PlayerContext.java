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

    public PlayerContext(float health, float maxHealth, float hunger, float saturation, int air, int armour, int experience, int age, String heldItemName, InventoryContext inventory, float x, float y, float z, float yaw, float pitch, float velocityX, float velocityY, float velocityZ, boolean isRiding, boolean isSneaking, boolean isSubmerged, boolean isFlying, boolean isAlive, boolean isCrawling, boolean isInFluid, boolean isInLava, boolean isJumping, boolean isSprinting, boolean isSwimming, boolean isUsingSpyglass, boolean isClimbingLadder, int sleepTimer, String gameMode, List<StatusEffectContext> effects, EntityContext attackingEntity, EntityContext attackedEntity) {
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
        this.attackingEntity = attackingEntity;
        this.attackedEntity = attackedEntity;
    }

    public int getSleepTimer() {
        return sleepTimer;
    }

    public float getHunger() {
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

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getHealth() {
        return health;
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public float getVelocityZ() {
        return velocityZ;
    }

    public boolean isRiding() {
        return isRiding;
    }

    public boolean isSubmerged() {
        return isSubmerged;
    }

    public boolean isSneaking() {
        return isSneaking;
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

    public boolean isUsingSpyglass() {
        return isUsingSpyglass;
    }

    public boolean isClimbingLadder() {
        return isClimbingLadder;
    }

    public String getGameMode() {
        return gameMode;
    }

    public List<StatusEffectContext> getEffects() {
        return effects;
    }

    public EntityContext getAttackingEntity() {
        return attackingEntity;
    }

    public EntityContext getAttackedEntity() {
        return attackedEntity;
    }
}
