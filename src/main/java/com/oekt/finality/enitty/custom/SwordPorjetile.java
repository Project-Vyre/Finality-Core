package com.oekt.finality.enitty.custom;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class SwordPorjetile extends Projectile {
    int DESPAWN_TIME = 300;
    int timer = 300;

    public SwordPorjetile(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }



    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        if(DESPAWN_TIME <= 0) {
            this.discard();

        } else {
            timer--;
        }

        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
    }
    // in ExplosiveArrowEntity.java
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
