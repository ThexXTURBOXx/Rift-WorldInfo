package com.github.upcraftlp.worldinfo.api;

import com.github.upcraftlp.worldinfo.api.block.IBlockInfoHandler;
import com.github.upcraftlp.worldinfo.api.entity.IEntityInfoHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfoHandlers {

	private static final List<IBlockInfoHandler> BLOCK_INFO_HANDLERS = new ArrayList<>();
	private static final Map<Class<? extends EntityLivingBase>, List<IEntityInfoHandler<? extends EntityLivingBase>>> ENTITY_INFO_HANDLERS = new HashMap<>();

	/**
	 * add custom block handlers.
	 */
	public static void addBlockHandler(IBlockInfoHandler infoHandler) {
		BLOCK_INFO_HANDLERS.add(infoHandler);
	}

	/**
	 * add custom entity handlers.
	 */
	public static <T extends EntityLivingBase> void addEntityHandler(Class<T> entityClass, IEntityInfoHandler<T> infoHandler) {
		List<IEntityInfoHandler<? extends EntityLivingBase>> list = ENTITY_INFO_HANDLERS.getOrDefault(entityClass, new ArrayList<>());
		list.add(infoHandler);
		ENTITY_INFO_HANDLERS.put(entityClass, list);
	}

	public static List<String> getInfo(World world, BlockPos pos, IBlockState state, @Nullable TileEntity tileEntity) {
		List<String> info = new ArrayList<>();
		for (IBlockInfoHandler handler : BLOCK_INFO_HANDLERS) {
			info.addAll(handler.getInfo(world, pos, state, tileEntity));
		}
		return info;
	}

	@SuppressWarnings("unchecked")
	public static <T extends EntityLivingBase> List<String> getInfo(T entity) {
		List<String> info = new ArrayList<>();
		List<IEntityInfoHandler<T>> handlers = (List<IEntityInfoHandler<T>>) (List) ENTITY_INFO_HANDLERS.getOrDefault(entity.getClass(), new ArrayList<>());
		for (IEntityInfoHandler<T> handler : handlers) {
			info.addAll(handler.getInfo(entity));
		}
		return info;
	}

}
