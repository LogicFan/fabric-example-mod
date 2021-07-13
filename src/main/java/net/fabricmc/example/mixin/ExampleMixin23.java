package net.fabricmc.example.mixin;

import net.fabricmc.example.InterfaceTest;
import net.fabricmc.example.hj;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;

@Mixin(hj.class)
@Implements(@Interface(iface= InterfaceTest.class, prefix="if$"))
public abstract class ExampleMixin23 {
    @Shadow(remap = false)
    @Final
    private String agh;

    public String if$ghj() {
        return this.agh;
    }

}