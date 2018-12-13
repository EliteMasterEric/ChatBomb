package com.mastereric.chatbomb.util;

import com.mastereric.chatbomb.ChatBomb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtility {
    private static final String TAG = "ChatBomb";

    private static final Logger logger = LogManager.getLogger(ChatBomb.class);

    public static void warn(String format, Object... data) {
        logger.warn(String.format(format, data));
    }

    public static void debug(String format, Object... data) {
        logger.debug(String.format(format, data));
    }

    public static void info(String format, Object... data) {
        logger.info(String.format(format, data));
    }

    public static void error(String format, Object... data) {
        logger.error(String.format(format, data));
    }
}
