package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityBlaze;

public class HandlerBlaze implements IEntityRenderHandler<EntityBlaze> {

    @Override
    public float getHeight(EntityBlaze blaze) {
        return blaze.isBurning() ? 4.4F : 2.3F;
    }

    @Override
    public float getOffsetY() {
        return -0.6F;
    }
}
