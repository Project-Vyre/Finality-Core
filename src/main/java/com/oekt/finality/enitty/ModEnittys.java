package com.oekt.finality.enitty;

import com.oekt.finality.Finality;
import com.oekt.finality.enitty.custom.SwordPorjetile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnittys {

        public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Finality.MODID);

    public static final RegistryObject<EntityType<SwordPorjetile>> SWORD_PORJECTILE = ENTITY_TYPES.register("sword_projectile",
            () -> EntityType.Builder.of(SwordPorjetile::new, MobCategory.MISC).sized(0.5F, 0.5F).build("sword_projectile"));
}
