package com.mastereric.chatbomb.events;

import com.mastereric.chatbomb.Reference;
import com.mastereric.chatbomb.common.blocks.BlockChatBomb;
import com.mastereric.chatbomb.util.LogUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;

public class ChatMessageEvent {
    @SubscribeEvent
    public void onServerChatEvent(ServerChatEvent event) {
        Matcher m = Reference.REGEX_CHAT_BOMB.matcher(event.getMessage().toLowerCase());
        if (m.find()) {
            String triggerWord;
            if (m.group(1) != null)
                triggerWord = m.group(1);
            else
                triggerWord = m.group(0);
            LogUtility.info("Triggered! %s", triggerWord);
            explodeInRadius(event, new BlockPos(event.getPlayer().getPosition()), 8, 4, triggerWord);
        }
    }

    private static void explodeInRadius(ServerChatEvent event, BlockPos pos, int radius, int height, String triggerWord) {
        for (BlockPos tempPos : BlockPos.getAllInBox(pos.add(-radius, -height / 2, -radius), pos.add(radius, height / 2, radius))) {
            IBlockState state = event.getPlayer().getEntityWorld().getBlockState(tempPos);
            if (state.getBlock() instanceof BlockChatBomb) {
                ((BlockChatBomb) state.getBlock()).trigger(event.getPlayer().getEntityWorld(), tempPos, event.getPlayer(), triggerWord);
            }
        }
    }
}
