package de.lavaclient.module.movement;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class Sneak extends Module {
    public Sneak() { super("ToggleSneak", "Toggles sneak without holding the key", Category.MOVEMENT); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        mc.options.sneakKey.setPressed(true);
    }

    @Override
    public void onDisable() {
        if (mc.options != null) mc.options.sneakKey.setPressed(false);
    }
}
