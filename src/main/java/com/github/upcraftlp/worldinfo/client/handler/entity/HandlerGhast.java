package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityGhast;

public class HandlerGhast implements IEntityRenderHandler<EntityGhast> {

	@Override
	public float getWidth(EntityGhast ghast) {
		return 14.0F;
	}

	@Override
	public float getHeight(EntityGhast ghast) {
		return 16.5F;
	}

	@Override
	public float getScale(EntityGhast ghast) {
		return 0.3F;
	}

	@Override
	public float getOffsetY() {
		return 7.5F;
	}
}
