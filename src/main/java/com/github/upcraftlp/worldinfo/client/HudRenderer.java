package com.github.upcraftlp.worldinfo.client;

import com.github.upcraftlp.worldinfo.WorldInfo;
import com.github.upcraftlp.worldinfo.api.InfoHandlers;
import com.github.upcraftlp.worldinfo.api.RenderingHandlers;
import com.github.upcraftlp.worldinfo.api.block.IBlockRenderHandler;
import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import com.github.upcraftlp.worldinfo.api.util.TurboUtils;
import java.util.List;
import net.insomniakitten.pylon.annotation.rift.Listener;
import net.insomniakitten.pylon.ref.Side;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextFormatting;
import org.dimdev.rift.listener.client.OverlayRenderer;

@Listener(side = Side.CLIENT)
public class HudRenderer implements OverlayRenderer {

	//TODO fluids -> config?
	//TODO offset if there is one or more boss bar

	private static final int LINE_MARGIN = 4;
	private static final int COLOR_BOX_ALPHA = 0x7F << 24;
	//private static final int COLOR_WHITE = COLOR_BOX_ALPHA | 0xFFFFFF;
	private static final int COLOR_BOX_BG = COLOR_BOX_ALPHA | 0x100010;
	private static final int COLOR_BOX_FRAME_START = COLOR_BOX_ALPHA | 0x5000FF;
	private static final int COLOR_BOX_FRAME_END = COLOR_BOX_ALPHA | 0x28007F;
	private static final ResourceLocation AIR = new ResourceLocation("air");

	//static so other renders can query and/or change them
	public static int x, y;

