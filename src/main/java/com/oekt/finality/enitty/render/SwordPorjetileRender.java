package com.oekt.finality.enitty.render;

import com.oekt.finality.Finality;
import com.oekt.finality.enitty.custom.SwordPorjetile;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;

public class SwordPorjetileRender extends ArrowRenderer<SwordPorjetile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Finality.MODID, "textures/entity/placeholder.png");

    public SwordPorjetileRender(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(SwordPorjetile arrow) {
        return TEXTURE;
    }
}
