package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntityPolarBear;

public class HandlerPolarBear implements IEntityRenderHandler<EntityPolarBear> {

	@Override
	public float getHeight(EntityPolarBear bear) {
		return 1.5F + (bear.isStanding() ? 8.2F : 1.8F);
	}
}
