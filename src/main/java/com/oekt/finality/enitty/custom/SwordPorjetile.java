package com.oekt.finality.enitty.custom;

import com.oekt.finality.Finality;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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

import java.util.Objects;

public class SwordPorjetile extends Projectile {
    int DESPAWN_TIME = 300;
    int timer = 600;

    int tickspeed = 3;

    float randomX;

    public SwordPorjetile(EntityType<? extends Projectile> p_37248_, Level p_37249_) {

        super(p_37248_, p_37249_);
        randomX =  RandomSource.create().nextInt(0, 360);

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
        // minecraft projectile movement code are not included within the projectile class
        Entity entity = this;
        if (!this.level.isClientSide || !entity.isRemoved() && this.level.hasChunkAt(this.blockPosition())) {


            HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }


//            Vec3 oldDeltaMovement = this.getDeltaMovement();

//            //double horzDist = oldDeltaMovement.horizontalDistance();
//            ProjectileUtil.rotateTowardsMovement(this, 0.2F);

//            Finality.LOGGER.info(String.valueOf(this.ownedBy(this)));
//            //Vec3 facingMuti = Objects.requireNonNull(this.getOwner()).getLookAngle();
//
//            this.setDeltaMovement(oldDeltaMovement.scale(0.99f));
//            Vec3 newDeltaMovement = this.getDeltaMovement();

            Vec3 oldDeltaMovement = this.getDeltaMovement();
            double newX = this.getX() + oldDeltaMovement.x;
            double newY = this.getY() + oldDeltaMovement.y;
            double newZ = this.getZ() + oldDeltaMovement.z;
           // ProjectileUtil.rotateTowardsMovement(this, 0.3f);
            //rotate towords movement
//            this.setYRot((float)(Mth.atan2(-newY, -newZ) * (double)(180F / (float)Math.PI)));
//            this.setXRot((float)(Mth.atan2(newY, oldDeltaMovement.horizontalDistance()) * (double)(180F / (float)Math.PI)));
//            this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
//            this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
//            this.setXRot(lerpRotation(this.xRotO, this.getXRot()+90));
            this.setXRot(randomX);
            this.setPos(newX, newY, newZ);
            this.checkInsideBlocks();
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
