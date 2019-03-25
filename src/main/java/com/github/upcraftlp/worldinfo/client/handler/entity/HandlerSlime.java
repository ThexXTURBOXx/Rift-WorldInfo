package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.monster.EntitySlime;

public class HandlerSlime implements IEntityRenderHandler<EntitySlime> {

	private static final int MAX_SIZE = 7; //TODO maybe have a fixed size renderer for slimes?

	@Override
	public float getHeight(EntitySlime slime) {
		int size = slime.getSlimeSize() - 1;
		return size == 0 ? 0 : size > MAX_SIZE ? -3.5F : (size * 1.5F);
	}

	@Override
	public float getWidth(EntitySlime slime) {
		return getHeight(slime);
	}

	@Override
	public float getScale(EntitySlime slime) {
		int size = slime.getSlimeSize() - 1;
		return size < 1 ? 1.5F : size > MAX_SIZE ? 1.0F : (1.0F / (size * 0.8F)) + size * 0.1F; //1.0F / slime.getSlimeSize();
	}

	//cancel rendering if too big
	@Override
	public boolean renderEntity(EntitySlime slime) {
		return slime.getSlimeSize() - 1 > MAX_SIZE;
	}
}
