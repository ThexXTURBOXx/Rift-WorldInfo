package com.github.upcraftlp.worldinfo.client;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RenderUtil {

    public static void drawGradientRect(int left, int top, int right, int bottom, int zLevel, int startColor, int endColor) {
        float alpha1 = (float) (startColor >> 24 & 255) / 255.0F;
        float red1 = (float) (startColor >> 16 & 255) / 255.0F;
        float green1 = (float) (startColor >> 8 & 255) / 255.0F;
        float blue1 = (float) (startColor & 255) / 255.0F;
        float alpha2 = (float) (endColor >> 24 & 255) / 255.0F;
        float red2 = (float) (endColor >> 16 & 255) / 255.0F;
        float green2 = (float) (endColor >> 8 & 255) / 255.0F;
        float blue2 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos((double) right, (double) top, (double) zLevel).color(red1, green1, blue1, alpha1).endVertex();
        buffer.pos((double) left, (double) top, (double) zLevel).color(red1, green1, blue1, alpha1).endVertex();
        buffer.pos((double) left, (double) bottom, (double) zLevel).color(red2, green2, blue2, alpha2).endVertex();
        buffer.pos((double) right, (double) bottom, (double) zLevel).color(red2, green2, blue2, alpha2).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        float zLevel = 0.0F;

        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos((double) (left + right), (double) top, (double) zLevel).color(f1, f2, f3, f).endVertex();
        buffer.pos((double) left, (double) top, (double) zLevel).color(f1, f2, f3, f).endVertex();
        buffer.pos((double) left, (double) (top + bottom), (double) zLevel).color(f5, f6, f7, f4).endVertex();
        buffer.pos((double) (left + right), (double) (top + bottom), (double) zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawTooltipBox(int x, int y, int width, int height, int backgroundColor, int gradientColorStart, int gradientColorEnd) {
        drawGradientRect(x + 1, y, width - 1, 1, backgroundColor, backgroundColor);
        drawGradientRect(x + 1, y + height, width - 1, 1, backgroundColor, backgroundColor);
        drawGradientRect(x + 1, y + 1, width - 1, height - 1, backgroundColor, backgroundColor);
        drawGradientRect(x, y + 1, 1, height - 1, backgroundColor, backgroundColor);
        drawGradientRect(x + width, y + 1, 1, height - 1, backgroundColor, backgroundColor);
        drawGradientRect(x + 1, y + 2, 1, height - 3, gradientColorStart, gradientColorEnd);
        drawGradientRect(x + width - 1, y + 2, 1, height - 3, gradientColorStart, gradientColorEnd);
        drawGradientRect(x + 1, y + 1, width - 1, 1, gradientColorStart, gradientColorStart);
        drawGradientRect(x + 1, y + height - 1, width - 1, 1, gradientColorEnd, gradientColorEnd);
    }
}
