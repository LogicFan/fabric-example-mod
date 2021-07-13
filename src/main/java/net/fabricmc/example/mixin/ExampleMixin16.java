package net.fabricmc.example.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;


/**
 *
 * @author sayeeed
 *
 */
@Mixin(SinglePoolElement.class)
public abstract class ExampleMixin16 {
    @Shadow(prefix = "shadow$") protected abstract StructurePlacementData shadow$createPlacementData(BlockRotation blockRotation, BlockBox blockBox, boolean keepJigsaws);
}