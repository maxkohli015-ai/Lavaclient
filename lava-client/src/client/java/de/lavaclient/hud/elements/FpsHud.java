package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import de.lavaclient.util.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsHud extends HudElement {
    public FpsHud(int x, int y) { super("FPS", x, y); }

    @Override
    public void render(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        int fps = mc.getCurrentFps();
        String text = "FPS: " + fps;
        int color = fps >= 60 ? 0xFF00FF00 : fps >= 30 ? 0xFFFFAA00 : 0xFFFF3300;
        ctx.drawTextWithShadow(mc.textRenderer, text, getX(), getY(), color);
    }

    @Override public int getWidth()  { return 60; }
    @Override public int getHeight() { return 10; }
}
