package de.lavaclient.util;

import net.minecraft.client.gui.DrawContext;

public class RenderUtil {

    public static void drawRect(DrawContext ctx, int x, int y, int w, int h, int color) {
        ctx.fill(x, y, x + w, y + h, color);
    }

    public static void drawBorder(DrawContext ctx, int x, int y, int w, int h, int thickness, int color) {
        ctx.fill(x,             y,             x + w,          y + thickness, color); // top
        ctx.fill(x,             y + h - thickness, x + w,     y + h,         color); // bottom
        ctx.fill(x,             y,             x + thickness,  y + h,         color); // left
        ctx.fill(x + w - thickness, y,         x + w,          y + h,         color); // right
    }

    public static void drawGradientHorizontal(DrawContext ctx, int x, int y, int w, int h, int colorLeft, int colorRight) {
        ctx.fillGradient(x, y, x + w, y + h, colorLeft, colorRight);
    }

    public static void drawGradientVertical(DrawContext ctx, int x, int y, int w, int h, int colorTop, int colorBottom) {
        ctx.fillGradient(x, y, x + w, y + h, colorTop, colorBottom);
    }

    /** Clamps a value between min and max */
    public static int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }

    public static float clampF(float v, float min, float max) {
        return Math.max(min, Math.min(max, v));
    }
}
