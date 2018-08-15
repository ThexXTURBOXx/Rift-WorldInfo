package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityShulker;

public class HandlerShulker implements IEntityRenderHandler<EntityShulker> {

    @Override
    public float getHeight(EntityShulker shulker) {
        return shulker.height + shulker.getClientPeekAmount(Minecraft.getMinecraft().getRenderPartialTicks()) * 3.0F;
    }
}
