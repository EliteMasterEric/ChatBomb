package com.mastereric.chatbomb.common.tile;

import com.mastereric.chatbomb.Reference;
import com.mastereric.chatbomb.common.entity.EntityChatBombPrimed;
import com.mastereric.chatbomb.init.ModConfig;
import com.mastereric.chatbomb.util.LangUtility;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileChatBomb extends TileEntity implements ITickable {
    private static final int COUNTER_DEFAULT = 4;
    private int shouldExplode = -1;

    private EntityPlayerMP igniter;
    private String triggerWord;

    public void trigger(EntityLivingBase igniter, String triggerWord) {
        this.shouldExplode = COUNTER_DEFAULT;
        if (igniter instanceof EntityPlayerMP)
            this.igniter = (EntityPlayerMP) igniter;
        this.triggerWord = triggerWord;
    }

    public boolean hasTriggered() {
        return this.shouldExplode != -1;
    }

    @Override
    public void update() {
        if (this.shouldExplode > 0)
            this.shouldExplode--;
        if (this.shouldExplode == 0)
            this.explode();
    }

    public void explode() {
        if (!getWorld().isRemote) {
            if(ModConfig.VERBOSE && igniter != null) {
                igniter.sendStatusMessage(LangUtility.getChatMessage(LangUtility.getTranslationRaw(Reference.LANG_CHAT_BOMB_NAME),
                        LangUtility.getTranslationFormat(Reference.LANG_CHAT_BOMB_RESPONSE, triggerWord.toUpperCase())), false);
            }

            getWorld().setBlockToAir(pos);
            EntityChatBombPrimed entity = new EntityChatBombPrimed(getWorld(), (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
            getWorld().spawnEntity(entity);
            getWorld().playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            getWorld().removeTileEntity(pos);
        }
    }
}
