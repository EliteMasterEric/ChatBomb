package com.mastereric.chatbomb.util;

import mcjty.lib.compat.CompatItemHandler;
import mcjty.lib.tools.ItemStackTools;
import mcjty.lib.tools.WorldTools;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemUtility {
    public static void dropItemsFromInventory(World world, BlockPos pos, CompatItemHandler inventoryItemHandler){
        for (int i = 0; i < inventoryItemHandler.getSlots(); i++) {
            ItemStack stack = inventoryItemHandler.getStackInSlot(i);
            if(!ItemStackTools.isEmpty(stack) && ItemStackTools.getStackSize(stack) != 0){
                float dX = world.rand.nextFloat()*0.8F+0.1F;
                float dY = world.rand.nextFloat()*0.8F+0.1F;
                float dZ = world.rand.nextFloat()*0.8F+0.1F;
                EntityItem entityItem = new EntityItem(world, pos.getX()+dX, pos.getY()+dY, pos.getZ()+dZ, stack.copy());
                float factor = 0.05F;
                entityItem.motionX = world.rand.nextGaussian()*factor;
                entityItem.motionY = world.rand.nextGaussian()*factor+0.2F;
                entityItem.motionZ = world.rand.nextGaussian()*factor;
                WorldTools.spawnEntity(world, entityItem);
            }
        }
    }
}
