package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CoordsHud extends HudElement {
    public CoordsHud(int x, int y) { super("Coordinates", x, y); }

    @Override
    public void render(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        int bx = (int) mc.player.getX();
        int by = (int) mc.player.getY();
        int bz = (int) mc.player.getZ();
        ctx.drawTextWithShadow(mc.textRenderer,
            "X: " + bx + "  Y: " + by + "  Z: " + bz,
            getX(), getY(), 0xFFFFAA00);
    }

    @Override public int getWidth()  { return 130; }
    @Override public int getHeight() { return 10; }
}
