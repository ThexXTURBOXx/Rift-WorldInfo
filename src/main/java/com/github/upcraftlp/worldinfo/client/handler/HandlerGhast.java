package com.github.upcraftlp.worldinfo.client.handler;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityGhast;

public class HandlerGhast implements IEntityRenderHandler<EntityGhast> {

    @Override
    public float getWidth(EntityGhast ghast) {
        return 4.0F;
    }

    @Override
    public float getHeight(EntityGhast ghast) {
        return 8.5F;
    }

    @Override
    public float getScale(EntityGhast ghast) {
        return 0.2F;
    }

    @Override
    public float getOffsetY() {
        return 6.0F;
    }
}
