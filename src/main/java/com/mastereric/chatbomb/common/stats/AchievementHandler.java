package com.mastereric.chatbomb.common.stats;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class AchievementHandler {

    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent e) {
    }

    @SubscribeEvent
    public void onPickup(EntityItemPickupEvent e) {
    }
}
