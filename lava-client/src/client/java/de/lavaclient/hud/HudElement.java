package de.lavaclient.hud;

public abstract class HudElement {
    private String name;
    private int x, y;
    private boolean visible;
    private float scale;

    public HudElement(String name, int defaultX, int defaultY) {
        this.name = name;
        this.x = defaultX;
        this.y = defaultY;
        this.visible = true;
        this.scale = 1.0f;
    }

    public abstract void render(net.minecraft.client.gui.DrawContext context, float delta);
    public abstract int getWidth();
    public abstract int getHeight();

    public String getName()    { return name; }
    public int getX()          { return x; }
    public int getY()          { return y; }
    public void setX(int x)    { this.x = x; }
    public void setY(int y)    { this.y = y; }
    public boolean isVisible() { return visible; }
    public void setVisible(boolean v) { this.visible = v; }
    public float getScale()    { return scale; }
    public void setScale(float s) { this.scale = s; }
}
