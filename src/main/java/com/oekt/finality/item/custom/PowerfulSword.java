package com.oekt.finality.item.custom;

import com.oekt.finality.Finality;
import com.oekt.finality.enitty.ModEnittys;
import com.oekt.finality.enitty.custom.SwordPorjetile;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PowerfulSword extends SwordItem {
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
        porjectile.setPos(player.position());
        porjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);
        porjectile.setOwner(player);
        //porjectile.setDeltaMovement(0, 1, 0);



        level.addFreshEntity(porjectile);

        return super.onEntitySwing(stack, player);
    }








}
