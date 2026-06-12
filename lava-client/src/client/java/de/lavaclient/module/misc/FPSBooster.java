package de.lavaclient.module.misc;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class FPSBooster extends Module {
    public final Setting<Boolean> reduceFog    = register(new Setting<>("Reduce Fog",    "Reduces fog calculations",    true));
    public final Setting<Boolean> reduceParticles = register(new Setting<>("Reduce Particles", "Limits particle count", true));
    public FPSBooster() { super("FPSBooster", "Various tweaks to improve frame rate", Category.MISC); }
}
