package de.lavaclient.module.world;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class XRay extends Module {
    public final Setting<Boolean> ores = register(new Setting<>("Ores", "Show ores through walls", true));
    public XRay() { super("XRay", "Shows ores and selected blocks through walls (singleplayer)", Category.WORLD); }
}
