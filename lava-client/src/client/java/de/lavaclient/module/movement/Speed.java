package de.lavaclient.module.movement;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class Speed extends Module {
    public final Setting<Float> multiplier = register(new Setting<>("Multiplier", "Speed multiplier", 1.3f).range(1.0f, 3.0f));

    public Speed() { super("Speed", "Increases movement speed (singleplayer only)", Category.MOVEMENT); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.isOnGround()) {
            mc.player.setVelocity(
                mc.player.getVelocity().x * multiplier.getValue(),
                mc.player.getVelocity().y,
                mc.player.getVelocity().z * multiplier.getValue()
            );
        }
    }
}
