package de.lavaclient.mixin;

import de.lavaclient.LavaClientClient;
import de.lavaclient.module.movement.Zoom;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyReturnValue;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @ModifyReturnValue(method = "getFov", at = @At("RETURN"))
    private double onGetFov(double original) {
        LavaClientClient lc = LavaClientClient.getInstance();
        if (lc == null) return original;
        return lc.getModuleManager().getByName("Zoom").map(m -> {
            if (!m.isEnabled()) return original;
            Zoom zoom = (Zoom) m;
            double target = original / zoom.zoomLevel.getValue();
            if (zoom.smooth.getValue()) {
                float cur = zoom.getCurrentFov();
                if (cur <= 1f) cur = (float) original;
                cur += (target - cur) * 0.15f;
                zoom.setCurrentFov(cur);
                return (double) cur;
            }
            return target;
        }).orElse(original);
    }
}
