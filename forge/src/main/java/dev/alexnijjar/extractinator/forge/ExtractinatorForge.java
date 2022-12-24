package dev.alexnijjar.extractinator.forge;

import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import dev.alexnijjar.extractinator.config.forge.ForgeMenuConfig;
import dev.alexnijjar.extractinator.registry.forge.RegistryHelpersImpl;
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
        RegistryHelpersImpl.REGISTRIES.values().forEach(deferredRegister -> deferredRegister.register(bus));
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ForgeMenuConfig::register);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ExtractinatorClient.initializeClient();
        ExtractinatorClientForge.postInit();
    }
}
