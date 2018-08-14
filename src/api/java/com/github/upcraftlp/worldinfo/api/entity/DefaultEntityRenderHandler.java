package com.github.upcraftlp.worldinfo.api.entity;

import net.minecraft.entity.EntityLivingBase;

public class DefaultEntityRenderHandler implements IEntityRenderHandler<EntityLivingBase> {

    public static IEntityRenderHandler<EntityLivingBase> INSTANCE = new DefaultEntityRenderHandler();

    @Override
    public float getWidth(EntityLivingBase entity) {
        return entity.width;
    }

    @Override
    public float getHeight(EntityLivingBase entity) {
        return entity.height;
    }
}
