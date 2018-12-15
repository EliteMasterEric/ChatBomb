package com.mastereric.chatbomb.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipOptions;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BlockItem;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class DescBlockItem extends BlockItem {
    public DescBlockItem(Block var1, Settings var2) {
        super(var1, var2);
    }

    @Override
    public void buildTooltip(ItemStack itemStack, World world, List<TextComponent> descList, TooltipOptions tooltipOptions) {
        super.buildTooltip(itemStack, world, descList, tooltipOptions);
        descList.add(new TranslatableTextComponent(this.getTranslationKey() + ".desc"));
    }
}
