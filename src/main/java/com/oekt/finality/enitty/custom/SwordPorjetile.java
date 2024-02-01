package com.oekt.finality.enitty.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class SwordPorjetile extends Projectile {
    int DESPAWN_TIME = 300;
    int timer = 600;

    public SwordPorjetile(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
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
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if(timer <= 0) {
                // MobGriffing check
                Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;

                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 5f, explosion$blockinteraction);
                this.discard();

            } else {
                timer--;
            }


        }
        // minecraft peojettiles movement code are not included within the projectile class
        Entity entity = this;
        if (!this.level.isClientSide || !entity.isRemoved() && this.level.hasChunkAt(this.blockPosition())) {


            HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }

            this.checkInsideBlocks();
            Vec3 vec33 = this.getDeltaMovement();
            double d0 = this.getX() + vec33.x;
            double d1 = this.getY() + vec33.y;
            double d2 = this.getZ() + vec33.z;
            ProjectileUtil.rotateTowardsMovement(this, 0.3F);

            Vec3 facingMuti = entity.getLookAngle();
            this.setDeltaMovement(vec33.add(facingMuti.scale(2)));

            //this.setPos(d0, d1, d2);
        } else {
            this.discard();
        }
    }








    @Override
    protected void onHitEntity(EntityHitResult result) {
        if(this.level.isClientSide) {return;}
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

    @Override
    protected void onHit(HitResult result) {
        if(!this.level.isClientSide) {
            this.discard();
        }

        super.onHit(result);
    }
}
