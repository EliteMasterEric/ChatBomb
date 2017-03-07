package com.mastereric.chatbomb.util;

import net.minecraft.util.text.TextComponentTranslation;

public class LangUtility {
    public static String getTranslation(String translationKey) {
        TextComponentTranslation desc = new TextComponentTranslation(translationKey);
        return desc.getFormattedText();
    }

    public static String getTranslationFormat(String translationKey, Object... format) {
        TextComponentTranslation desc = new TextComponentTranslation(translationKey);
        return String.format(desc.getUnformattedComponentText(), format);
    }
}
