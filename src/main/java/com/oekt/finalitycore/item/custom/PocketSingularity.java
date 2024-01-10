package com.oekt.finalitycore.item.custom;

import com.oekt.finalitycore.FinalityCore;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PocketSingularity extends Item {



    public PocketSingularity(Properties p_41383_) {
        super(p_41383_);

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
        NonNullList<ItemStack> to_inv_pocket = NonNullList.withSize(300, ItemStack.EMPTY);
        ItemStack stack = player.getItemInHand(interactionHand);
       // FinalityCore.LOGGER.info(stack.getOrCreateTag().toString());
        ContainerHelper.loadAllItems(stack.getOrCreateTag().copy(), to_inv_pocket);
        //FinalityCore.LOGGER.info(to_inv_pocket.toString());
        for(ItemStack itemStack : to_inv_pocket) {

            player.getInventory().add(itemStack);
        }

        return InteractionResultHolder.consume(ItemStack.EMPTY);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        NonNullList<ItemStack> to_inv_pocket = NonNullList.withSize(300, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(stack.getOrCreateTag().copy(), to_inv_pocket);

        componentList.add(Component.literal("Size: " + get_items(to_inv_pocket) + "/300").withStyle(ChatFormatting.GRAY));
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
}
