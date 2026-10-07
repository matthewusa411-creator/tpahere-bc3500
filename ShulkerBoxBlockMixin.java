package com.matt.shulkerdupe.mixin;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @Inject(method = "getDrops", at = @At("RETURN"), cancellable = true)
    private void shulkerDupe(BlockState state, LootParams.Builder params,
                             CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!(params.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof Player player)) {
            return;
        }

        // Creative already effectively has infinite copies, so only add the dupe in Survival/Adventure.
        if (player.isCreative()) {
            return;
        }

        List<ItemStack> original = cir.getReturnValue();
        if (original.isEmpty()) {
            return;
        }

        List<ItemStack> drops = new ArrayList<>(original);

        // Vanilla already creates the shulker item with its stored contents/components.
        // Copying that returned stack gives an exact second copy.
        for (ItemStack stack : original) {
            if (stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem
                    && blockItem.getBlock() instanceof ShulkerBoxBlock) {
                drops.add(stack.copy());
                break;
            }
        }

        cir.setReturnValue(drops);
    }
}
