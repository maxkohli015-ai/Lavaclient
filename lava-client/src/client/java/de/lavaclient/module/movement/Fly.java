package de.lavaclient.module.movement;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

/** Creative-style fly for singleplayer / servers with permission. */
public class Fly extends Module {
    public final Setting<Float> speed = register(new Setting<>("Speed", "Fly speed multiplier", 1.0f).range(0.5f, 5.0f));

    public Fly() { super("Fly", "Enables creative-style flying (singleplayer/permitted servers only)", Category.MOVEMENT); }

    @Override
    public void onEnable() {
        if (mc.player != null) mc.player.getAbilities().allowFlying = true;
    }

    @Override
    public void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
    }
}
