package com.oekt.finality.item.custom;

import com.oekt.finality.Finality;

import com.oekt.finality.enitty.ModEnittys;
import com.oekt.finality.enitty.custom.SwordPorjetile;
import com.oekt.finality.networking.ModMesseges;
import com.oekt.finality.networking.packet.SwordPacket;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PowerfulSword extends SwordItem implements BetterCombatClientEvents.PlayerAttackHit {
    public PowerfulSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity livingEntity) {
        entity.setHealth(0);
        entity.die(DamageSource.playerAttack((Player) livingEntity));
        return false;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity player) {
        if(player.level.isClientSide) {return false;}
        Level level = player.level;
        SwordPorjetile porjectile = new SwordPorjetile(ModEnittys.SWORD_PORJECTILE.get(), player.level);
        porjectile.setPos(player.position().add(0, 1, 0));
        porjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);
        porjectile.setOwner(player);
        //porjectile.setDeltaMovement(0, 1, 0);



        level.addFreshEntity(porjectile);

        return super.onEntitySwing(stack, player);
    }



    public static void spawnSlash(ServerPlayer localPlayer) {
        if(localPlayer.level.isClientSide) {return;}
        Finality.LOGGER.info("pew");

        if(localPlayer.getMainHandItem().getItem() instanceof PowerfulSword) {

            Level level = localPlayer.level;
            SwordPorjetile porjectile = new SwordPorjetile(ModEnittys.SWORD_PORJECTILE.get(), localPlayer.level);
            porjectile.setPos(localPlayer.position().add(0, 1, 0));
            porjectile.shootFromRotation(localPlayer, localPlayer.getXRot(), localPlayer.getYRot(), 0.0F, 2.0F, 1.0F);
            porjectile.setOwner(localPlayer);
            //porjectile.setDeltaMovement(0, 1, 0);



            level.addFreshEntity(porjectile);
        }
    }

    @Override
    public void onPlayerAttackStart(LocalPlayer localPlayer, AttackHand attackHand, List<Entity> list, @Nullable Entity entity) {
        Finality.LOGGER.info("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    }
    public static void onPlayerSwing(Player player, AttackHand attackHand) {
        // Send Packet to server
        ModMesseges.sendToServer(new SwordPacket());
    }

}
