package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityCreeper;

public class HandlerCreeper implements IEntityRenderHandler<EntityCreeper> {

    @Override
    public float getHeight(EntityCreeper creeper) {
        return 3.2F;
    }
}
