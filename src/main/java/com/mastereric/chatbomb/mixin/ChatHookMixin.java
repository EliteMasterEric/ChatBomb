package com.mastereric.chatbomb.mixin;

import com.mastereric.chatbomb.common.blocks.ChatBombBlock;
import com.mastereric.chatbomb.util.LogUtility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.ChatMessageServerPacket;
import net.minecraft.text.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * This is a mixin! It allows you to modify the code of another method,
 * usually one from core Minecraft.
 * This mixin is modifying net.minecraft.server.network.ServerPlayNetworkHandler.class,
 * for the purpose of adding additional logic when processing chat messages.
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ChatHookMixin {
    @Shadow
    public ServerPlayerEntity player;

	/**
     * The mixin injects the following method is injected, such that it is called in the function onChatMessage,
     * before the invocation of the function void net.minecraft.server.PlayerManager.broadcastChatMessage(net.minecraft.text.TextComponent, boolean).
     *
     * LocalCapture.CAPTURE_FAILEXCEPTION indicates that local variables should be passed to the method.
     * The arguments taken must be of a very specific type and order. To determine the expected order, set it to LocalCapture.PRINT first.
     *
     * See here for more info:
     * https://github.com/SpongePowered/Mixin/wiki/Advanced-Mixin-Usage---Callback-Injectors
     * http://jenkins.liteloader.com/view/Other/job/Mixin/javadoc/org/spongepowered/asm/util/Locals.html
	 */
	@Inject(method = "onChatMessage", at = @At("TAIL"),
        locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private void captureChatMessage(ChatMessageServerPacket inputPacket, CallbackInfo callbackInfo) {
		LogUtility.info("Captured chat message: %s", inputPacket.getChatMessage());
        ChatBombBlock.evaluateChatMessage(player, inputPacket.getChatMessage());
	}

	/*
	@At reference:
	HEAD: inject the callback immediately before the method runs
	RETURN: inject the calllback immediately before returning the method
	TAIL: inject the callback immediately before the final return call in the target method (i.e. at the end)
	INVOKE: inject the callback immediately before a given target method is executed
	Note that 'at = ' is an array, so multiple @At can be specified.

	Old invoke:
	at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/TextComponent;Z)V"
	 */
}
