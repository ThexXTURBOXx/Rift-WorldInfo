package com.github.upcraftlp.worldinfo.api.entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.EntityLivingBase;

public interface IEntityInfoHandler<T extends EntityLivingBase> {

	/**
	 * @return Custom Info to show in WorldInfo's HUD
	 */
	@Nonnull
	default List<String> getInfo(T entity) {
		return new ArrayList<>();
	}

}
