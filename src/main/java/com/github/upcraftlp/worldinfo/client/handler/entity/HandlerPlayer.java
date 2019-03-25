package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.player.EntityPlayer;

public class HandlerPlayer<T extends EntityPlayer> implements IEntityRenderHandler<T> {

	@Override
	public float getHeight(EntityPlayer player) {
		return 7.5F;
	}

	@Override
	public float getWidth(EntityPlayer player) {
		return 1.0F;
	}

	@Override
	public float getOffsetY() {
		return 0.25F;
	}
}
