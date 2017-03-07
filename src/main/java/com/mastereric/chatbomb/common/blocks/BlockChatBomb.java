package com.mastereric.chatbomb.common.blocks;

import com.mastereric.chatbomb.common.tile.TileChatBomb;
import mcjty.lib.compat.CompatBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockChatBomb extends CompatBlock implements ITileEntityProvider {

    public BlockChatBomb() {
        super(Material.TNT);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
    }

    public void trigger(World worldIn, BlockPos pos, EntityLivingBase igniter, String triggerWord) {
        TileEntity entity = worldIn.getTileEntity(pos);
        if (entity instanceof TileChatBomb) {
            if (!((TileChatBomb) entity).hasTriggered()) {
                ((TileChatBomb) entity).trigger(igniter, triggerWord);
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileChatBomb();
    }
}
