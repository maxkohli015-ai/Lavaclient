package de.lavaclient.mixin;

import de.lavaclient.LavaClientClient;
import de.lavaclient.module.render.CustomCrosshair;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRenderHud(DrawContext ctx, RenderTickCounter counter, CallbackInfo ci) {
        LavaClientClient lc = LavaClientClient.getInstance();
        if (lc == null) return;
        float delta = counter.getTickDelta(true);
        lc.getHudManager().render(ctx, delta);
        lc.getModuleManager().onRender(delta);
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void onRenderCrosshair(DrawContext ctx, RenderTickCounter counter, CallbackInfo ci) {
        LavaClientClient lc = LavaClientClient.getInstance();
        if (lc == null) return;
        lc.getModuleManager().getByName("CustomCrosshair").ifPresent(m -> {
            if (!m.isEnabled()) return;
            CustomCrosshair cc = (CustomCrosshair) m;
            int cx = ctx.getScaledWindowWidth() / 2;
            int cy = ctx.getScaledWindowHeight() / 2;
            int size = cc.size.getValue();
            int color = 0xFF000000 | (cc.red.getValue() << 16) | (cc.green.getValue() << 8) | cc.blue.getValue();
            ctx.fill(cx - size, cy - 1, cx + size + 1, cy + 2, color);
            ctx.fill(cx - 1,   cy - size, cx + 2,   cy + size + 1, color);
            if (cc.dot.getValue()) ctx.fill(cx, cy, cx + 1, cy + 1, color);
            ci.cancel();
        });
    }
}
