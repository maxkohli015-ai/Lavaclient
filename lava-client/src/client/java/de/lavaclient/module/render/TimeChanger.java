package de.lavaclient.module.render;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class TimeChanger extends Module {
    public final Setting<Integer> time = register(new Setting<>("Time", "World time (0=dawn, 6000=noon, 12000=dusk, 18000=night)", 6000).range(0, 24000));
    public TimeChanger() { super("TimeChanger", "Changes the visual time of day (client-side only)", Category.RENDER); }
}
