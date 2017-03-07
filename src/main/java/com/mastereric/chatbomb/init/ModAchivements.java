package com.mastereric.chatbomb.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;

public class ModAchivements {
    public static void initializeAchievements() {
    }

    private static Achievement createAchievement(String name, int xPos, int yPos, Item icon) {
        return createAchievement(name, xPos, yPos, icon, null, false);
    }

    private static Achievement createAchievement(String name, int xPos, int yPos, Item icon, boolean special) {
        return createAchievement(name, xPos, yPos, icon, null, special);
    }

    private static Achievement createAchievement(String name, int xPos, int yPos, Item icon, Achievement parent) {
        return createAchievement(name, xPos, yPos, icon, parent, false);
    }

    private static Achievement createAchievement(String name, int xPos, int yPos, Item icon, Achievement parent, boolean special) {
        Achievement achievement = new Achievement("achievement." + name, name, xPos, yPos, icon, parent);
        if (special)
            achievement.setSpecial();
        achievement.registerStat();
        return achievement;
    }

    public static void grantAchivement(EntityPlayer player, Achievement achievement) {
        player.addStat(achievement, 1);
    }
}
