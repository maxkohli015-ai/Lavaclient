package de.lavaclient.module.render;

import de.lavaclient.module.Module;

public class Fullbright extends Module {
    private double previousGamma = 1.0;

    public Fullbright() { super("Fullbright", "Makes everything fully bright - no darkness", Category.RENDER); }

    @Override
    public void onEnable() {
        if (mc.options != null) {
            previousGamma = (Double) mc.options.getGamma().getValue();
            mc.options.getGamma().setValue(10.0);
        }
    }

    @Override
    public void onDisable() {
        if (mc.options != null) {
            mc.options.getGamma().setValue(previousGamma);
        }
    }
}
