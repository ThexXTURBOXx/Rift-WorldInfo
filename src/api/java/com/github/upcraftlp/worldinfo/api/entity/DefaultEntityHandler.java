package com.github.upcraftlp.worldinfo.api.entity;

import net.minecraft.entity.EntityLivingBase;

public class DefaultEntityHandler<T extends EntityLivingBase> implements IEntityRenderHandler<T> {

    private final float height, yOffset, scale;

    public DefaultEntityHandler(float height) {
        this(height, 0.0F, 1.0F);
    }

    public DefaultEntityHandler(float height, float yOffset) {
        this(height, yOffset, 1.0F);
    }

    public DefaultEntityHandler(float height, float yOffset, float scale) {
        this.height = height;
        this.yOffset = yOffset;
        this.scale = scale;
    }

    @Override
    public float getHeight(T t) {
        return this.height;
    }

    @Override
    public float getOffsetY() {
        return this.yOffset;
    }

    @Override
    public float getScale(T t) {
        return this.scale;
    }
}
