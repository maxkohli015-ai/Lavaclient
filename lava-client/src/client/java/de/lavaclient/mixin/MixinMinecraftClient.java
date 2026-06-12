package de.lavaclient.mixin;

import de.lavaclient.LavaClientClient;
import de.lavaclient.gui.ModMenuScreen;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    private boolean lastRShift = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        LavaClientClient lc = LavaClientClient.getInstance();
        if (lc == null) return;

        // Module tick
        lc.getModuleManager().onTick();

        // Open mod menu with RIGHT SHIFT (toggle on press, not hold)
        MinecraftClient mc = MinecraftClient.getInstance();
        boolean rShift = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
        if (rShift && !lastRShift && mc.currentScreen == null) {
            mc.setScreen(new ModMenuScreen());
        }
        lastRShift = rShift;
    }
}
