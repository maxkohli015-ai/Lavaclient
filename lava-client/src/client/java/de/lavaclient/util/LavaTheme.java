package de.lavaclient.util;

public class LavaTheme {
    // Primary lava colors
    public static final int LAVA_RED        = 0xFFCC2200;
    public static final int LAVA_ORANGE     = 0xFFFF6600;
    public static final int LAVA_BRIGHT     = 0xFFFF9933;
    public static final int LAVA_GLOW       = 0xFFFFCC44;

    // Background colors
    public static final int BG_DARK         = 0xFF0D0D0D;
    public static final int BG_PANEL        = 0xFF1A1A1A;
    public static final int BG_SIDEBAR      = 0xFF111111;
    public static final int BG_HOVER        = 0xFF2A1800;
    public static final int BG_ACTIVE       = 0xFF3D1A00;

    // Text colors
    public static final int TEXT_PRIMARY    = 0xFFFFFFFF;
    public static final int TEXT_SECONDARY  = 0xFFAAAAAA;
    public static final int TEXT_ACCENT     = 0xFFFF6600;
    public static final int TEXT_DISABLED   = 0xFF555555;

    // Module enabled/disabled
    public static final int MODULE_ON       = 0xFFFF6600;
    public static final int MODULE_OFF      = 0xFF333333;

    // Alpha variants
    public static int withAlpha(int color, int alpha) {
        return (color & 0x00FFFFFF) | (alpha << 24);
    }

    public static int LAVA_ORANGE_ALPHA(int a) { return withAlpha(LAVA_ORANGE, a); }
    public static int BG_PANEL_ALPHA(int a)    { return withAlpha(BG_PANEL, a); }
}
