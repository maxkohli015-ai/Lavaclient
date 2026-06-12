package de.lavaclient.module.render;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class ESP extends Module {
    public final Setting<Boolean> players  = register(new Setting<>("Players",  "Highlight players",   true));
    public final Setting<Boolean> mobs     = register(new Setting<>("Mobs",     "Highlight mobs",      false));
    public final Setting<Boolean> items    = register(new Setting<>("Items",    "Highlight item drops", false));
    public ESP() { super("ESP", "Draws colored outlines around entities (visual only)", Category.RENDER); }
}
