package net.fabricmc.example.mixin;

import net.minecraft.server.world.ServerChunkManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerChunkManager.class)
public interface ExampleMixin19 {
    @Invoker("tick")
    @SuppressWarnings("UnusedReturnValue")
    public boolean tick();
}