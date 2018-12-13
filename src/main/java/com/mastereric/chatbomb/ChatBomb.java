package com.mastereric.chatbomb;

import com.mastereric.chatbomb.client.PrimedChatBombEntityRenderer;
import com.mastereric.chatbomb.common.blocks.ChatBombBlock;
import com.mastereric.chatbomb.common.entity.PrimedChatBombEntity;
import com.mastereric.chatbomb.common.items.DescBlockItem;
import com.mastereric.chatbomb.util.LogUtility;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
            CHAT_BOMB = registerEntity(PrimedChatBombEntity.class,
                    new PrimedChatBombEntityRenderer.PrimedChatBombEntityRendererFactory(), "chatbomb");
        }

        static EntityType registerEntity(Class<? extends Entity> entityClass,
                                         EntityRendererRegistry.Factory rendererFactory, String name) {
            EntityRendererRegistry.INSTANCE.register(entityClass, rendererFactory);
            return Registry.register(Registry.ENTITY_TYPE, new Identifier(Reference.MOD_ID, name),
                    FabricEntityTypeBuilder.create(entityClass).build(name));
        }
    }

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        LogUtility.info("Initializing Chat Bomb %s.", Reference.MOD_VERSION);

        Blocks.initializeBlocks();
        Entities.initializeEntities();
	}
}
