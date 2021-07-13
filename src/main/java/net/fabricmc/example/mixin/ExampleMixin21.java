package net.fabricmc.example.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class ExampleMixin21 {
    //zooms in screen
    @Inject(at = @At("HEAD"), method = "updateMovementFovMultiplier", cancellable = true)
    private void updateMovementFovMultiplier(CallbackInfo info) {
        if(MinecraftClient.getInstance().player != null) {
            if (MinecraftClient.getInstance().player.isUsingSpyglass() && MinecraftClient.getInstance().options.getPerspective().isFirstPerson()) {

                setLastMovementFovMultiplier(getFovMultiplier());
                setFovMultiplier(getFovMultiplier());

                info.cancel();
            }
        }
    }

    @Accessor(value = "movementFovMultiplier")
    public abstract void setFovMultiplier(float fov);

    @Accessor(value = "movementFovMultiplier")
    public abstract float getFovMultiplier();

    @Accessor(value = "lastMovementFovMultiplier")
    public abstract void setLastMovementFovMultiplier(float fov);
}
