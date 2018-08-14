package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.AbstractSkeleton;

public class HandlerAbstractSkeleton implements IEntityRenderHandler<AbstractSkeleton> {

    @Override
    public float getHeight(AbstractSkeleton skeleton) {
        return 4.5F;
    }
}
