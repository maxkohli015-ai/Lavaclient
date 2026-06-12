package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class KeystrokesHud extends HudElement {
    public KeystrokesHud(int x, int y) { super("Keystrokes", x, y); }

    @Override
    public void render(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        boolean w = mc.options.forwardKey.isPressed();
        boolean a = mc.options.leftKey.isPressed();
        boolean s = mc.options.backKey.isPressed();
        boolean d = mc.options.rightKey.isPressed();
        boolean space = mc.options.jumpKey.isPressed();

        int x = getX(), y = getY();
        drawKey(ctx, mc, "W", x + 14, y,      w);
        drawKey(ctx, mc, "A", x,      y + 14, a);
        drawKey(ctx, mc, "S", x + 14, y + 14, s);
        drawKey(ctx, mc, "D", x + 28, y + 14, d);
        drawWideKey(ctx, mc, "SPC", x, y + 28, space);
    }

    private void drawKey(DrawContext ctx, MinecraftClient mc, String label, int x, int y, boolean pressed) {
        int bg = pressed ? 0xAAFF6600 : 0xAA222222;
        ctx.fill(x, y, x + 12, y + 12, bg);
        ctx.drawTextWithShadow(mc.textRenderer, label, x + 2, y + 2, 0xFFFFFFFF);
    }

    private void drawWideKey(DrawContext ctx, MinecraftClient mc, String label, int x, int y, boolean pressed) {
        int bg = pressed ? 0xAAFF6600 : 0xAA222222;
        ctx.fill(x, y, x + 40, y + 12, bg);
        ctx.drawCenteredTextWithShadow(mc.textRenderer, label, x + 20, y + 2, 0xFFFFFFFF);
    }

    @Override public int getWidth()  { return 42; }
    @Override public int getHeight() { return 42; }
}
