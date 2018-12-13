package com.mastereric.chatbomb.common.entity;

import com.mastereric.chatbomb.ChatBomb;
import com.mastereric.chatbomb.util.LogUtility;
import com.sun.istack.internal.Nullable;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class PrimedChatBombEntity extends Entity {
    private static final int FUSE = 80;

    @Nullable
    private LivingEntity causingEntity;
    private int fuseTimer;

    public PrimedChatBombEntity(World var1) {
        super(ChatBomb.Entities.CHAT_BOMB, var1);
        this.field_6033 = true;
        this.fireImmune = true;
        this.setSize(0.98F, 0.98F);
    }

    public PrimedChatBombEntity(World var1, double posX, double posY, double var6, @Nullable LivingEntity causingEntity) {
        this(var1);
        this.setPosition(posX, posY, var6);
        float randomVariance = (float)(Math.random() * 6.2831854820251465D);
        this.velocityX = (double)(-((float)Math.sin((double)randomVariance)) * 0.02F);
        this.velocityY = 0.20000000298023224D;
        this.velocityZ = (double)(-((float)Math.cos((double)randomVariance)) * 0.02F);
        this.prevX = posX;
        this.prevY = posY;
        this.prevZ = var6;
        this.causingEntity = causingEntity;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 80);
    }

    protected boolean method_5658() {
        return false;
    }

    public boolean doesCollide() {
        return !this.invalid;
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag var1) {

    }

    @Override
    protected void writeCustomDataToTag(CompoundTag var1) {

    }

    public void update() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        if (!this.isUnaffectedByGravity()) {
            this.velocityY -= 0.03999999910593033D;
        }

        this.move(MovementType.SELF, this.velocityX, this.velocityY, this.velocityZ);
        this.velocityX *= 0.9800000190734863D;
        this.velocityY *= 0.9800000190734863D;
        this.velocityZ *= 0.9800000190734863D;
        if (this.onGround) {
            this.velocityX *= 0.699999988079071D;
            this.velocityZ *= 0.699999988079071D;
            this.velocityY *= -0.5D;
        }

        if (this.age >= FUSE) {
            LogUtility.info("FuseTimer elapsed.", );
            this.invalidate();
            if (!this.world.isRemote) {
                this.explode();
            }
        } else {
            this.method_5713();
            this.world.method_8406(ParticleTypes.SMOKE, this.x, this.y + 0.5D, this.z, 0.0D, 0.0D, 0.0D);
        }

    }

    private void explode() {
        this.world.createExplosion(this, this.x, this.y + (double)(this.height / 16.0F), this.z, 4.0F, true);
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    public float getEyeHeight() {
        return 0.0F;
    }
}
