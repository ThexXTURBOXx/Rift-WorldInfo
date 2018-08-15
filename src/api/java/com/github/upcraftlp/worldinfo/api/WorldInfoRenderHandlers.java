package com.github.upcraftlp.worldinfo.api;

import com.github.upcraftlp.worldinfo.api.block.DefaultBlockRenderHandler;
import com.github.upcraftlp.worldinfo.api.block.IBlockRenderHandler;
import com.github.upcraftlp.worldinfo.api.entity.DefaultEntityRenderHandler;
import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldInfoRenderHandlers {

    private static final Map<Class, IEntityRenderHandler> ENTITY_RENDER_HANDLERS = new HashMap<>();
    private static final Map<ResourceLocation, IBlockRenderHandler> BLOCK_RENDER_HANDLERS = new HashMap<>();
    private static final Set<Class<? extends EntityLivingBase>> BLACKLISTED_ENTITIES = new HashSet<>();

    /**
     * add custom entity handlers
     */
    public static <T extends EntityLivingBase> void addEntityHandler(Class<T> entityClass, IEntityRenderHandler<T> renderHandler) {
        ENTITY_RENDER_HANDLERS.put(entityClass, renderHandler);
    }

    @SuppressWarnings("unchecked")
    public static <T extends EntityLivingBase> IEntityRenderHandler<T> getEntityHandler(Class<T> entityClass) {
        IEntityRenderHandler renderHandler = ENTITY_RENDER_HANDLERS.get(entityClass);
        if(renderHandler == null && entityClass != EntityLivingBase.class) {
            renderHandler = ENTITY_RENDER_HANDLERS.get(entityClass.getSuperclass());
            ENTITY_RENDER_HANDLERS.put(entityClass, renderHandler);
        }
        return renderHandler != null ? renderHandler : (IEntityRenderHandler<T>) DefaultEntityRenderHandler.INSTANCE;
    }

    /**
     * add custom block handlers
     */
    public static void addBlockHandler(ResourceLocation block, IBlockRenderHandler renderHandler) {
        BLOCK_RENDER_HANDLERS.put(block, renderHandler);
    }

    public static IBlockRenderHandler getBlockHandler(ResourceLocation block) {
        return BLOCK_RENDER_HANDLERS.getOrDefault(block, DefaultBlockRenderHandler.INSTANCE);
    }

    /**
     * add entities for which the overlay should NOT be rendered
     */
    public static void addInfoExclusion(Class<? extends EntityLivingBase> entityClass) {
        BLACKLISTED_ENTITIES.add(entityClass);
    }

    public static boolean isEntityBlacklisted(Class<? extends EntityLivingBase> entityClass) {
        return BLACKLISTED_ENTITIES.contains(entityClass);
    }


}
