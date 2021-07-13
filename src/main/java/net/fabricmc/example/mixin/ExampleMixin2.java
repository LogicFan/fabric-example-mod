package net.fabricmc.example.mixin;

import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.BeaconScreenHandler;
import net.minecraft.text.Text;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BeaconScreen.class)
public abstract class ExampleMixin2 extends HandledScreen<BeaconScreenHandler> {

    public ExampleMixin2(BeaconScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @ModifyArg(method = "drawBackground",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;renderInGuiWithOverrides(Lnet/minecraft/item/ItemStack;II)V"),
            index = 1, remap = true, allow = -1, require = -1, constraints = "", expect = 1)
    private int test(int x) {
        return x - 13;
    }

    @Inject(
            method = "drawBackground",
            slice = @Slice(from = @At(value = "INVOKE:LAST", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderInGuiWithOverrides(Lnet/minecraft/item/ItemStack;II)V")),
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/item/ItemRenderer;zOffset:F", opcode = Opcodes.PUTFIELD),
            constraints = "", allow = -1, expect = 1, id = "", require = -1, cancellable = false, locals = LocalCapture.NO_CAPTURE, remap = true
    )
    private void copperIngotRenderInGui(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;

        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.COPPER_INGOT), i + 42 + 88 - 13, j + 109);
    }

}