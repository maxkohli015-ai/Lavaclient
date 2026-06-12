package de.lavaclient.module.render;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class CustomCrosshair extends Module {
    public final Setting<Integer> red   = register(new Setting<>("Red",   "Red",   255).range(0, 255));
    public final Setting<Integer> green = register(new Setting<>("Green", "Green", 80).range(0, 255));
    public final Setting<Integer> blue  = register(new Setting<>("Blue",  "Blue",  0).range(0, 255));
    public final Setting<Integer> size  = register(new Setting<>("Size",  "Crosshair size", 5).range(2, 20));
    public final Setting<Boolean> dot   = register(new Setting<>("Dot",   "Show center dot", true));
    public CustomCrosshair() { super("CustomCrosshair", "Replaces the default crosshair", Category.RENDER); }
}
