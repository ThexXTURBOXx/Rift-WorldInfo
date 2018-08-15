package com.github.upcraftlp.worldinfo.api.entity;

import net.minecraft.entity.EntityLivingBase;

public interface IEntityRenderHandler<T extends EntityLivingBase> {

    default float getWidth(T t) {
        return Math.max(t.width, getHeight(t)) * 2.0F;
    }

    default float getHeight(T t) {
        return t.isBurning() ? t.height + 2.1F : t.height;
    }

    default float getScale(T t) {
        return 1.0F;
    }

    default float getOffsetX() {
        return 0.0F;
    }

    default float getOffsetY() {
        return 0.0F;
    }

    /**
     * @return a (translated) String for displaying the Entity's name
     */
    default String getEntityDisplayString(T t) {
        return t.getDisplayName().getFormattedText();
    }

    default boolean renderEntity(T t) {
        return false;
    }
}
