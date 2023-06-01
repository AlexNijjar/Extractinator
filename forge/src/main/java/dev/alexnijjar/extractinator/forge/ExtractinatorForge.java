package dev.alexnijjar.extractinator.forge;

import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import dev.alexnijjar.extractinator.common.registry.forge.ModRegistryHelpersImpl;
import net.minecraftforge.api.distmarker.Dist;
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
        ModRegistryHelpersImpl.REGISTRIES.values().forEach(deferredRegister -> deferredRegister.register(bus));
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ExtractinatorClient.initializeClient();
        ExtractinatorClientForge.postInit();
    }
}
