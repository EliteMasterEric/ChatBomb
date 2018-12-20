package com.mastereric.chatbomb;

import com.mastereric.chatbomb.client.PrimedChatBombEntityRenderer;
import com.mastereric.chatbomb.client.network.ChatBombNetworkHandler;
import com.mastereric.chatbomb.client.network.PrimedChatBombEntitySpawnClientPacket;
import com.mastereric.chatbomb.common.blocks.ChatBombBlock;
import com.mastereric.chatbomb.common.entity.PrimedChatBombEntity;
import com.mastereric.chatbomb.common.entity.damage.ChatBombDamageSource;
import com.mastereric.chatbomb.common.items.DescBlockItem;
import com.mastereric.chatbomb.util.LogUtility;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.networking.CustomPayloadPacketRegistry;
import net.fabricmc.fabric.networking.PacketContext;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import java.util.function.BiConsumer;

public class ChatBomb implements ModInitializer {

    public abstract static class Blocks {

        public static Block CHAT_BOMB;

        static void initializeBlocks() {
            LogUtility.info("Initializing blocks...");
            CHAT_BOMB = registerBlock(new ChatBombBlock(), "chatbomb", ItemGroup.REDSTONE);
        }

        static Block registerBlock(Block block, String name, ItemGroup itemGroup) {
            // Register the block.
            Registry.register(Registry.BLOCK, new Identifier(Reference.MOD_ID, name), block);

            // Create and register the BlockItem.
            DescBlockItem blockItem = new DescBlockItem(block, new Item.Settings().itemGroup(itemGroup));
            blockItem.registerBlockItemMap(Item.BLOCK_ITEM_MAP, blockItem);
            Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, name), blockItem);

            return block;
        }
    }

    public abstract static class Entities {
        public static EntityType CHAT_BOMB;

        static void initializeEntities() {
            LogUtility.info("Initializing entities...");
            CHAT_BOMB = registerEntity(PrimedChatBombEntity.class, "chatbomb",
                    ChatBombNetworkHandler::handlePrimedChatBombEntitySpawnClientPacket,
                    160, 10, true);
        }

        public static void initializeEntityRenderers() {
            // Entity renderer code should only be run on the Client.
            LogUtility.info("Initializing entity renderers...");
            registerEntityRenderer(PrimedChatBombEntity.class, (ctx, ctx2) -> new PrimedChatBombEntityRenderer(ctx));
        }

        static EntityType registerEntity(Class<? extends Entity> entityClass, String name,
                                         BiConsumer<PacketContext, PacketByteBuf> packetHandler) {
            // Use this only for living entities that don't require tracking.
            return registerEntity(entityClass, name, packetHandler, -1, -1, false);
        }

        static EntityType registerEntity(Class<? extends Entity> entityClass, String name,
                                         BiConsumer<PacketContext, PacketByteBuf> packetHandler,
                                         int trackingDistance, int updateIntervalTicks, boolean alwaysUpdateVelocity) {
            // Manually enabling tracking is required for non-living entities.
            // trackingDistance represents how close the player must be for the entity to be tracked.
            //
            // updateInvervalTicks represents how many ticks between updates there are.
            boolean tracking = trackingDistance != -1 || updateIntervalTicks != -1 || alwaysUpdateVelocity;

            FabricEntityTypeBuilder builder = FabricEntityTypeBuilder.create(entityClass);
            // Entity tracking. Reference net.minecraft.server.network.EntityTracker.add(Entity) for correct logic.
            if (tracking) {
                builder.trackable(trackingDistance, updateIntervalTicks, alwaysUpdateVelocity);
            }
            EntityType<?> entityType = builder.build();
            // Currently, Fabric requires networking logic for non-LivingEntity entities to be done manually.
            if (tracking) {
                EntityTrackingRegistry.INSTANCE.registerSpawnPacketProvider(
                        entityType, PrimedChatBombEntitySpawnClientPacket::new);
                CustomPayloadPacketRegistry.CLIENT.register(
                        new Identifier(Reference.MOD_ID, "spawn_"+name), packetHandler);
            }
            Registry.register(Registry.ENTITY_TYPE, new Identifier(Reference.MOD_ID, name), entityType);
            return entityType;
        }

        static void registerEntityRenderer(Class<? extends Entity> entityClass, EntityRendererRegistry.Factory factory) {
            EntityRendererRegistry.INSTANCE.register(entityClass, factory);
        }
    }

    /**
     * Custom damage source, used for Chat Bomb messages.
     */
    public static final DamageSource CHATBOMB_DAMAGE = new ChatBombDamageSource();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        LogUtility.info("Initializing Chat Bomb %s.", Reference.MOD_VERSION);
        LogUtility.printLogLevel();

        Blocks.initializeBlocks();
        Entities.initializeEntities();
	}
}
