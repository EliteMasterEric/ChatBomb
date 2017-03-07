package com.mastereric.chatbomb.init;

import com.mastereric.chatbomb.ChatBomb;
import com.mastereric.chatbomb.Reference;
import com.mastereric.chatbomb.common.blocks.BlockChatBomb;
import com.mastereric.chatbomb.common.items.ItemBlockDesc;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ModBlocks {
    public static Block blockChatBomb;

    public static ItemBlock itemBlockChatBomb;

    public static void initializeBlocks() {
        blockChatBomb = new BlockChatBomb();
        itemBlockChatBomb = new ItemBlockDesc(blockChatBomb);
        registerBlock(blockChatBomb, Reference.NAME_BLOCK_CHAT_BOMB, itemBlockChatBomb);
    }

    @SideOnly(Side.CLIENT)
    public static void initializeBlockModels() {
        // Run this on the ClientProxy after running initializeItems.
        registerBlockModel(blockChatBomb);
    }

    private static void registerBlock(Block block, String registryName) {
        registerBlock(block, registryName, new ItemBlock(block), true);
    }

    private static void registerBlock(Block block, String registryName, boolean inCreativeTab) {
        registerBlock(block, registryName, new ItemBlock(block), inCreativeTab);
    }

    private static void registerBlock(Block block, String registryName, ItemBlock itemBlock) {
        registerBlock(block, registryName, itemBlock, true);
    }

    private static void registerBlock(Block block, String registryName, ItemBlock itemBlock, boolean inCreativeTab) {
        // Set the registry name.
        block.setRegistryName(Reference.MOD_ID, registryName);
        block.setUnlocalizedName(Reference.MOD_ID + "." + registryName);
        // Add to the game registry.
        GameRegistry.register(block);
        GameRegistry.register(itemBlock, block.getRegistryName());

        if (inCreativeTab)
            block.setCreativeTab(ChatBomb.creativeTab);

        System.out.println("Registered block ~ "+block.getRegistryName());

    }

    @SideOnly(Side.CLIENT)
    private static void registerBlockModel(Block block) {
        // Function overloads make everything simpler.
        registerBlockModel(block, 0);
    }

    @SideOnly(Side.CLIENT)
    private static void registerBlockModel(Block block, int metadata) {
        // Register the item model.
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata,
                new ModelResourceLocation(block.getRegistryName(), "inventory"));

        System.out.println("Registered block model ~ " + block.getRegistryName() + " ~ " + block.getUnlocalizedName());
    }

    private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        GameRegistry.registerTileEntity(tileEntityClass, id);
    }
}