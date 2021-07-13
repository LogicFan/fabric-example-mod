package net.fabricmc.example.mixin;


import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;
import java.util.function.Supplier;

@Mixin(HopperBlockEntity.class)
public class ExampleMixin12 {
    @Shadow private int transferCooldown;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2614; method_11238(I)V", remap = false), method = "method_11243(Ljava/util/function/Supplier;)Z", remap = false, allow
    = -1, constraints = "", expect = 1, require = -1)
    private void insertAndExtract_setCooldown(HopperBlockEntity thiz, int cooldown, Supplier<Boolean> extractMethod) {
        transferCooldown = Objects.requireNonNull(thiz.getWorld()).getGameRules().getInt(null);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2614; method_5434(II)Lnet/minecraft/class_1799;", remap = false), method = "method_11246()Z", remap = false)
    private ItemStack insert_removeStack(HopperBlockEntity thiz, int slot, int amount) {
        return thiz.removeStack(slot, Objects.requireNonNull(thiz.getWorld()).getGameRules().getInt(null));
    }
}