package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.EntityCow;

public class HandlerCow implements IEntityRenderHandler<EntityCow> {

    @Override
    public float getHeight(EntityCow cow) {
        return 1.8F;
    }

    @Override
    public float getOffsetY() {
        return -0.25F;
    }
}
