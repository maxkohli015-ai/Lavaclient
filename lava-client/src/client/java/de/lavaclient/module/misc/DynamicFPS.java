package de.lavaclient.module.misc;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class DynamicFPS extends Module {
    public final Setting<Integer> backgroundFps = register(new Setting<>("Background FPS", "FPS cap when window is unfocused", 15).range(1, 60));
    public DynamicFPS() { super("DynamicFPS", "Reduces FPS when Minecraft is in the background", Category.MISC); }
}
