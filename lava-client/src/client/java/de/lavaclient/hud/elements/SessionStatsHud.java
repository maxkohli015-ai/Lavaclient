package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class SessionStatsHud extends HudElement {
    private int kills = 0, deaths = 0;
    private final long sessionStart = System.currentTimeMillis();

    public SessionStatsHud(int x, int y) { super("SessionStats", x, y); }

    public void addKill()  { kills++; }
    public void addDeath() { deaths++; }

    @Override
    public void render(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        long seconds = (System.currentTimeMillis() - sessionStart) / 1000;
        String time = String.format("%02d:%02d", seconds / 60, seconds % 60);
        ctx.drawTextWithShadow(mc.textRenderer, "K: " + kills + "  D: " + deaths + "  " + time, getX(), getY(), 0xFFFF4400);
    }

    @Override public int getWidth()  { return 120; }
    @Override public int getHeight() { return 10; }
}
