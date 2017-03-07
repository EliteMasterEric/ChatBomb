package com.mastereric.chatbomb.events;

import com.mastereric.chatbomb.util.LogUtility;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatMessageEvent {
    @SubscribeEvent
    public void onServerChatEvent(ServerChatEvent event) {
        EntityPlayerMP playerMP = event.getPlayer();
        LogUtility.info("%s said %s at (%d, %d, %d)", event.getUsername(), event.getMessage(),
                (int) playerMP.posX, (int) playerMP.posY, (int) playerMP.posZ);
        BlockPos pos = new BlockPos(playerMP.posX, playerMP.posY, playerMP.posZ);

    }
}
