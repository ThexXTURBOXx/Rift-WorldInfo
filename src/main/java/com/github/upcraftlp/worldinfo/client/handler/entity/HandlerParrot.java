package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.EntityParrot;

public class HandlerParrot implements IEntityRenderHandler<EntityParrot> {

    @Override
    public float getScale(EntityParrot parrot) {
        return 1.5F;
    }
}
