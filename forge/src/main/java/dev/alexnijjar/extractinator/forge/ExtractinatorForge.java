package dev.alexnijjar.extractinator.forge;

import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import dev.alexnijjar.extractinator.registry.ModOres;
import dev.alexnijjar.extractinator.registry.forge.RegistryHelpersImpl;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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
        MinecraftForge.EVENT_BUS.addListener(ExtractinatorForge::onBiomeLoading);
        RegistryHelpersImpl.REGISTRIES.values().forEach(deferredRegister -> deferredRegister.register(bus));
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ExtractinatorClient.initializeClient();
        ExtractinatorClientForge.postInit();
    }

    public static void onBiomeLoading(BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(Holder.direct(ModOres.SILT_ORE.get()));
        if (event.getCategory() == Biome.BiomeCategory.ICY) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(Holder.direct(ModOres.SLUSH_ORE.get()));
        }
    }
}