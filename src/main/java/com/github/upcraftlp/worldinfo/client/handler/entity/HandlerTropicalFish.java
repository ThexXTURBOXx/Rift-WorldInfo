package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.EntityTropicalFish;
import net.minecraft.util.ResourceLocation;

public class HandlerTropicalFish implements IEntityRenderHandler<EntityTropicalFish> {

	private static final ResourceLocation MODEL_B = new ResourceLocation("textures/entity/fish/tropical_b.png");

	@Override
	public float getScale(EntityTropicalFish fish) {
		boolean modelB = fish.getBodyTexture().equals(MODEL_B);
		return modelB ? 1.6F : 3.0F;
	}
}