	@SuppressWarnings("unchecked")
	@Override
	public void renderOverlay() {
		Minecraft mc = Minecraft.getInstance();
		if (mc.currentScreen == null) { //make sure we're not in a GUI
			mc.profiler.startSection(WorldInfo.MODID + ":overlay");
			int width = mc.mainWindow.getScaledWidth();
			//int height = mc.mainWindow.getScaledHeight();  //TODO remove
			RayTraceResult result = mc.objectMouseOver;
			if (result != null && result.type != RayTraceResult.Type.MISS) {
				x = (int) (width / 2.0F);
				y = 2;
				float scale = 10;
				int zLevel = 100;

				if (result.type == RayTraceResult.Type.BLOCK) {
					scale = 20;
					y += 4;
					BlockPos pos = result.getBlockPos();
					IBlockState state = mc.world.getBlockState(pos);
					Block block = state.getBlock();
					ItemStack stack = block.getItem(mc.world, pos, state);
					ResourceLocation itemName = IRegistry.ITEM.getKey(stack.getItem());
					if (itemName == null || itemName.equals(AIR)) {
						itemName = IRegistry.ITEM.getKey(block.asItem());
						if (itemName == null) {
							itemName = AIR;
						} else {
							stack = new ItemStack(block);
						}
					}
					List<String> info = InfoHandlers.getInfo(mc.world, pos, state, mc.world.getTileEntity(pos));
					String harvest;
					boolean harvestable = false;
					float hardness = state.getBlockHardness(mc.world, pos);
					if (hardness == -1.0F || hardness == -1.0D || hardness == -1) {
						harvest = I18n.format("worldinfo.info.unbreakable");
					} else if (mc.player.canHarvestBlock(state)) {
						harvestable = true;
						harvest = I18n.format("worldinfo.info.harvest");
					} else {
						harvest = I18n.format("worldinfo.info.notharvest");
					}
					info.add((harvestable ? "§a✔" : "§4✘") + " §r" + harvest);
					ItemGroup itemGroup = stack.getItem().getGroup();
					String group;
					if (itemGroup != null) {
						if (itemGroup.getIndex() <= 11)
							group = I18n.format("worldinfo.info.minecraft");
						else
							group = I18n.format(itemGroup.getTranslationKey());
					} else {
						group = I18n.format("worldinfo.info.unknown");
					}

					IBlockRenderHandler blockRenderHandler = RenderingHandlers.getBlockHandler(itemName);
					scale *= blockRenderHandler.getScale();

					String blockDisplayName = blockRenderHandler.getBlockDisplayString(stack, state, mc.world, pos);
					int blockNameWidth = mc.fontRenderer.getStringWidth(blockDisplayName);
					int groupWidth = mc.fontRenderer.getStringWidth(group);
					double bWidth = blockRenderHandler.getWidth(state, mc.world, pos) * scale;
					double bHeight = blockRenderHandler.getHeight(state, mc.world, pos) * scale;

					double[] infoWidths = new double[info.size()];
					for (int i = 0; i < infoWidths.length; i++) {
						infoWidths[i] = mc.fontRenderer.getStringWidth(info.get(i));
					}
					int w = (int) TurboUtils.maxOr(0, TurboUtils.maxOr(0, infoWidths), bWidth, blockNameWidth, groupWidth) * 2;
					int h = (int) ((itemName.equals(AIR) ? 0 : bHeight) //Block Render
							+ (2 * (mc.fontRenderer.FONT_HEIGHT + LINE_MARGIN)) //Name, Creative Tab
							+ (info.size() * mc.fontRenderer.FONT_HEIGHT) //Info lines
							+ (info.size() > 0 ? LINE_MARGIN : 0)); //Space at the end
					drawBackgroundBox(x + w / 4, y - LINE_MARGIN, w + LINE_MARGIN * 3, h);

					if (!itemName.equals(AIR)) {
						//render the block's item
						GlStateManager.pushMatrix();
						RenderHelper.enableGUIStandardItemLighting();
						{
							if (!blockRenderHandler.renderBlock(mc.world, state, pos)) {
								mc.getItemRenderer().renderItemAndEffectIntoGUI(mc.player, stack, (int) (x - bWidth / 2.0F), y);
							}
							y += bHeight;
						}
						GlStateManager.popMatrix();
						y -= 2;
					}

					x -= 2;
					mc.fontRenderer.drawStringWithShadow(blockDisplayName, x - Math.round(blockNameWidth / 2.0F), y, 0xFFFFFFFF);
					y += LINE_MARGIN + mc.fontRenderer.FONT_HEIGHT;
					mc.fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + group, x - Math.round(groupWidth / 2.0F), y, 0xFF0000FF);
					y += LINE_MARGIN + mc.fontRenderer.FONT_HEIGHT;
					for (int i = 0; i < info.size(); i++) {
						String infoLine = info.get(i);
						mc.fontRenderer.drawStringWithShadow(infoLine, x - Math.round(infoWidths[i] / 2.0F), y, 0xFFAAAAAA);
						y += mc.fontRenderer.FONT_HEIGHT;
					}
				} else if (result.type == RayTraceResult.Type.ENTITY) {
					EntityLivingBase entity = null;
					if (result.entity instanceof EntityLivingBase) entity = (EntityLivingBase) result.entity;
					else if (result.entity instanceof MultiPartEntityPart) { //fix for multipart entities //FIXME multipart fix does not work, ender dragon does not render
						IEntityMultiPart multiPart = ((MultiPartEntityPart) result.entity).parent;
						if (multiPart instanceof EntityLivingBase) entity = (EntityLivingBase) multiPart;
					}
					if (entity != null && entity.isAlive()) {
						if (!RenderingHandlers.isEntityBlacklisted(entity.getClass())) {
							IEntityRenderHandler renderHandler = RenderingHandlers.getEntityHandler(entity.getClass());
							scale *= renderHandler.getScale(entity);
							float eHeight = renderHandler.getHeight(entity) * scale;
							float eWidth = renderHandler.getWidth(entity) * scale;

							String name = renderHandler.getEntityDisplayString(entity);
							int entityNameWidth = mc.fontRenderer.getStringWidth(name);
							float maxHealth = entity.getMaxHealth();
							float health = Math.min(maxHealth, entity.getHealth());
							String healthString = TurboUtils.round(health, 1) + " ❤ / " + TurboUtils.round(maxHealth, 1) + " ❤";
							int healthWidth = mc.fontRenderer.getStringWidth(healthString);

							int w = (int) TurboUtils.maxOr(0, eWidth, entityNameWidth, healthWidth) * 2;
							int h = (int) (eHeight //Entity
									+ (mc.fontRenderer.FONT_HEIGHT + LINE_MARGIN) * 2 //2 lines
									+ LINE_MARGIN * 2); //Top & Bottom Margin
							drawBackgroundBox(x + w / 4 + LINE_MARGIN / 2, y, w + LINE_MARGIN * 3, h);

							GlStateManager.pushMatrix();
							{
								y += LINE_MARGIN;
								y += eHeight;
								GlStateManager.translatef(x + renderHandler.getOffsetX() * scale, y - renderHandler.getOffsetY() * scale, zLevel);
								if (!renderHandler.renderEntity(entity)) {
									GuiInventory.drawEntityOnScreen(0, 0, (int) scale * 2, 45, 0, entity);
								}
							}
							GlStateManager.popMatrix();

							y += LINE_MARGIN;
							mc.fontRenderer.drawStringWithShadow(name, x - entityNameWidth / 2.0F, y, 0xFFFFFFFF);
							y += LINE_MARGIN + mc.fontRenderer.FONT_HEIGHT;
							mc.fontRenderer.drawStringWithShadow(healthString, x - healthWidth / 2.0F, y, 0xFFFFFFFF);
						}
					}
				}
			}
			mc.profiler.endSection();
		}
	}

	private static void drawBackgroundBox(int x, int y, int width, int height) {
		GlStateManager.pushMatrix();
		{
			RenderUtil.drawTooltipBox(x - width / 2, y, width / 2, height, COLOR_BOX_BG, COLOR_BOX_FRAME_START, COLOR_BOX_FRAME_END);
		}
		GlStateManager.popMatrix();
	}

}
