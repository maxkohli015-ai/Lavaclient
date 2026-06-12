package de.lavaclient.hud.elements;

import de.lavaclient.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class ArmorHud extends HudElement {
    public ArmorHud(int x, int y) { super("ArmorHUD", x, y); }

    @Override
    public void render(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        // Armor slots: 36=boots, 37=leggings, 38=chestplate, 39=helmet
        int ox = getX();
        for (int slot = 39; slot >= 36; slot--) {
            ItemStack stack = mc.player.getInventory().getStack(slot);
            if (!stack.isEmpty()) {
                ctx.drawItem(stack, ox, getY());
                ox += 18;
            }
        }
    }

    @Override public int getWidth()  { return 72; }
    @Override public int getHeight() { return 16; }
}
