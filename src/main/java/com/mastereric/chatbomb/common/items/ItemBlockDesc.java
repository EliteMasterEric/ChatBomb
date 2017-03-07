package com.mastereric.chatbomb.common.items;

import com.mastereric.chatbomb.util.LangUtility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBlockDesc extends ItemBlock {
    public ItemBlockDesc(Block block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(LangUtility.getTranslation(this.getUnlocalizedName() + ".desc"));
    }

}
