package com.mastereric.chatbomb.init;

import com.mastereric.chatbomb.ChatBomb;
import com.mastereric.chatbomb.Reference;
import com.mastereric.chatbomb.client.render.entity.RenderChatBombPrimed;
import com.mastereric.chatbomb.common.entity.EntityChatBombPrimed;
import mcjty.lib.tools.EntityTools;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {
    public static void initializeEntities() {
        registerEntity(new ResourceLocation(Reference.MOD_ID, Reference.NAME_ENTITY_CHAT_BOMB),
                EntityChatBombPrimed.class, Reference.NAME_ENTITY_CHAT_BOMB,
                160, 10, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initializeEntityModels() {
        registerEntityModel(EntityChatBombPrimed.class, RenderChatBombPrimed.FACTORY);
    }

    private static int id = 1;

    private static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityTools.registerModEntity(registryName, entityClass, entityName, id++, ChatBomb.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private static <T extends Entity> void registerEntityModel(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}
