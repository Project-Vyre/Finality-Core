package com.oekt.finalitycore.item.custom;

import com.oekt.finalitycore.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FinalPickaxe extends PickaxeItem {

    public FinalPickaxe(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos blockPos, LivingEntity entity) {
        AABB boundingbox = InstlizeAABBBox(blockPos, 3);
        Iterable<BlockPos> list_of_blocks = GetBlocksPosAABB(boundingbox);
        NonNullList<ItemStack> to_inv_pocket = NonNullList.createWithCapacity(300);
        for(BlockPos pos : list_of_blocks) {

            LootContext.Builder lootcontextbuilder = (new LootContext.Builder((ServerLevel) level)).withRandom(level.random).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockPos)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
            List<ItemStack> drops =  level.getBlockState(pos).getBlock().getDrops(state, lootcontextbuilder);
            to_inv_pocket.addAll(drops);

            level.destroyBlock(pos, false);
        }
        ItemStack pocket_item = new ItemStack(ModItems.POCKET_SINGULARITY.get());

            CompoundTag nbt = pocket_item.getOrCreateTag();
            CompoundTag nbt_inv = ContainerHelper.saveAllItems(nbt, to_inv_pocket, true);

            if(entity instanceof Player player) {
                player.getInventory().add(pocket_item);

            }



        return super.mineBlock(itemStack, level, state, blockPos, entity);
    }
    public AABB InstlizeAABBBox(BlockPos pos ,int offset){

        BlockPos cornerTop = pos.offset(offset, offset, offset);
        BlockPos cornerBottom = pos.offset(-offset, 0, -offset);
        AABB box = new AABB(cornerTop, cornerBottom);
        return box;
    }
    Iterable<BlockPos> GetBlocksPosAABB(AABB aabb) {
        return BlockPos.betweenClosed((int)aabb.minX, (int)aabb.minY, (int)aabb.minZ,
                (int)aabb.maxX,  (int)aabb.maxY,  (int)aabb.maxZ);

    }
}
