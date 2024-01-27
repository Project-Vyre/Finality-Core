package com.oekt.finality;

import com.mojang.logging.LogUtils;
import com.oekt.finality.block.ModBlocks;
import com.oekt.finality.enitty.ModEnittys;
import com.oekt.finality.enitty.render.SwordPorjetileRender;
import com.oekt.finality.item.ModItems;
import com.oekt.finality.item.custom.FinalPickaxe;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Finality.MODID)
public class Finality
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "finality";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace


    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path

    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path



    public Finality()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        ModEnittys.ENTITY_TYPES.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> setPropertyOverride(ModItems.FINAL_PICKAXE.get(), new ResourceLocation(MODID, "hammer"), (itemstack, world, enitty, d) -> {
                if (itemstack.getItem() instanceof FinalPickaxe)
                    return itemstack.getOrCreateTag().getBoolean("hammer") ? 1 : 0;
                return 0;
            }));
            EntityRenderers.register(ModEnittys.SWORD_PORJECTILE.get(), SwordPorjetileRender::new);
            // Some client setup code
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIVING_NETHERWART_CROP.get(), RenderType.cutout());
        }
//        @SubscribeEvent
//        public static void onSwing(PlayerInteractEvent. event) {
//            PlayerEvent.HarvestCheck
//        }
    }
    public static void setPropertyOverride(Item itemProvider, ResourceLocation override, ItemPropertyFunction propertyGetter) {
        ItemProperties.register(itemProvider.asItem(), override, propertyGetter);
    }

}
