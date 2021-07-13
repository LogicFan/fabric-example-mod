package net.fabricmc.example.mixin;
import net.fabricmc.fabric.impl.item.group.FabricCreativeGuiComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FabricCreativeGuiComponents.ItemGroupButtonWidget.class)
public interface ExampleMixin20 {
    @Accessor(remap = false)
    FabricCreativeGuiComponents.Type getType();
}

