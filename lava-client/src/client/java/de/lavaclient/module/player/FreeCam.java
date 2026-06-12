package de.lavaclient.module.player;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class FreeCam extends Module {
    public final Setting<Float> speed = register(new Setting<>("Speed", "Camera speed", 1.0f).range(0.1f, 5.0f));
    public FreeCam() { super("FreeCam", "Lets you fly the camera freely without moving your player", Category.PLAYER); }
}
