package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CpsHud extends HudElement {
    private int cps = 0;
    private int clicks = 0;
    private long lastSecond = System.currentTimeMillis();

    public CpsHud(int x, int y) { super("CPS", x, y); }

    public void registerClick() { clicks++; }

    @Override
    public void render(DrawContext ctx, float delta) {
        long now = System.currentTimeMillis();
        if (now - lastSecond >= 1000) { cps = clicks; clicks = 0; lastSecond = now; }
        MinecraftClient mc = MinecraftClient.getInstance();
        ctx.drawTextWithShadow(mc.textRenderer, "CPS: " + cps, getX(), getY(), 0xFFFF6600);
    }

    @Override public int getWidth()  { return 60; }
    @Override public int getHeight() { return 10; }
}
