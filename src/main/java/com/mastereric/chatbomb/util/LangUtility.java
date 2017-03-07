package com.mastereric.chatbomb.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class LangUtility {
    public static String getTranslation(String translationKey) {
        TextComponentTranslation desc = new TextComponentTranslation(translationKey);
        return desc.getFormattedText();
    }

    public static ITextComponent getTranslationRaw(String translationKey) {
        TextComponentTranslation desc = new TextComponentTranslation(translationKey);
        return desc;
    }

    public static String getTranslationFormat(String translationKey, Object... format) {
        TextComponentTranslation desc = new TextComponentTranslation(translationKey);
        return String.format(desc.getUnformattedText(), format);
    }

    public static ITextComponent getChatMessage(ITextComponent username, String message) {
        return new TextComponentTranslation("chat.type.text", username, message);
    }
}
