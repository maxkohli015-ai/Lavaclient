package de.lavaclient.module.render;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class WeatherChanger extends Module {
    public final Setting<String> weather = register(new Setting<>("Weather", "clear/rain/thunder", "clear"));
    public WeatherChanger() { super("WeatherChanger", "Changes the visual weather (client-side only)", Category.RENDER); }
}
