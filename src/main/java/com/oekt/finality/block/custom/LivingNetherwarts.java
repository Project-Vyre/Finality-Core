package com.oekt.finality.block.custom;

import com.oekt.finality.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LivingNetherwarts extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
    //private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)};

    public LivingNetherwarts(BlockBehaviour.Properties p_54971_) {
        super(p_54971_);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }



    protected boolean mayPlaceOn(BlockState blockState, BlockGetter p_54992_, BlockPos p_54993_) {
        return blockState.is(Blocks.SOUL_SAND);
    }


//    @Override
//    public void randomTick(BlockState p_221806_, ServerLevel p_221807_, BlockPos p_221808_, RandomSource p_221809_) {
//        int i = p_221806_.getValue(AGE);
//        if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_221807_, p_221808_, p_221806_, p_221809_.nextInt(10) == 0)) {
//            p_221806_ = p_221806_.setValue(AGE, Integer.valueOf(i + 1));
//            p_221807_.setBlock(p_221808_, p_221806_, 2);
//            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_221807_, p_221808_, p_221806_);
//        }
//
//    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.LIVING_NETHER_WART_SPORES.get();
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
