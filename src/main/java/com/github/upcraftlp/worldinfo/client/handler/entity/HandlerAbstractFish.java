package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.AbstractFish;

public class HandlerAbstractFish implements IEntityRenderHandler<AbstractFish> {

    @Override
    public float getScale(AbstractFish fish) {
        return 2.0F;
    }
}
