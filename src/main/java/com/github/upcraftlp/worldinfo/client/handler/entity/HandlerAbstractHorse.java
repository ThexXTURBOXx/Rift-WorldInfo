package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.AbstractHorse;

public class HandlerAbstractHorse implements IEntityRenderHandler<AbstractHorse> {

	@Override
	public float getHeight(AbstractHorse horse) {
		return horse.isChild() ? horse.height + 2.8F : 5.0F;
		//return horse.func_195046_g(Minecraft.getInstance().getRenderPartialTicks()); //TODO adjust size when horse is standing!
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
