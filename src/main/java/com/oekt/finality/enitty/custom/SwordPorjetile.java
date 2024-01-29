package com.oekt.finality.enitty.custom;

import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class SwordPorjetile extends AbstractHurtingProjectile {
    int DESPAWN_TIME = 300;
    int timer = 300;

    public SwordPorjetile(EntityType<? extends AbstractHurtingProjectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }



    @Override
    protected void defineSynchedData() {

    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public void tick() {
        if(timer <= 0) {
            this.discard();

        } else {
            timer--;
        }




        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        if(target instanceof LivingEntity entity) {
            if(target instanceof Player) {
                super.onHitEntity(result);
                return;
            } else {
                entity.setHealth(0);
            }

        }

        super.onHitEntity(result);
    }
    // in ExplosiveArrowEntity.java
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
