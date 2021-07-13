package net.fabricmc.example.mixin;

import net.minecraft.block.entity.StructureBlockBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = StructureBlockBlockEntity.class, priority = 999)
public class ExampleMixin6 {

    @ModifyConstant(method = "readNbt", constant = @Constant(intValue = 48), require = 0, remap = true, allow = -1, expect = 1)
    public int readNbtUpper(int value) {
        return 512;
    }

    @ModifyConstant(method = "readNbt", constant = @Constant(intValue = -48), require = 0, remap = true, allow = -1, expect = 1)
    public int readNbtLower(int value) {
        return -512;
    }

    @ModifyConstant(method = "detectStructureSize", constant = @Constant(intValue = 80), require = 0, remap = true, allow = -1, expect = 1)
    public int detectSize(int value) {
        return 256;
    }
}