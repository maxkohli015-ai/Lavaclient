package de.lavaclient.module.render;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class MotionBlur extends Module {
    public final Setting<Float> strength = register(new Setting<>("Strength", "Blur strength", 0.5f).range(0.1f, 0.9f));
    public MotionBlur() { super("MotionBlur", "Adds a smooth motion blur effect", Category.RENDER); }
}
