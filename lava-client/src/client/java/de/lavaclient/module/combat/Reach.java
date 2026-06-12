package de.lavaclient.module.combat;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

/** Visualizes hit feedback with custom colors - purely cosmetic. */
public class Reach extends Module {
    public final Setting<Boolean> showIndicator = register(new Setting<>("Show Indicator", "Show reach distance indicator", true));

    public Reach() { super("ReachDisplay", "Displays your current attack reach distance in HUD", Category.COMBAT); }
}
