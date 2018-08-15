package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelSquid;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;

public class HandlerSquid implements IEntityRenderHandler<EntitySquid> {

    private final ModelSquid model = new ModelSquid();
    private static final ResourceLocation SQUID_TEXTURES = new ResourceLocation("textures/entity/squid.png");

    @Override
    public float getScale(EntitySquid squid) {
        return 0.8F;
    }

    @Override
    public float getOffsetY() {
        return 2.4F;
    }

    @Override
    public float getOffsetX() {
        return -0.1F;
    }

    @Override
    public float getHeight(EntitySquid squid) {
        return 3.4F;
    }

    @Override
    public float getWidth(EntitySquid squid) {
        return 4.0F;
    }

    @Override
    public boolean renderEntity(EntitySquid squid) {
        float scale = this.getScale(squid);
        Minecraft.getMinecraft().getTextureManager().bindTexture(SQUID_TEXTURES);
        GlStateManager.translate(-10.0F, -5.0F, 0.0F);
        GlStateManager.rotate(-45.0F, 0.0F, 0.0F, 1.0F);
        this.model.render(squid, 00.0F, 0.0F, squid.tentacleAngle, 0.0F, 30.0F, scale);
        return true;
    }
}
