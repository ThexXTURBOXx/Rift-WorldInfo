package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.EntityLlama;

public class HandlerLlama implements IEntityRenderHandler<EntityLlama> {

	@Override
	public float getHeight(EntityLlama llama) {
		return llama.isChild() ? llama.height : 5.5F;
	}
}
