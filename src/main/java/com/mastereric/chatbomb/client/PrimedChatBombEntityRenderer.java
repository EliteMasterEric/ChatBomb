package com.mastereric.chatbomb.client;

import com.mastereric.chatbomb.ChatBomb;
import com.mastereric.chatbomb.common.entity.PrimedChatBombEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class PrimedChatBombEntityRenderer extends EntityRenderer<PrimedChatBombEntity> {
    public PrimedChatBombEntityRenderer(EntityRenderDispatcher var1) {
        super(var1);
        this.field_4673 = 0.5F;
    }

    @Override
    public void method_3936(PrimedChatBombEntity chatBombEntity, double var2, double var4, double var6, float var8, float var9) {
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)var2, (float)var4 + 0.5F, (float)var6);
        float var11;
        if ((float)chatBombEntity.getFuseTimer() - var9 + 1.0F < 10.0F) {
            var11 = 1.0F - ((float)chatBombEntity.getFuseTimer() - var9 + 1.0F) / 10.0F;
            var11 = MathHelper.clamp(var11, 0.0F, 1.0F);
            var11 *= var11;
            var11 *= var11;
            float var12 = 1.0F + var11 * 0.3F;
            GlStateManager.scalef(var12, var12, var12);
        }

        var11 = (1.0F - ((float)chatBombEntity.getFuseTimer() - var9 + 1.0F) / 100.0F) * 0.8F;
        this.method_3925(chatBombEntity);
        GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
        blockRenderManager.renderAsItem(ChatBomb.Blocks.CHAT_BOMB.getDefaultState(), chatBombEntity.method_5718());
        GlStateManager.translatef(0.0F, 0.0F, 1.0F);
        if (this.field_4674) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.method_3929(chatBombEntity));
            blockRenderManager.renderAsItem(ChatBomb.Blocks.CHAT_BOMB.getDefaultState(), 1.0F);
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        } else if (chatBombEntity.getFuseTimer() / 5 % 2 == 0) {
            GlStateManager.disableTexture();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SrcBlendFactor.SRC_ALPHA, GlStateManager.DstBlendFactor.DST_ALPHA);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, var11);
            GlStateManager.polygonOffset(-3.0F, -3.0F);
            GlStateManager.enablePolygonOffset();
            blockRenderManager.renderAsItem(ChatBomb.Blocks.CHAT_BOMB.getDefaultState(), 1.0F);
            GlStateManager.polygonOffset(0.0F, 0.0F);
            GlStateManager.disablePolygonOffset();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture();
        }

        GlStateManager.popMatrix();
        super.method_3936(chatBombEntity, var2, var4, var6, var8, var9);
    }

    protected Identifier getTexture(PrimedChatBombEntity var1) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEX;
    }
}
