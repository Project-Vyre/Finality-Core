package com.oekt.finality.item;

import com.oekt.finality.Finality;
import com.oekt.finality.block.ModBlocks;
import com.oekt.finality.item.custom.PocketSingularity;
import com.oekt.finality.item.custom.FinalPickaxe;
import com.oekt.finality.item.custom.PowerfulSword;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Finality.MODID);
    public static final RegistryObject<Item> LIVING_NETHER_WART_SPORES = ITEMS.register("living_nether_wart_spores", () -> new ItemNameBlockItem(ModBlocks.LIVING_NETHERWART_CROP.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS).stacksTo(64)));
    public static final RegistryObject<Item> LIVING_NETHER_WART = ITEMS.register("living_nether_wart", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).stacksTo(64)));

    public static final RegistryObject<Item> POCKET_SINGULARITY = ITEMS.register("pocket_singularity", () -> new PocketSingularity(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).stacksTo(64)));
    public static final RegistryObject<Item> POWERFUL_SWORD = ITEMS.register("final_sword", () -> new PowerfulSword(Tiers.NETHERITE, 63, 99, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1)));

    public static final RegistryObject<Item> FINAL_PICKAXE = ITEMS.register("final_pickaxe", () -> new FinalPickaxe(Tiers.NETHERITE, 99, 99, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
}
