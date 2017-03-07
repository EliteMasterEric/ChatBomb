package com.mastereric.chatbomb.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityChatBombPrimed extends EntityTNTPrimed {
    public EntityChatBombPrimed(World worldIn) {
        super(worldIn);
    }

    public EntityChatBombPrimed(World worldIn, double x, double y, double z, EntityLivingBase igniter) {
        super(worldIn, x, y, z, igniter);
    }
}
