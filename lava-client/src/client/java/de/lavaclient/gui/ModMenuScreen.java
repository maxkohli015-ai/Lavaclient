package de.lavaclient.gui;

import de.lavaclient.LavaClientClient;
import de.lavaclient.config.Setting;
import de.lavaclient.module.Module;
import de.lavaclient.util.LavaTheme;
import de.lavaclient.util.RenderUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ModMenuScreen extends Screen {

    // Layout constants
    private static final int SIDEBAR_W   = 110;
    private static final int HEADER_H    = 32;
    private static final int MODULE_H    = 22;
    private static final int SETTINGS_W  = 160;

    // Window position (draggable)
    private int winX, winY, winW, winH;

    // State
    private Module.Category selectedCategory = Module.Category.COMBAT;
    private Module selectedModule = null;
    private String searchQuery = "";
    private boolean searchFocused = false;

    // Drag state
    private boolean dragging = false;
    private int dragOffX, dragOffY;

    // Scroll
    private int moduleScrollOffset = 0;

    // Animation
    private float openAnim = 0f;

    public ModMenuScreen() {
        super(Text.literal("Lava Client"));
    }

    @Override
    protected void init() {
        winW = 520;
        winH = 340;
        winX = (width  - winW) / 2;
        winY = (height - winH) / 2;
        openAnim = 0f;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        // Animate open
        openAnim = Math.min(1f, openAnim + delta * 0.18f);
        float scale = 0.85f + 0.15f * openAnim;

        // Darken background
        ctx.fillGradient(0, 0, width, height, 0xBB000000, 0xCC000000);

        // Apply scale animation (centered on window)
        ctx.getMatrices().push();
        ctx.getMatrices().translate(winX + winW / 2f, winY + winH / 2f, 0);
        ctx.getMatrices().scale(scale, scale, 1f);
        ctx.getMatrices().translate(-(winX + winW / 2f), -(winY + winH / 2f), 0);

        drawWindow(ctx, mouseX, mouseY);

        ctx.getMatrices().pop();

        super.render(ctx, mouseX, mouseY, delta);
    }

    private void drawWindow(DrawContext ctx, int mx, int my) {
        // Window background
        RenderUtil.drawRect(ctx, winX, winY, winW, winH, LavaTheme.BG_DARK);
        RenderUtil.drawBorder(ctx, winX, winY, winW, winH, 1, LavaTheme.LAVA_ORANGE);

        drawHeader(ctx, mx, my);
        drawSidebar(ctx, mx, my);
        drawModuleList(ctx, mx, my);
        if (selectedModule != null) drawSettings(ctx, mx, my);
    }

    private void drawHeader(DrawContext ctx, int mx, int my) {
        // Header background with gradient
        ctx.fillGradient(winX, winY, winX + winW, winY + HEADER_H,
                LavaTheme.BG_ACTIVE, LavaTheme.BG_DARK);

        // Lava glow line under header
        ctx.fillGradient(winX, winY + HEADER_H - 1, winX + winW, winY + HEADER_H,
                LavaTheme.LAVA_RED, LavaTheme.LAVA_ORANGE);

        // Logo text
        ctx.drawTextWithShadow(textRenderer, "§c§l🔥 §6§lLava Client §7§lv1.0", winX + 8, winY + 10, LavaTheme.TEXT_PRIMARY);

        // Search box
        int sx = winX + winW - 140;
        int sy = winY + 8;
        int sw = 130;
        int sh = 14;
        int searchBg = searchFocused ? LavaTheme.BG_HOVER : 0xFF1C1C1C;
        RenderUtil.drawRect(ctx, sx, sy, sw, sh, searchBg);
        RenderUtil.drawBorder(ctx, sx, sy, sw, sh, 1, searchFocused ? LavaTheme.LAVA_ORANGE : 0xFF444444);
        String displaySearch = searchQuery.isEmpty() && !searchFocused ? "§7Search..." : searchQuery + (searchFocused ? "§7|" : "");
        ctx.drawTextWithShadow(textRenderer, displaySearch, sx + 4, sy + 3, LavaTheme.TEXT_PRIMARY);

        // Close button
        int cx = winX + winW - 18;
        boolean hoverClose = mx >= cx && mx < cx + 14 && my >= winY + 6 && my < winY + 22;
        RenderUtil.drawRect(ctx, cx, winY + 6, 14, 16, hoverClose ? 0xFF660000 : 0xFF2A0000);
        ctx.drawCenteredTextWithShadow(textRenderer, "§c✕", cx + 7, winY + 11, LavaTheme.TEXT_PRIMARY);
    }

    private void drawSidebar(DrawContext ctx, int mx, int my) {
        int sx = winX;
        int sy = winY + HEADER_H;
        int sh = winH - HEADER_H;

        RenderUtil.drawRect(ctx, sx, sy, SIDEBAR_W, sh, LavaTheme.BG_SIDEBAR);
        RenderUtil.drawBorder(ctx, sx + SIDEBAR_W - 1, sy, 1, sh, 1, 0xFF2A1000);

        // Enabled module count per category
        var mm = LavaClientClient.getInstance().getModuleManager();

        int iy = sy + 6;
        for (Module.Category cat : Module.Category.values()) {
            boolean selected = cat == selectedCategory;
            boolean hovered  = mx >= sx && mx < sx + SIDEBAR_W && my >= iy && my < iy + 20;

            long enabledCount = mm.getByCategory(cat).stream().filter(Module::isEnabled).count();

            if (selected) {
                ctx.fillGradient(sx, iy, sx + SIDEBAR_W, iy + 20,
                        LavaTheme.BG_ACTIVE, LavaTheme.BG_HOVER);
                RenderUtil.drawRect(ctx, sx, iy, 2, 20, LavaTheme.LAVA_ORANGE);
            } else if (hovered) {
                RenderUtil.drawRect(ctx, sx, iy, SIDEBAR_W, 20, LavaTheme.BG_HOVER);
            }

            ctx.drawTextWithShadow(textRenderer,
                    cat.icon + " " + cat.name,
                    sx + 10, iy + 6,
                    selected ? LavaTheme.LAVA_BRIGHT : LavaTheme.TEXT_SECONDARY);

            if (enabledCount > 0) {
                String badge = String.valueOf(enabledCount);
                int bw = textRenderer.getWidth(badge) + 6;
                int bx = sx + SIDEBAR_W - bw - 6;
                RenderUtil.drawRect(ctx, bx, iy + 5, bw, 10, LavaTheme.LAVA_RED);
                ctx.drawTextWithShadow(textRenderer, badge, bx + 3, iy + 6, 0xFFFFFFFF);
            }
            iy += 22;
        }
    }

    private void drawModuleList(DrawContext ctx, int mx, int my) {
        int lx = winX + SIDEBAR_W;
        int ly = winY + HEADER_H;
        int lw = winW - SIDEBAR_W - (selectedModule != null ? SETTINGS_W : 0);
        int lh = winH - HEADER_H;

        // Scissor to list area
        var mm = LavaClientClient.getInstance().getModuleManager();
        List<Module> modules = searchQuery.isEmpty()
                ? mm.getByCategory(selectedCategory)
                : mm.search(searchQuery);

        int iy = ly + 4 - moduleScrollOffset;
        for (Module mod : modules) {
            if (iy + MODULE_H < ly || iy > ly + lh) { iy += MODULE_H + 2; continue; }

            boolean hovered = mx >= lx && mx < lx + lw && my >= iy && my < iy + MODULE_H;
            boolean active  = mod.isEnabled();

            int bg = active ? LavaTheme.BG_ACTIVE : (hovered ? 0xFF1E1E1E : 0xFF161616);
            RenderUtil.drawRect(ctx, lx + 2, iy, lw - 4, MODULE_H, bg);

            // Left accent bar
            if (active) RenderUtil.drawRect(ctx, lx + 2, iy, 2, MODULE_H, LavaTheme.LAVA_ORANGE);

            // Module name
            ctx.drawTextWithShadow(textRenderer, mod.getName(), lx + 10, iy + 4, active ? LavaTheme.LAVA_BRIGHT : LavaTheme.TEXT_PRIMARY);
            ctx.drawTextWithShadow(textRenderer, "§7" + mod.getDescription().substring(0, Math.min(38, mod.getDescription().length())),
                    lx + 10, iy + 13, LavaTheme.TEXT_DISABLED);

            // Toggle pill
            int pw = 28, ph = 10;
            int px = lx + lw - pw - 8;
            int py = iy + (MODULE_H - ph) / 2;
            RenderUtil.drawRect(ctx, px, py, pw, ph, active ? LavaTheme.LAVA_RED : 0xFF333333);
            ctx.drawCenteredTextWithShadow(textRenderer, active ? "§aON" : "§cOFF", px + pw / 2, py + 1, 0xFFFFFFFF);

            // Settings arrow if module has settings
            if (!mod.getSettings().isEmpty()) {
                boolean sel = mod == selectedModule;
                ctx.drawTextWithShadow(textRenderer, sel ? "§6◀" : "§7▶", px - 12, iy + 7, LavaTheme.TEXT_SECONDARY);
            }

            iy += MODULE_H + 2;
        }
    }

    private void drawSettings(DrawContext ctx, int mx, int my) {
        if (selectedModule == null) return;
        int sx = winX + winW - SETTINGS_W;
        int sy = winY + HEADER_H;
        int sh = winH - HEADER_H;

        RenderUtil.drawRect(ctx, sx, sy, SETTINGS_W, sh, 0xFF131313);
        RenderUtil.drawBorder(ctx, sx, sy, SETTINGS_W, sh, 1, 0xFF2A1000);

        ctx.drawTextWithShadow(textRenderer, "§6⚙ " + selectedModule.getName(), sx + 6, sy + 6, LavaTheme.LAVA_BRIGHT);
        ctx.fillGradient(sx, sy + 16, sx + SETTINGS_W, sy + 17, LavaTheme.LAVA_RED, LavaTheme.LAVA_ORANGE);

        int iy = sy + 22;
        for (Setting<?> s : selectedModule.getSettings()) {
            ctx.drawTextWithShadow(textRenderer, "§7" + s.getName(), sx + 6, iy, LavaTheme.TEXT_SECONDARY);
            iy += 10;

            Object val = s.getValue();
            if (val instanceof Boolean bVal) {
                int bx = sx + 6;
                boolean hov = mx >= bx && mx < bx + 50 && my >= iy && my < iy + 12;
                RenderUtil.drawRect(ctx, bx, iy, 50, 12, bVal ? LavaTheme.BG_ACTIVE : 0xFF1E1E1E);
                RenderUtil.drawBorder(ctx, bx, iy, 50, 12, 1, bVal ? LavaTheme.LAVA_ORANGE : 0xFF444444);
                ctx.drawCenteredTextWithShadow(textRenderer, bVal ? "§aEnabled" : "§cDisabled", bx + 25, iy + 2, LavaTheme.TEXT_PRIMARY);
            } else if (val instanceof Integer iVal) {
                drawSlider(ctx, mx, my, s, sx + 6, iy, SETTINGS_W - 12, iVal, s.getMin(), s.getMax());
            } else if (val instanceof Float fVal) {
                drawSliderFloat(ctx, mx, my, s, sx + 6, iy, SETTINGS_W - 12, fVal, s.getMin(), s.getMax());
            } else if (val instanceof String strVal) {
                RenderUtil.drawRect(ctx, sx + 6, iy, SETTINGS_W - 12, 12, 0xFF1E1E1E);
                ctx.drawTextWithShadow(textRenderer, strVal, sx + 10, iy + 2, LavaTheme.TEXT_PRIMARY);
            }
            iy += 18;
            if (iy > sy + sh - 10) break;
        }
    }

    @SuppressWarnings("unchecked")
    private void drawSlider(DrawContext ctx, int mx, int my, Setting<?> s, int x, int y, int w, int val, Object minO, Object maxO) {
        if (minO == null || maxO == null) {
            ctx.drawTextWithShadow(textRenderer, "§f" + val, x, y + 2, LavaTheme.LAVA_BRIGHT);
            return;
        }
        int min = (int) minO, max = (int) maxO;
        float t = (float)(val - min) / (max - min);
        RenderUtil.drawRect(ctx, x, y + 4, w, 4, 0xFF333333);
        RenderUtil.drawRect(ctx, x, y + 4, (int)(w * t), 4, LavaTheme.LAVA_ORANGE);
        ctx.drawTextWithShadow(textRenderer, "§f" + val, x + w + 4, y + 2, LavaTheme.LAVA_BRIGHT);
    }

    @SuppressWarnings("unchecked")
    private void drawSliderFloat(DrawContext ctx, int mx, int my, Setting<?> s, int x, int y, int w, float val, Object minO, Object maxO) {
        if (minO == null || maxO == null) {
            ctx.drawTextWithShadow(textRenderer, String.format("%.1f", val), x, y + 2, LavaTheme.LAVA_BRIGHT);
            return;
        }
        float min = (float) minO, max = (float) maxO;
        float t = (val - min) / (max - min);
        RenderUtil.drawRect(ctx, x, y + 4, w, 4, 0xFF333333);
        RenderUtil.drawRect(ctx, x, y + 4, (int)(w * t), 4, LavaTheme.LAVA_ORANGE);
        ctx.drawTextWithShadow(textRenderer, String.format("%.1f", val), x + w + 4, y + 2, LavaTheme.LAVA_BRIGHT);
    }

    // ── Input handling ──────────────────────────────────────────────────────

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        int imx = (int) mx, imy = (int) my;

        // Close button
        int cx = winX + winW - 18;
        if (imx >= cx && imx < cx + 14 && imy >= winY + 6 && imy < winY + 22) {
            close(); return true;
        }

        // Search box click
        int ssx = winX + winW - 140, ssy = winY + 8;
        searchFocused = imx >= ssx && imx < ssx + 130 && imy >= ssy && imy < ssy + 14;

        // Header drag
        if (imy >= winY && imy < winY + HEADER_H && imx >= winX && imx < winX + winW - 150) {
            dragging = true; dragOffX = imx - winX; dragOffY = imy - winY; return true;
        }

        // Sidebar category click
        int sideSy = winY + HEADER_H;
        int iy = sideSy + 6;
        for (Module.Category cat : Module.Category.values()) {
            if (imx >= winX && imx < winX + SIDEBAR_W && imy >= iy && imy < iy + 20) {
                selectedCategory = cat;
                selectedModule = null;
                moduleScrollOffset = 0;
                return true;
            }
            iy += 22;
        }

        // Module list click
        var mm = LavaClientClient.getInstance().getModuleManager();
        List<Module> modules = searchQuery.isEmpty() ? mm.getByCategory(selectedCategory) : mm.search(searchQuery);
        int lx = winX + SIDEBAR_W;
        int ly = winY + HEADER_H;
        int lw = winW - SIDEBAR_W - (selectedModule != null ? SETTINGS_W : 0);
        int miy = ly + 4 - moduleScrollOffset;
        for (Module mod : modules) {
            if (imx >= lx && imx < lx + lw && imy >= miy && imy < miy + MODULE_H) {
                // Toggle pill area
                int pw = 28, ph = 10;
                int px = lx + lw - pw - 8;
                if (imx >= px && imx < px + pw) {
                    mod.toggle();
                } else if (!mod.getSettings().isEmpty()) {
                    selectedModule = (selectedModule == mod) ? null : mod;
                } else {
                    mod.toggle();
                }
                return true;
            }
            miy += MODULE_H + 2;
        }

        // Settings panel clicks (boolean toggle)
        if (selectedModule != null) {
            int ssx2 = winX + winW - SETTINGS_W;
            int ssh  = winY + HEADER_H + 22;
            for (Setting<?> s : selectedModule.getSettings()) {
                ssh += 10;
                Object val = s.getValue();
                if (val instanceof Boolean) {
                    if (imx >= ssx2 + 6 && imx < ssx2 + 56 && imy >= ssh && imy < ssh + 12) {
                        @SuppressWarnings("unchecked") Setting<Boolean> bs = (Setting<Boolean>) s;
                        bs.setValue(!bs.getValue());
                        return true;
                    }
                }
                ssh += 18;
            }
        }

        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean mouseDragged(double mx, double my, int button, double dx, double dy) {
        if (dragging) {
            winX = (int) mx - dragOffX;
            winY = (int) my - dragOffY;
            // Clamp to screen
            winX = RenderUtil.clamp(winX, 0, width  - winW);
            winY = RenderUtil.clamp(winY, 0, height - winH);
            return true;
        }
        return super.mouseDragged(mx, my, button, dx, dy);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int button) {
        dragging = false;
        return super.mouseReleased(mx, my, button);
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double hScroll, double vScroll) {
        int lx = winX + SIDEBAR_W;
        int lw = winW - SIDEBAR_W;
        if (mx >= lx && mx < lx + lw && my >= winY + HEADER_H && my < winY + winH) {
            moduleScrollOffset -= (int)(vScroll * 12);
            moduleScrollOffset = Math.max(0, moduleScrollOffset);
        }
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (searchFocused) {
            if (keyCode == 259 && !searchQuery.isEmpty()) { // backspace
                searchQuery = searchQuery.substring(0, searchQuery.length() - 1);
                moduleScrollOffset = 0;
                return true;
            }
            if (keyCode == 256) { searchFocused = false; return true; } // escape
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (searchFocused && chr >= 32) {
            searchQuery += chr;
            moduleScrollOffset = 0;
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public boolean shouldCloseOnEsc() { return true; }
}
