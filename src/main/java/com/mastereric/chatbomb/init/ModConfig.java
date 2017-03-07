package com.mastereric.chatbomb.init;


import com.mastereric.chatbomb.util.LogUtility;
import net.minecraftforge.common.config.Configuration;

public class ModConfig {
    public static Configuration config;

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_GENERAL_DESC = "General configuration";

    public static boolean MOD_IDEA_190 = false;
    private static String MOD_IDEA_190_NAME = "mod_idea_190";
    private static String MOD_IDEA_190_DESC = "The mods created by the Mod Maker will no longer be usable by the M.E.T.A.";

    public static void parseConfig() {
        if (config != null) {
            try {
                config.load();
                parseConfigGeneral();
            } catch (Exception e) {
                LogUtility.error("Error loading config file! %s", e);
            } finally {
                saveConfig();
            }
        }
    }

    public static void saveConfig() {
        if (config.hasChanged()) {
            config.save();
        }
    }

    private static void parseConfigGeneral() {
        config.addCustomCategoryComment(CATEGORY_GENERAL, CATEGORY_GENERAL_DESC);
        MOD_IDEA_190 = config.getBoolean(MOD_IDEA_190_NAME, CATEGORY_GENERAL, MOD_IDEA_190, MOD_IDEA_190_DESC);
    }
}
