package com.alxtray.minecraftbuddy.mixin.client;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
abstract class FrameGrabberMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void captureRenderedFrame(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        
    }
}