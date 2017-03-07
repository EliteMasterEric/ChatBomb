package com.mastereric.chatbomb.init;


import com.mastereric.chatbomb.util.LogUtility;
import net.minecraftforge.common.config.Configuration;

public class ModConfig {
    public static Configuration config;

    public static boolean VERBOSE = true;

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_GENERAL_DESC = "General configuration";

    private static final String VERBOSE_NAME = "verbose";
    private static final String VERBOSE_DESC = "Does ChatBomb respond to the player?";

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
        VERBOSE = config.getBoolean(VERBOSE_NAME, CATEGORY_GENERAL, VERBOSE, VERBOSE_DESC);
    }
}
