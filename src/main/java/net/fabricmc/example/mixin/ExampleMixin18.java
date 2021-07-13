package net.fabricmc.example.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Invoker;

@Pseudo
@Mixin(targets = "net.optifine.render.VboRegion", remap = false)
public interface ExampleMixin18
{
    @Invoker(value = "deleteGlBuffers", remap = false)
    void ip_deleteGlBuffers();
}