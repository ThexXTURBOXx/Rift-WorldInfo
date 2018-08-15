package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityElderGuardian;

public class HandlerElderGuardian implements IEntityRenderHandler<EntityElderGuardian> {

    @Override
    public float getHeight(EntityElderGuardian guardian) {
        return 7.0F;
    }

    @Override
    public float getWidth(EntityElderGuardian guardian) {
        return 12.0F;
    }

    @Override
    public float getOffsetY() {
        return 0.8F;
    }

    @Override
    public float getOffsetX() {
        return -0.3F;
    }

    @Override
    public float getScale(EntityElderGuardian guardian) {
        return 0.6F;
    }
}
