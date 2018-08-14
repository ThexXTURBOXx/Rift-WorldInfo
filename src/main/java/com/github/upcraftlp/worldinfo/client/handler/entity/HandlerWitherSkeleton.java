package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityWitherSkeleton;

public class HandlerWitherSkeleton implements IEntityRenderHandler<EntityWitherSkeleton> {

    @Override
    public float getHeight(EntityWitherSkeleton skeleton) {
        return 5.2F;
    }

    @Override
    public float getScale(EntityWitherSkeleton skeleton) {
        return 0.9F;
    }
}
