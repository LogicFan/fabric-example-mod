package net.fabricmc.example.mixin;

import net.minecraft.network.PacketInflater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PacketInflater.class)
public class ExampleMixin5 {
    @ModifyConstant(method = "decode",constant = @Constant(intValue = 2097152), constraints = "", remap = true, allow = -1, expect = 1, require = -1)
    private int xlPackets(int old) {
        return Integer.MAX_VALUE;
    }
}