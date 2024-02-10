package com.oekt.finality.client;

import com.oekt.finality.item.custom.PowerfulSword;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.bettercombat.fabricmc.api.ClientModInitializer;
import net.minecraft.client.player.LocalPlayer;

public class FinalityClient implements ClientModInitializer {
    @Override
    public void onInitialize() {
        BetterCombatClientEvents.ATTACK_START.register(this::checkSwing);
    }
    public void checkSwing(LocalPlayer clientPlayerEntity, AttackHand attackHand){
        if(attackHand.itemStack().getItem() instanceof PowerfulSword) {

        }
    }
}
