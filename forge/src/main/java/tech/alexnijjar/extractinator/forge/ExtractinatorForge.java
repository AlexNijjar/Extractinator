package tech.alexnijjar.extractinator.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tech.alexnijjar.extractinator.Extractinator;
import tech.alexnijjar.extractinator.client.ExtractinatorClient;

@Mod(Extractinator.MOD_ID)
public class ExtractinatorForge {
    public ExtractinatorForge() {
        Extractinator.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ExtractinatorClientForge::init);
        bus.addListener(ExtractinatorForge::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ExtractinatorClient.initializeClient();
        ExtractinatorClientForge.postInit();
    }
}
