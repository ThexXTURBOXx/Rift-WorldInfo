package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.EntityRabbit;

public class HandlerRabbit implements IEntityRenderHandler<EntityRabbit> {

    @Override
    public float getScale(EntityRabbit rabbit) {
        return rabbit.isChild() ? 2.2F : 2.0F;
    }
}
