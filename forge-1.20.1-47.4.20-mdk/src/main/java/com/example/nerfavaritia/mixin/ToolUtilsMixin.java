package com.example.nerfavaritia.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Classe utilitaria do Re-Avaritia.
// isInfinite(player) retorna true quando o jogador usa o set completo da Infinity Armor.
// Esse unico metodo e o gatilho de TODA a invencibilidade (resistencia infinita):
//   InfinityHandler.onGetHurt / onAttacked / onLivingDamage  -> cancelam/zeram o dano
//   InfinityHandler.onDeath -> cancela a morte e cura full
// Forcando o retorno para false, o jogador volta a tomar dano e morrer normalmente.
// O Infinity Totem (item separado) continua funcionando, pois usa a branch de else no onDeath.
@Mixin(targets = "committee.nova.mods.avaritia.util.ToolUtils")
public class ToolUtilsMixin {

    // isInfinite e um metodo do proprio Avaritia (nao obfuscado), por isso remap = false.
    @Inject(method = "isInfinite", at = @At("HEAD"), cancellable = true, remap = false)
    private static void nerfAvaritia$isInfinite(LivingEntity player, CallbackInfoReturnable<Boolean> cir) {
        // Remove a resistencia infinita concedida pelo set da Infinity Armor.
        cir.setReturnValue(false);
    }
}
