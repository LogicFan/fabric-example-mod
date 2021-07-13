package net.fabricmc.example.mixin;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class ExampleMixin17
{
    @Shadow(remap = false)
    private double field_5987; // UNMAPPED_FIELD
    @Shadow(remap = false)
    private double field_6010; // UNMAPPED_FIELD
    @Shadow(remap = false)
    private double field_6035; // UNMAPPED_FIELD
    @Shadow(remap = false)
    abstract Vec3d method_5812(); // UNMAPPED_METHOD

    @Unique
    protected void setPosDirectly(final double x, final double y, final double z)
    {
        field_5987 = x;
        field_6010 = y;
        field_6035 = z;
    }
}