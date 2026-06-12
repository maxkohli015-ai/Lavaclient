package de.lavaclient.module.movement;

import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;

public class Zoom extends Module {
    public final Setting<Float> zoomLevel = register(new Setting<>("Zoom Level", "How much to zoom in", 4.0f).range(1.5f, 10.0f));
    public final Setting<Boolean> smooth = register(new Setting<>("Smooth Zoom", "Smooth zoom animation", true));

    private float currentFov = 70f;
    private boolean zooming = false;

    public Zoom() { super("Zoom", "Zoom in like a spyglass (press C)", Category.MOVEMENT); }

    public boolean isZooming() { return zooming; }
    public void setZooming(boolean v) { this.zooming = v; }
    public float getCurrentFov() { return currentFov; }
    public void setCurrentFov(float v) { this.currentFov = v; }
}
