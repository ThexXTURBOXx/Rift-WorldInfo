package com.github.upcraftlp.worldinfo.client;

import com.github.upcraftlp.worldinfo.WorldInfo;
import com.github.upcraftlp.worldinfo.api.RenderingHandlers;
import com.github.upcraftlp.worldinfo.api.block.IBlockRenderHandler;
import com.github.upcraftlp.worldinfo.api.entity.IEntityRenderHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.dimdev.rift.listener.client.OverlayRenderer;

//@SuppressWarnings("unused")
public class HudRenderer implements OverlayRenderer {

    private static final int NAME_MARGIN = 4;
    private static final int COLOR_BOX_ALPHA = 0x7F << 24;
    private static final int COLOR_WHITE = COLOR_BOX_ALPHA | 0xFFFFFF;
    private static final int COLOR_BOX_BG = COLOR_BOX_ALPHA | 0x100010;
    private static final int COLOR_BOX_FRAME_START = COLOR_BOX_ALPHA | 0x5000FF;
    private static final int COLOR_BOX_FRAME_END = COLOR_BOX_ALPHA | 0x28007F;

    //static so other renders can query and/or change them
    public static int x, y;

    @SuppressWarnings("unchecked")
    @Override
    public void renderOverlay() {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.currentScreen == null) { //make sure we're not in a GUI
            mc.profiler.startSection(WorldInfo.MODID + ":overlay");
            int width = mc.mainWindow.getScaledWidth();
            //int height = mc.mainWindow.getScaledHeight();  //TODO remove
            RayTraceResult result = mc.objectMouseOver;
            if(result != null && result.typeOfHit != RayTraceResult.Type.MISS) {
                x = (int) (width / 2.0F);
                y = 2; //TODO offset if there is one or more boss bar
                float scale = 10;
                int zLevel = 100;

                if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
                    scale = 20;
                    y += 4;
                    BlockPos pos = result.getBlockPos();
                    IBlockState state = mc.world.getBlockState(pos);
                    ItemStack stack = state.getBlock().getItem(mc.world, pos, state);
                    //FIXME is the stack nullable?

                    ResourceLocation itemName = Item.REGISTRY.getNameForObject(stack.getItem());
                    if(itemName == null) itemName = new ResourceLocation("air");
                    IBlockRenderHandler blockRenderHandler = RenderingHandlers.getBlockHandler(itemName);
                    scale *= blockRenderHandler.getScale();

                    String blockDisplayName = blockRenderHandler.getBlockDisplayString(stack, state, mc.world, pos);
                    int blockNameWidth = mc.fontRenderer.getStringWidth(blockDisplayName);
                    double bWidth = blockRenderHandler.getWidth(state, mc.world, pos) * scale;
                    double bHeight = blockRenderHandler.getHeight(state, mc.world, pos) * scale;

                    int w = (int) Math.max(bWidth, blockNameWidth) * 2;
                    int h = (int) (bHeight + mc.fontRenderer.FONT_HEIGHT + NAME_MARGIN);
                    drawBackgroundBox(x + w / 4, y - NAME_MARGIN, w + NAME_MARGIN * 3, h);

                    //render the block's item
                    GlStateManager.pushMatrix();
                    RenderHelper.enableGUIStandardItemLighting();
                    {
                        if(!blockRenderHandler.renderBlock(mc.world, state, pos)) {
                            mc.getRenderItem().renderItemAndEffectIntoGUI(mc.player, stack, (int) (x - bWidth / 2.0F), y);
                        }
                        y += bHeight;
                    }
                    GlStateManager.popMatrix();

                    mc.fontRenderer.drawStringWithShadow(blockDisplayName, x - Math.round(blockNameWidth / 2.0F) - 2, y - 2, 0xFFFFFFFF);
                } else if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
                    EntityLivingBase entity = null;
                    if(result.entityHit instanceof EntityLivingBase) entity = (EntityLivingBase) result.entityHit;
                    else if(result.entityHit instanceof MultiPartEntityPart) { //fix for multipart entities //FIXME multipart fix does not work, ender dragon does not render
                        IEntityMultiPart multiPart = ((MultiPartEntityPart) result.entityHit).parent;
                        if(multiPart instanceof EntityLivingBase) entity = (EntityLivingBase) multiPart;
                    }
                    if(entity != null && entity.isEntityAlive()) {
                        if(!RenderingHandlers.isEntityBlacklisted(entity.getClass())) {
                            IEntityRenderHandler renderHandler = RenderingHandlers.getEntityHandler(entity.getClass());
                            scale *= renderHandler.getScale(entity);
                            float eHeight = renderHandler.getHeight(entity) * scale;
                            float eWidth = renderHandler.getWidth(entity) * scale;

                            String name = renderHandler.getEntityDisplayString(entity);
                            int entityNameWidth = mc.fontRenderer.getStringWidth(name);

                            int w = (int) Math.max(eWidth, entityNameWidth) * 2;
                            int h = (int) (eHeight + mc.fontRenderer.FONT_HEIGHT + NAME_MARGIN * 3);
                            drawBackgroundBox(x + w / 4 + NAME_MARGIN / 2, y, w + NAME_MARGIN * 3, h);

                            GlStateManager.pushMatrix();
                            {
                                y += NAME_MARGIN;
                                y += eHeight;
                                GlStateManager.translate(x + renderHandler.getOffsetX() * scale, y - renderHandler.getOffsetY() * scale, zLevel);
                                if(!renderHandler.renderEntity(entity)) {
                                    GuiInventory.drawEntityOnScreen(0, 0, (int) scale * 2, 45, 0, entity);
                                }
                            }
                            GlStateManager.popMatrix();

                            y += NAME_MARGIN;
                            mc.fontRenderer.drawStringWithShadow(name, x - entityNameWidth / 2.0F, y, 0xFFFFFFFF);
                            //TODO display entity health and possibly other information
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
