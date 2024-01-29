package com.oekt.finality.enitty.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.oekt.finality.Finality;
import com.oekt.finality.enitty.ModModelLayers;
import com.oekt.finality.enitty.custom.SwordPerjectileModel;
import com.oekt.finality.enitty.custom.SwordPorjetile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SwordPorjetileRender extends EntityRenderer<SwordPorjetile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Finality.MODID, "textures/entity/placeholder.png");
    public SwordPerjectileModel model;
    public SwordPorjetileRender(EntityRendererProvider.Context manager) {
        super(manager);
        this.model = new SwordPerjectileModel(SwordPerjectileModel.createBodyLayer().bakeRoot());

    }

    public ResourceLocation getTextureLocation(SwordPorjetile arrow) {
        return TEXTURE;
    }

    @Override
    public void render(SwordPorjetile swordPorjetile, float yaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource buffferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        VertexConsumer VertexConsumer = buffferIn.getBuffer(this.model.renderType(getTextureLocation(swordPorjetile)));
        this.model.renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
        super.render(swordPorjetile, yaw, partialTicks, matrixStackIn, buffferIn, packedLightIn);
    }
}
