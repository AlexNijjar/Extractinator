package dev.alexnijjar.extractinator.forge;

import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import dev.alexnijjar.extractinator.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Extractinator.MOD_ID)
public class ExtractinatorForge {
    public ExtractinatorForge() {
        Extractinator.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ExtractinatorClientForge::init);
        bus.addListener(ExtractinatorForge::onClientSetup);
        bus.addListener(ExtractinatorForge::onRegisterCreativeTabs);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ExtractinatorClient.initializeClient();
        ExtractinatorClientForge.postInit();
    }

    public static void onRegisterCreativeTabs(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            ModItems.onRegisterFunctionalCreativeTabs(event::accept);
        }
        if (event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            ModItems.onRegisterNaturalCreativeTabs(event::accept);
        }
    }
}
