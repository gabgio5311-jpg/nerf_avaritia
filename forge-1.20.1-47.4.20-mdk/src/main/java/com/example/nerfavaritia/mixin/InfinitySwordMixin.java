package com.example.nerfavaritia.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Classe real do Re-Avaritia (confirmada no codigo decompilado).
// O dano infinito acontece em onLeftClickEntity, que chama hurt(victim, source, Float.MAX_VALUE).
@Mixin(targets = "committee.nova.mods.avaritia.common.item.tools.infinity.InfinitySwordItem")
public class InfinitySwordMixin {

    // onLeftClickEntity e um metodo do Forge (nao obfuscado), por isso remap = false.
    @Inject(method = "onLeftClickEntity", at = @At("HEAD"), cancellable = true, remap = false)
    private void nerfAvaritia$onLeftClick(ItemStack stack, Player player, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        // So roda no servidor
        if (player.level().isClientSide) {
            return;
        }
        if (entity instanceof LivingEntity target) {
            // Aplica um dano fixo e razoavel em vez do dano infinito
            target.hurt(player.damageSources().playerAttack(player), 20.0f);
        }
        // Retornar true cancela todo o processamento original (corta o dano infinito do Avaritia)
        cir.setReturnValue(true);
    }
}
