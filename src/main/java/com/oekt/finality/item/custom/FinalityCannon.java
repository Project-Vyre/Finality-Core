package com.oekt.finality.item.custom;

import com.simibubi.create.content.equipment.potatoCannon.PotatoCannonItem;
import net.minecraft.world.item.ItemStack;

public class FinalityCannon extends PotatoCannonItem {
    public FinalityCannon(Properties properties) {
        super(properties);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);
    }
}
