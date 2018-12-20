package com.mastereric.chatbomb.client.network;

import com.mastereric.chatbomb.common.entity.PrimedChatBombEntity;
import com.mastereric.chatbomb.util.LogUtility;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.networking.PacketContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.util.PacketByteBuf;

@Environment(EnvType.CLIENT)
public abstract class ChatBombNetworkHandler {
    public static void handlePrimedChatBombEntitySpawnClientPacket(PacketContext packetContext, PacketByteBuf packetByteBuf) {
        PrimedChatBombEntitySpawnClientPacket packet
                = new PrimedChatBombEntitySpawnClientPacket(packetByteBuf);

        LogUtility.debug("Client spawning server-designated Chat Bomb entity, at pos (%f, %f, %f)",
                packet.getX(), packet.getY(), packet.getZ());

        // Copied logic from net.minecraft.client.network.ClientPlayNetworkHandler.onEntitySpawn
        PrimedChatBombEntity entity = new PrimedChatBombEntity(packetContext.getPlayer().getEntityWorld(),
                packet.getX(), packet.getY(), packet.getZ(), null);
        entity.pitch = packet.getPitch();
        entity.yaw = packet.getYaw();
        //entity.getParts() is null
        entity.setEntityId(packet.getId());
        entity.setUuid(packet.getUuid());
        entity.setVelocityClient((double)packet.getVelocityX() / 8000.0D,
                (double)packet.getVelocityY() / 8000.0D,
                (double)packet.getVelocityz() / 8000.0D);

        // This unmapped function adds the entity to the Client's world.
        MinecraftClient.getInstance().world.method_2942(packet.getId(), entity);
    }
}
