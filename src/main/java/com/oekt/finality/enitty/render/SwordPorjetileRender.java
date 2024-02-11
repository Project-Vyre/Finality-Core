package com.oekt.finality.enitty.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.oekt.finality.Finality;
import com.oekt.finality.enitty.ModModelLayers;
import com.oekt.finality.enitty.custom.SwordPerjectileModel;
import com.oekt.finality.enitty.custom.SwordPorjetile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SwordPorjetileRender extends EntityRenderer<SwordPorjetile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Finality.MODID, "textures/entity/placeholder_projetile.png");
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
        // From Arrow renderer
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, swordPorjetile.yRotO, swordPorjetile.getYRot()) - 70.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, swordPorjetile.xRotO, swordPorjetile.getXRot())  - 90.0F));


       // matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(swordPorjetile.yRotO));
        matrixStackIn.mulPoseMatrix(Matrix4f.createTranslateMatrix(0, -1.5f, 0));
        VertexConsumer VertexConsumer = buffferIn.getBuffer(this.model.renderType(getTextureLocation(swordPorjetile)));
        this.model.renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStackIn.popPose();
        super.render(swordPorjetile, yaw, partialTicks, matrixStackIn, buffferIn, packedLightIn);
    }
}
