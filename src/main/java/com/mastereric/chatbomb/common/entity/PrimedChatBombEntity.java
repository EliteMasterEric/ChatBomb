package com.mastereric.chatbomb.common.entity;

import com.mastereric.chatbomb.ChatBomb;
import com.mastereric.chatbomb.util.LogUtility;
import com.sun.istack.internal.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import sun.rmi.runtime.Log;

public class PrimedChatBombEntity extends Entity
{
    private static final TrackedData<Integer> FUSE;
    @Nullable
    private LivingEntity causingEntity;
    private int fuseTimer;

    public PrimedChatBombEntity(final World world) {
        super(ChatBomb.Entities.CHAT_BOMB, world);
        this.fuseTimer = 80;
        this.field_6033 = true;
        this.fireImmune = true;
        this.setSize(0.98f, 0.98f);
    }

    public PrimedChatBombEntity(final World world, final double x, final double y, final double z, @Nullable final LivingEntity vLivingEntity9) {
        this(world);
        this.setPosition(x, y, z);
        final float vFloat10 = (float)(Math.random() * 6.2831854820251465);
        this.velocityX = -(float)Math.sin(vFloat10) * 0.02f;
        this.velocityY = 0.20000000298023224;
        this.velocityZ = -(float)Math.cos(vFloat10) * 0.02f;
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = vLivingEntity9;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(PrimedChatBombEntity.FUSE, 80);
    }

    @Override
    public boolean doesCollide() {
        return !this.invalid;
    }

    @Override
    public void update() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        if (!this.isUnaffectedByGravity()) {
            this.velocityY -= 0.03999999910593033;
        }
        this.move(MovementType.SELF, this.velocityX, this.velocityY, this.velocityZ);
        this.velocityX *= 0.9800000190734863;
        this.velocityY *= 0.9800000190734863;
        this.velocityZ *= 0.9800000190734863;
        if (this.onGround) {
            this.velocityX *= 0.699999988079071;
            this.velocityZ *= 0.699999988079071;
            this.velocityY *= -0.5;
        }
        //LogUtility.info("Fuse timer: %d", this.fuseTimer);
        --this.fuseTimer;
        if (this.fuseTimer <= 0) {
            //LogUtility.info("Exploding at age %d.", this.age);
            this.invalidate();
            if (!this.world.isRemote) {
                this.explode();
            }
        }
        else {
            this.method_5713();
            this.world.method_8406(ParticleTypes.SMOKE, this.x, this.y + 0.5, this.z, 0.0, 0.0, 0.0);
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.x, this.y + this.height / 16.0f, this.z, 4.0f, true);
    }

    @Override
    protected void writeCustomDataToTag(final CompoundTag vCompoundTag2) {
        vCompoundTag2.putShort("Fuse", (short)this.getFuseTimer());
    }

    @Override
    protected void readCustomDataFromTag(final CompoundTag vCompoundTag2) {
        this.setFuse(vCompoundTag2.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    @Override
    public float getEyeHeight() {
        return 0.0f;
    }

    public void setFuse(final int vInteger2) {
        this.dataTracker.set(PrimedChatBombEntity.FUSE, vInteger2);
        this.fuseTimer = vInteger2;
    }

    @Override
    public void onTrackedDataSet(final TrackedData<?> vTrackedData2) {
        if (PrimedChatBombEntity.FUSE.equals(vTrackedData2)) {
            this.fuseTimer = this.getFuse();
        }
    }

    public int getFuse() {
        return this.dataTracker.<Integer>get(PrimedChatBombEntity.FUSE);
    }

    public int getFuseTimer() {
        return this.fuseTimer;
    }

    static {
        FUSE = DataTracker.registerData(PrimedChatBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
