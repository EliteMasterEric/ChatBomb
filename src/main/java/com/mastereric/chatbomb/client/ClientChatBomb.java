package com.mastereric.chatbomb.client;

import com.mastereric.chatbomb.ChatBomb;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class ClientChatBomb implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChatBomb.Entities.initializeEntityRenderers();
    }
}
