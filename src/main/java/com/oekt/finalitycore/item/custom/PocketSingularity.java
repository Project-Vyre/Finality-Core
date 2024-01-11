package com.oekt.finalitycore.item.custom;

import com.oekt.finalitycore.FinalityCore;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PocketSingularity extends Item {


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size = 0;
    public PocketSingularity(Properties p_41383_, int size) {
        super(p_41383_);
        this.size = size;
    }

//    public ItemStack createStorege(NonNullList<ItemStack> loot) {
//        Item item = this.asItem();
//        ItemStack stack = item.getDefaultInstance();
//        CompoundTag nbt = stack.getOrCreateTag();
//        ContainerHelper.saveAllItems(nbt, loot, true);
//
//        return stack;
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        NonNullList<ItemStack> to_inv_pocket = NonNullList.withSize(1080, ItemStack.EMPTY);
        ItemStack stack = player.getItemInHand(interactionHand);
       // FinalityCore.LOGGER.info(stack.getOrCreateTag().toString());
        ContainerHelper.loadAllItems(stack.getOrCreateTag().copy(), to_inv_pocket);
        //FinalityCore.LOGGER.info(to_inv_pocket.toString());
        for(ItemStack itemStack : to_inv_pocket) {

            if(!player.getInventory().add(itemStack)) {
                dropItemStack(level, player.getX(), player.getY(), player.getZ(), itemStack);
            }
        }
        this.setSize(get_items(to_inv_pocket));
        return InteractionResultHolder.consume(ItemStack.EMPTY);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        NonNullList<ItemStack> to_inv_pocket = NonNullList.withSize(1080, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(stack.getOrCreateTag().copy(), to_inv_pocket);

        componentList.add(Component.literal("Size: " + get_items(to_inv_pocket) + "/1080").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, componentList, tooltipFlag);
    }
    int get_items(NonNullList<ItemStack> itemStacks) {
        int count = 0;
        for(int i = 0; i < itemStacks.size(); i++) {
            if(!itemStacks.get(i).isEmpty()) {
                count++;
            }
        }
        return count;
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
