package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityZombie;

public class HandlerZombie implements IEntityRenderHandler<EntityZombie> {

    @Override
    public float getHeight(EntityZombie zombie) {
        return 4.35F;
    }
}
