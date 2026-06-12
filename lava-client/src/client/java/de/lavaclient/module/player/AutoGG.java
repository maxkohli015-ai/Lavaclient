package de.lavaclient.module.player;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class AutoGG extends Module {
    public final Setting<String> message = register(new Setting<>("Message", "Message to send", "gg"));
    public final Setting<Integer> delay  = register(new Setting<>("Delay", "Delay in ticks", 20).range(0, 100));
    public AutoGG() { super("AutoGG", "Automatically sends GG after a game ends", Category.PLAYER); }
}
