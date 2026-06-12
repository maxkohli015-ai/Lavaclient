package de.lavaclient.module.combat;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class HitColor extends Module {
    public final Setting<Integer> red = register(new Setting<>("Red", "Red value", 255).range(0, 255));
    public final Setting<Integer> green = register(new Setting<>("Green", "Green value", 0).range(0, 255));
    public final Setting<Integer> blue = register(new Setting<>("Blue", "Blue value", 0).range(0, 255));
    public final Setting<Float> duration = register(new Setting<>("Duration", "Flash duration (ticks)", 5f).range(1f, 20f));

    public HitColor() { super("HitColor", "Changes the color of entity hurt flashes", Category.COMBAT); }
}
