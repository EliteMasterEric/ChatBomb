package com.mastereric.chatbomb.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModRecipes {
    public static void initializeCraftingRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockChatBomb),
				"WGW", "GNG", "WGW",
				'W', new ItemStack(Blocks.WOOL),
				'G', new ItemStack(Items.GUNPOWDER),
                'N', new ItemStack(Blocks.NOTEBLOCK));
	}
}