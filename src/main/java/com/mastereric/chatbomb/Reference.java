package com.mastereric.chatbomb;

import java.util.regex.Pattern;

public class Reference {
	public static final String MOD_ID       = "chatbomb";
	public static final String MOD_VERSION  = "1.0";
	public static final String MC_VERSION = "[1.10.2, 1.11.2]";
	public static final String DEPENDENCIES = "required-after:compatlayer";
	public static final String CLIENT_PROXY = "com.mastereric.chatbomb.proxy.ClientProxy";
	public static final String SERVER_PROXY = "com.mastereric.chatbomb.proxy.ServerProxy";

	public static final String NAME_BLOCK_CHAT_BOMB = "chat_bomb";
	public static final String NAME_ENTITY_CHAT_BOMB = "chat_bomb";

	public static final String NAME_ACHIEVEMENT_ACTIVATE_BOMB = "chatbomb.activate_bomb";

	//TODO rename
	public static final String LANG_CHAT_BOMB_NAME = "chat.chatbomb.name";
	public static final String LANG_CHAT_BOMB_RESPONSE = "chat.chatbomb.chat_bomb_response";

	private static final String REGEX_CHAT_BOMB_STRING = ".*" +
            "(boom)|(blast)|(explode)|(explosive)|(dynamite)|" +
            "(tnt)|(detonate)|(bomb)|(burst)|(bang)" +
            ".*";

    public static final Pattern REGEX_CHAT_BOMB = Pattern.compile(REGEX_CHAT_BOMB_STRING);
}
