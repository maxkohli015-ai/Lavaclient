package de.lavaclient.hud;

import de.lavaclient.hud.elements.*;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private final List<HudElement> elements = new ArrayList<>();

    public void init() {
        elements.add(new FpsHud(2, 2));
        elements.add(new CpsHud(2, 14));
        elements.add(new PingHud(2, 26));
        elements.add(new CoordsHud(2, 38));
        elements.add(new ArmorHud(2, 60));
        elements.add(new KeystrokesHud(200, 100));
        elements.add(new SessionStatsHud(2, 80));
    }

    public void render(DrawContext context, float delta) {
        for (HudElement el : elements) {
            if (el.isVisible()) el.render(context, delta);
        }
    }

    public List<HudElement> getElements() { return elements; }
}
