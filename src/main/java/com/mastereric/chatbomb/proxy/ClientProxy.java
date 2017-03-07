package com.mastereric.chatbomb.proxy;

import com.mastereric.chatbomb.init.ModBlocks;
import com.mastereric.chatbomb.init.ModEntities;
import com.mastereric.chatbomb.util.LogUtility;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// Suppress warnings about calling @SideOnly(Side.CLIENT) methods in the ClientProxy,
// this class is only used on the client.
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
	    super.preInit(event);
		LogUtility.info("Performing client initialization.");
		ModBlocks.initializeBlockModels();
        ModEntities.initializeEntityModels();
	}
}
