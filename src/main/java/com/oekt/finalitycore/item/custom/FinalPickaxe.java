package com.oekt.finalitycore.item.custom;

import com.oekt.finalitycore.FinalityCore;
import com.oekt.finalitycore.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class FinalPickaxe extends PickaxeItem {

    public FinalPickaxe(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos blockPos, LivingEntity entity) {
        if(itemStack.getOrCreateTag().getBoolean("hammer")) {

            AABB boundingbox = InstlizeAABBBox(blockPos, 3);
            Iterable<BlockPos> list_of_blocks = GetBlocksPosAABB(boundingbox);
            boolean createNewItem = false;
           // NonNullList<ItemStack> to_inv_pocket = NonNullList.createWithCapacity(1080);
            for(BlockPos pos : list_of_blocks) {
                BlockState blockstat = level.getBlockState(pos);
                if(blockstat.is(Tags.Blocks.STONE) || blockstat.is(Tags.Blocks.ORES)) {
                    LootContext.Builder lootcontextbuilder = (new LootContext.Builder((ServerLevel) level)).withRandom(level.random).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockPos)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
                    //Gets drops
                    List<ItemStack> drops =  level.getBlockState(pos).getDrops(lootcontextbuilder);
                    //Adds Drops to Item Stack List
                    if(!addItemLoot(drops, (Player) entity)) {

                        createNewItem = true;
                    }

                    level.destroyBlock(pos, false);
                }

            }
            if(createNewItem) {
                ItemStack pocket_item = new ItemStack(ModItems.POCKET_SINGULARITY.get());
                if(pocket_item.getItem() instanceof PocketSingularity pocketSingularity) {
                   // Add loot items to Pocket Singalirty

                    if(entity instanceof Player player) {
                        boolean result = player.getInventory().add(pocket_item);
                        if(!result); {
                            dropItemStack(level, player.getX(), player.getY(), player.getZ(), pocket_item);
                        }

                    }
                }



            }

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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        CompoundTag nbt = stack.getOrCreateTag();
        if(player.isShiftKeyDown()) {
            nbt.putBoolean("hammer", !nbt.getBoolean("hammer"));
            player.setMainArm(HumanoidArm.RIGHT);
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        CompoundTag nbt = stack.getOrCreateTag();
        components.add(Component.literal("Mode: " + (nbt.getBoolean("hammer") ? "Hammer" : "Pickaxe")));
        super.appendHoverText(stack, level, components, flag);
    }
    boolean addItemLoot(List<ItemStack> loot, Player player) {
        boolean stop = false;
        for(int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            Item item = stack.getItem();


            if(item instanceof PocketSingularity pocketSingularity) {

                //NonNullList<ItemStack> pocket_SIBGELARTY_storege = NonNullList.withSize(1080, ItemStack.EMPTY);


                    for (int e = 0; e < pocketSingularity.invetory.getSlots(); e++) {
                        ItemStack stackTarget = pocketSingularity.invetory.getStackInSlot(e);
                        if(stackTarget.isEmpty()) {
                            for(ItemStack itemStackLoot : loot) {
                                if(!itemStackLoot.isEmpty()) {

                                    pocketSingularity.invetory.setStackInSlot(e, itemStackLoot.copy());
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }

                        FinalityCore.LOGGER.info("You spin");
                    }






                stack.getOrCreateTag().putBoolean("modifyed", true);
                stop = true;
                break;
            }
        }
       if(stop) {
           return true;
       }
       return false;
    }
    public static void dropItemStack(Level p_18993_, double p_18994_, double p_18995_, double p_18996_, ItemStack p_18997_) {
        double d0 = (double) EntityType.ITEM.getWidth();
        double d1 = 1.0D - d0;
        double d2 = d0 / 2.0D;
        double d3 = Math.floor(p_18994_) + p_18993_.random.nextDouble() * d1 + d2;
        double d4 = Math.floor(p_18995_) + p_18993_.random.nextDouble() * d1;
        double d5 = Math.floor(p_18996_) + p_18993_.random.nextDouble() * d1 + d2;

        while (!p_18997_.isEmpty()) {
            ItemEntity itementity = new ItemEntity(p_18993_, d3, d4, d5, p_18997_.split(1));
            float f = 0.05F;
            itementity.setDeltaMovement(p_18993_.random.triangle(0.0D, 0.11485000171139836D), p_18993_.random.triangle(0.2D, 0.11485000171139836D), p_18993_.random.triangle(0.0D, 0.11485000171139836D));
            p_18993_.addFreshEntity(itementity);
        }
    }

}
