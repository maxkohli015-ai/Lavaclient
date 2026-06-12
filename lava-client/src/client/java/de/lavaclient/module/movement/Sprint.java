package de.lavaclient.module.movement;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class Sprint extends Module {
    public final Setting<Boolean> omniSprint = register(new Setting<>("Omni-Sprint", "Sprint in all directions", false));

    public Sprint() { super("ToggleSprint", "Automatically keeps sprinting active", Category.MOVEMENT); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (!mc.player.isSprinting()) {
            boolean moving = mc.options.forwardKey.isPressed() || (omniSprint.getValue() &&
                    (mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed() || mc.options.backKey.isPressed()));
            if (moving) mc.player.setSprinting(true);
        }
    }
}
