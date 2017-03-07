package com.mastereric.chatbomb.common.blocks;

import mcjty.lib.compat.CompatBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChatBomb extends CompatBlock {
    public static final PropertyBool EXPLODE = PropertyBool.create("explode");

    public BlockChatBomb() {
        super(Material.TNT);
    }

    public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter) {
        if (!worldIn.isRemote) {
            if (((Boolean)state.getValue(EXPLODE)).booleanValue()) {
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
                worldIn.spawnEntity(entitytntprimed);
                worldIn.playSound((EntityPlayer)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }
}
