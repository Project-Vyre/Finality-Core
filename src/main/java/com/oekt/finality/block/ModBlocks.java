package com.oekt.finality.block;

import com.oekt.finality.Finality;
import com.oekt.finality.block.custom.LivingNetherwarts;
import com.oekt.finality.block.custom.TauBacktankBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Finality.MODID);
    public static final RegistryObject<Block> LIVING_NETHERWART_CROP = BLOCKS.register("crop_living_nether_wart", () -> new LivingNetherwarts(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
   // public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new (BlockBehaviour.Properties.of(Material.STONE))));
   public static final RegistryObject<Block> TAU_BACKTANK_BLOCK = BLOCKS.register("tau_backtank", () -> new TauBacktankBlock(BlockBehaviour.Properties.of(Material.STONE)));

}
