package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

public class PingHud extends HudElement {
    public PingHud(int x, int y) { super("Ping", x, y); }

    @Override
    public void render(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        int ping = 0;
        if (mc.player != null && mc.getNetworkHandler() != null) {
            PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
            if (entry != null) ping = entry.getLatency();
        }
        int color = ping < 80 ? 0xFF00FF00 : ping < 150 ? 0xFFFFAA00 : 0xFFFF3300;
        ctx.drawTextWithShadow(mc.textRenderer, "Ping: " + ping + "ms", getX(), getY(), color);
    }

    @Override public int getWidth()  { return 80; }
    @Override public int getHeight() { return 10; }
}
