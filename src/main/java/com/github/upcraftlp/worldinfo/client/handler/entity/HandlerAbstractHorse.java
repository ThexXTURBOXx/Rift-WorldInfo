package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.AbstractHorse;

public class HandlerAbstractHorse implements IEntityRenderHandler<AbstractHorse> {

    @Override
    public float getHeight(AbstractHorse horse) {
        return horse.isChild() ? horse.height + 2.8F : 5.0F;
    }

    @Override
    public float getWidth(AbstractHorse horse) {
        return 5.0F;
    }

    @Override
    public float getOffsetY() {
        return 0.1F;
    }
}
