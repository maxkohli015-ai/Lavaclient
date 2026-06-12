package de.lavaclient.module;

import de.lavaclient.config.Setting;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {

    protected static final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private int keybind;
    private final List<Setting<?>> settings = new ArrayList<>();

    public enum Category {
        COMBAT("Combat", "\uD83D\uDDE1"),
        MOVEMENT("Movement", "\uD83C\uDFC3"),
        RENDER("Render", "\uD83C\uDFA8"),
        PLAYER("Player", "\uD83D\uDC64"),
        WORLD("World", "\uD83C\uDF0D"),
        MISC("Misc", "\u2699");

        public final String name;
        public final String icon;
        Category(String name, String icon) { this.name = name; this.icon = icon; }
    }

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = -1;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender(float delta) {}

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    protected <T> Setting<T> register(Setting<T> setting) {
        settings.add(setting);
        return setting;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKeybind() { return keybind; }
    public void setKeybind(int keybind) { this.keybind = keybind; }
    public List<Setting<?>> getSettings() { return settings; }
}
