package com.mastereric.chatbomb.client.network;

import com.mastereric.chatbomb.Reference;
import com.mastereric.chatbomb.common.entity.PrimedChatBombEntity;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.packet.CustomPayloadClientPacket;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

/**
 * Packet sent to the client to tell it to spawn a copy of the entity on the server.
 * This is done for you for EntityLiving, and hopefully Fabric will do it automatically for all entities,
 * but for now, non-living entities require custom networking code.
 */
public class PrimedChatBombEntitySpawnClientPacket extends CustomPayloadClientPacket {
    private int id;
    private UUID uuid;
    private double x;
    private double y;
    private double z;
    private int velocityX;
    private int velocityY;
    private int velocityZ;
    private int pitch;
    private int yaw;
    private Identifier channel2;

    public PrimedChatBombEntitySpawnClientPacket(PacketByteBuf packetByteBuf) {
        super(new Identifier(Reference.MOD_ID, "spawn_chatbomb"), packetByteBuf);
        this.read(packetByteBuf);
    }

    public PrimedChatBombEntitySpawnClientPacket(Entity entity) {
        this((PrimedChatBombEntity) entity);
    }

    public PrimedChatBombEntitySpawnClientPacket(PrimedChatBombEntity primedChatBombEntity) {
        this.channel2 = new Identifier(Reference.MOD_ID, "spawn_chatbomb");
        this.id = primedChatBombEntity.getEntityId();
        this.uuid = primedChatBombEntity.getUuid();
        this.x = primedChatBombEntity.x;
        this.y = primedChatBombEntity.y;
        this.z = primedChatBombEntity.z;
        this.pitch = MathHelper.floor(primedChatBombEntity.pitch * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(primedChatBombEntity.yaw * 256.0F / 360.0F);
        this.velocityX = (int)(MathHelper.clamp(primedChatBombEntity.velocityX, -3.9D, 3.9D) * 8000.0D);
        this.velocityY = (int)(MathHelper.clamp(primedChatBombEntity.velocityY, -3.9D, 3.9D) * 8000.0D);
        this.velocityZ = (int)(MathHelper.clamp(primedChatBombEntity.velocityZ, -3.9D, 3.9D) * 8000.0D);
    }

    @Override
    public Identifier getChannel() {
        return this.channel2;
    }

    public void read(PacketByteBuf var1) {
        this.id = var1.readVarInt();
        this.uuid = var1.readUuid();
        this.x = var1.readDouble();
        this.y = var1.readDouble();
        this.z = var1.readDouble();
        this.velocityX = var1.readShort();
        this.velocityY = var1.readShort();
        this.velocityZ = var1.readShort();
        this.pitch = var1.readByte();
        this.yaw = var1.readByte();
    }

    public void write(PacketByteBuf var1) {
        var1.writeVarInt(this.id);
        var1.writeUuid(this.uuid);
        var1.writeDouble(this.x);
        var1.writeDouble(this.y);
        var1.writeDouble(this.z);
        var1.writeShort(this.velocityX);
        var1.writeShort(this.velocityY);
        var1.writeShort(this.velocityZ);
        var1.writeByte(this.pitch);
        var1.writeByte(this.yaw);
    }

    @Override
    public PacketByteBuf getData() {
        PacketByteBuf buf = (new PacketByteBuf(Unpooled.buffer()));
        write(buf);
        return buf;
    }

    @Environment(EnvType.CLIENT)
    public int getId() {
        return this.id;
    }

    @Environment(EnvType.CLIENT)
    public UUID getUuid() {
        return this.uuid;
    }

    @Environment(EnvType.CLIENT)
    public double getX() {
        return this.x;
    }

    @Environment(EnvType.CLIENT)
    public double getY() {
        return this.y;
    }

    @Environment(EnvType.CLIENT)
    public double getZ() {
        return this.z;
    }

    @Environment(EnvType.CLIENT)
    public int getVelocityX() {
        return this.velocityX;
    }

    @Environment(EnvType.CLIENT)
    public int getVelocityY() {
        return this.velocityY;
    }

    @Environment(EnvType.CLIENT)
    public int getVelocityz() {
        return this.velocityZ;
    }

    @Environment(EnvType.CLIENT)
    public int getPitch() {
        return this.pitch;
    }

    @Environment(EnvType.CLIENT)
    public int getYaw() {
        return this.yaw;
    }

    public void setVelocityX(int var1) {
        this.velocityX = var1;
    }

    public void setVelocityY(int var1) {
        this.velocityY = var1;
    }

    public void setVelocityZ(int var1) {
        this.velocityZ = var1;
    }
}
