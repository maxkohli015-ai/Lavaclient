package de.lavaclient.module.player;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class AutoText extends Module {
    public final Setting<String> trigger  = register(new Setting<>("Trigger",  "Keyword to watch for", "hello"));
    public final Setting<String> response = register(new Setting<>("Response", "Auto-reply text",      "Hey!"));
    public AutoText() { super("AutoText", "Automatically replies to chat messages containing a keyword", Category.PLAYER); }
}
