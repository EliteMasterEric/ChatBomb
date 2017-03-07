package com.mastereric.chatbomb.init;

import com.mastereric.chatbomb.util.LogUtility;
import net.minecraftforge.fml.common.Loader;

public class ModCompat {
    public static String MC_VERSION;

    public static boolean COMMON_CAPABILITIES = false;
    public static boolean WAILA = false;


    public static void initializeCompat() {
        MC_VERSION = Loader.instance().getMinecraftModContainer().getVersion();

        COMMON_CAPABILITIES = isModLoaded("commoncapabilities");
        if (MC_VERSION.equals("1.10") || MC_VERSION.equals("1.10.2"))
            WAILA = isModLoaded("Waila"); // 1.10.
        else
            WAILA = isModLoaded("waila"); // 1.11.
    }

    private static boolean isModLoaded(String modname) {
        if(Loader.isModLoaded(modname)) {
            LogUtility.info("Found mod " + modname);
            return true;
        }
        return false;
    }
}
