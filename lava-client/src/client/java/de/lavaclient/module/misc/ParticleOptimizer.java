package de.lavaclient.module.misc;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class ParticleOptimizer extends Module {
    public final Setting<Integer> maxParticles = register(new Setting<>("Max Particles", "Maximum particles allowed", 500).range(10, 5000));
    public ParticleOptimizer() { super("ParticleOptimizer", "Limits the number of particles to improve performance", Category.MISC); }
}
