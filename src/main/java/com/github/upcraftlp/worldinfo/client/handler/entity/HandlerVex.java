package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityVex;

public class HandlerVex implements IEntityRenderHandler<EntityVex> {

    @Override
    public float getScale(EntityVex vex) {
        return 1.2F;
    }

    @Override
    public float getHeight(EntityVex vex) {
        return 2.0F;
    }

    @Override
    public float getWidth(EntityVex vex) {
        return 2.2F;
    }

    @Override
    public float getOffsetX() {
        return -0.15F;
    }
}
