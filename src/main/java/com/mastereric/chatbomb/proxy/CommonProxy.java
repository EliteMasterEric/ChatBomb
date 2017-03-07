package com.mastereric.chatbomb.proxy;

import com.mastereric.chatbomb.common.stats.AchievementHandler;
import com.mastereric.chatbomb.events.ChatMessageEvent;
import com.mastereric.chatbomb.init.*;
import com.mastereric.chatbomb.util.LogUtility;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		LogUtility.info("Performing common initialization.");
		// Add achievements.
		ModAchivements.initializeAchievements();
		ModBlocks.initializeBlocks();
        ModRecipes.initializeCraftingRecipes();
        ModEntities.initializeEntities();

		ModConfig.config = new Configuration(new File(event.getModConfigurationDirectory().getPath(), "chatbomb.cfg"));
		ModConfig.parseConfig();
	}

	public void init(FMLInitializationEvent event) {
	    // Register GUI handler.
		//NetworkRegistry.INSTANCE.registerGuiHandler(ChatBomb.instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new AchievementHandler());
		MinecraftForge.EVENT_BUS.register(new ChatMessageEvent());
	}

	public void postInit(FMLPostInitializationEvent e) {
		ModConfig.saveConfig();
		// Initialize compatibility!
		ModCompat.initializeCompat();
	}
}
