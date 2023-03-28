package dev.alexnijjar.extractinator.forge;

import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import dev.alexnijjar.extractinator.config.forge.ForgeMenuConfig;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.HashMap;
import java.util.Map;

public class ExtractinatorClientForge {
    private static final Map<Item, BlockEntityWithoutLevelRenderer> ITEM_RENDERERS = new HashMap<>();
    private static boolean hasInitializedRenderers = false;

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ExtractinatorClientForge::modelLoading);
        ForgeMenuConfig.register();
    }

    public static void modelLoading(ModelEvent.RegisterAdditional event) {
        ExtractinatorClient.onRegisterModels(event::register);
    }

    @SuppressWarnings("unchecked")
    public static void postInit() {
        ExtractinatorClient.onRegisterItemRenderers((item, renderer) -> ITEM_RENDERERS.put(item.asItem(), renderer));
        ExtractinatorClient.onRegisterBlockRenderers((type, factory) -> BlockEntityRenderers.register(type.get(), factory));
        hasInitializedRenderers = true;
    }

    public static BlockEntityWithoutLevelRenderer getItemRenderer(ItemLike item) {
        return ITEM_RENDERERS.get(item.asItem());
    }

    public static boolean hasInitializedRenderers() {
        return hasInitializedRenderers;
    }
}
