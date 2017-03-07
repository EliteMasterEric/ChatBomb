package com.mastereric.chatbomb;

import com.mastereric.chatbomb.init.ModBlocks;
import com.mastereric.chatbomb.proxy.CommonProxy;
import mcjty.lib.compat.CompatCreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MC_VERSION, dependencies = Reference.DEPENDENCIES)
public class ChatBomb {

	@Mod.Instance(Reference.MOD_ID)
	public static ChatBomb instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		FMLLog.getLogger().info("Initializing mod " + Reference.MOD_ID);
		proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static CompatCreativeTabs creativeTab = new CompatCreativeTabs(Reference.MOD_ID) {
		@Override
		protected Item getItem() {
			return ModBlocks.itemBlockChatBomb;
		}
	};
}
