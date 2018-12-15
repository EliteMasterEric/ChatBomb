package com.mastereric.chatbomb.client;

import com.mastereric.chatbomb.ChatBomb;
import net.fabricmc.api.ClientModInitializer;

public class ClientChatBomb implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChatBomb.Entities.initializeEntityRenderers();
    }
}
