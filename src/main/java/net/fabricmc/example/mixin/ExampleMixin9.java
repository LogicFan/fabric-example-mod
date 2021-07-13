package net.fabricmc.example.mixin;


import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import com.mojang.blaze3d.systems.*;

// Lower priority to load before Bedrockify's mixin, fix for issue Ladysnake/Requiem#198
@Mixin(value = InGameHud.class, priority = 995)
public abstract class ExampleMixin9 {
    @Unique
    private static final int X_CENTER_SHIFT = 77;

    @Unique
    private boolean cancelNextItem;
    @Unique
    private boolean renderMainHandOnly;

    @Shadow
    protected abstract PlayerEntity getCameraPlayer();


    @ModifyVariable(method = "renderHotbar", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/player/PlayerEntity;getOffHandStack()Lnet/minecraft/item/ItemStack;"))
    private ItemStack lockOffHand(ItemStack base) {
        return base;
    }

    @ModifyArg(
            method = "renderHotbar",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 0),
            index = 1,
            remap = true
    )
    private int centerCroppedHotbar(int x) {
        if (this.renderMainHandOnly) {
            return x + X_CENTER_SHIFT;
        }
        return x;
    }

    @ModifyArg(
            method = "renderHotbar",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1),
            index = 1
    )
    private int centerSelectedSlot(int x) {
        if (this.renderMainHandOnly) {
            return x + X_CENTER_SHIFT;
        }
        return x;
    }

    @ModifyArg(
            method = "renderHotbar",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 0),
            index = 5
    )
    private int cropHotbar_width(int width) {
        if (this.renderMainHandOnly) {
            return 21;
        }
        return width;
    }


    @ModifyArg(
            method = "renderHotbar",
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;defaultBlendFunc()V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 1)
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V")
    )
    private ItemStack cancelLockedItemRender(ItemStack stack) {
        if (this.cancelNextItem) {
            return ItemStack.EMPTY;
        }
        return stack;
    }

    @ModifyArg(
            method = "renderHotbar",
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;defaultBlendFunc()V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 1)
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"),
            index = 0
    )
    private int shiftMainHandItem(int x) {
        if (this.renderMainHandOnly && !this.cancelNextItem) {
            return x + X_CENTER_SHIFT;
        }
        return x;
    }
}
