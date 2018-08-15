package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.EntityLivingBase;

public class HandlerAbstractVillager<T extends EntityLivingBase> implements IEntityRenderHandler<T> {

    @Override
    public float getHeight(T t) {
        return 4.5F;
    }

    @Override
    public float getOffsetY() {
        return -0.1F;
    }
}
