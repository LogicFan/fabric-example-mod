package net.fabricmc.example.mixin;
import net.minecraft.client.particle.ParticleManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
@SuppressWarnings("unused")
@Mixin(ParticleManager.class)
public class ExampleMixin10 {
    @SuppressWarnings("UnresolvedMixinReference")
    @ModifyArg(
            remap = false,
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/EvictingQueue;create(I)Lcom/google/common/collect/EvictingQueue;"),
            method = "method_18125"
    )
    private static int modifyMaxParticleCount(int value) {
        return 200000;
    }
}
