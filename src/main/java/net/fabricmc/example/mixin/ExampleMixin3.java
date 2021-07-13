package net.fabricmc.example.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.enchantment.EnchantmentTarget$11")
public abstract class ExampleMixin3 {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true, constraints = "", allow = -1, expect = 1, id = "", require = -1, remap = true)
    private void changeWeaponCondition(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item == Items.CARROT) cir.setReturnValue(true);
    }
}

