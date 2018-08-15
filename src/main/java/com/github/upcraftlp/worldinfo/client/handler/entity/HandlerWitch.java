package com.github.upcraftlp.worldinfo.client.handler.entity;

import net.minecraft.entity.monster.EntityWitch;

public class HandlerWitch extends HandlerAbstractVillager<EntityWitch> {

    @Override
    public float getHeight(EntityWitch witch) {
        return super.getHeight(witch) + 2.3F;
    }
}
