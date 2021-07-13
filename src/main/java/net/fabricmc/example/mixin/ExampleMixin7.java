package net.fabricmc.example.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@SuppressWarnings("unused")
@Mixin(LivingEntity.class)
public abstract class ExampleMixin7 extends Entity {

    public ExampleMixin7(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }


    @ModifyConstant(method = "blockedByShield", slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;dotProduct(Lnet/minecraft/util/math/Vec3d;)D")
    ))
    double modifyShieldEffectiveAngle(double original) {
        return -0.15;
    }
}