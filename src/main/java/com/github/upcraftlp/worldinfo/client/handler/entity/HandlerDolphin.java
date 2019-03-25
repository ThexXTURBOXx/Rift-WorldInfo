package com.github.upcraftlp.worldinfo.client.handler.entity;

import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.passive.EntityDolphin;

public class HandlerDolphin implements IEntityRenderHandler<EntityDolphin> {

	@Override
	public float getHeight(EntityDolphin dolphin) {
		return 2.7F; //dolphin.func_195046_g(Minecraft.getInstance().getRenderPartialTicks()) --> headYaw //TODO adjust if swimmming up/down!
	}

	@Override
	public float getOffsetX() {
		return -0.3F;
	}
}
