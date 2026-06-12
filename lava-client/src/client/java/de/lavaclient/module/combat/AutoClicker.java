package de.lavaclient.module.combat;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

/** Auto-clicks for breaking blocks / farming - singleplayer utility. */
public class AutoClicker extends Module {
    public final Setting<Integer> cps       = register(new Setting<>("CPS", "Clicks per second", 8).range(1, 20));
    public final Setting<Boolean> breakOnly = register(new Setting<>("Break Only", "Only click while targeting a block", true));

    private int tickCounter = 0;

    public AutoClicker() { super("AutoClicker", "Automatically clicks for block breaking and farming", Category.COMBAT); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        int interval = Math.max(1, 20 / cps.getValue());
        tickCounter++;
        if (tickCounter >= interval) {
            tickCounter = 0;
            if (breakOnly.getValue()) {
                if (mc.crosshairTarget != null) {
                    mc.options.attackKey.setPressed(true);
                }
            } else {
                mc.options.attackKey.setPressed(true);
            }
        } else {
            mc.options.attackKey.setPressed(false);
        }
    }

    @Override
    public void onDisable() {
        tickCounter = 0;
        if (mc.options != null) mc.options.attackKey.setPressed(false);
    }
}
