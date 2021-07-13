package net.fabricmc.example.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net/minecraft/client/gui/screen/ConnectScreen$1")
public class ExampleMixin15 {
    @Shadow(aliases = "field_33738")
    public MinecraftClient client;

    @Shadow(aliases = "field_33737")
    public ServerAddress address;

}