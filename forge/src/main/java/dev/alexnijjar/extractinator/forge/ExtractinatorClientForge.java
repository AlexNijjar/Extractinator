package dev.alexnijjar.extractinator.forge;

import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import dev.alexnijjar.extractinator.config.forge.ForgeMenuConfig;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ExtractinatorClientForge {
    private static final Map<Item, BlockEntityWithoutLevelRenderer> ITEM_RENDERERS = new HashMap<>();
    private static boolean hasInitializedRenderers = false;

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ExtractinatorClientForge::modelLoading);
        bus.addListener(ExtractinatorClientForge::onRegisterRenderers);
        ForgeMenuConfig.register();
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ExtractinatorClient.registerBlockRenderers(new ExtractinatorClient.BlockRendererRegistry() {
            @Override
            public <T extends BlockEntity> void register(Supplier<? extends BlockEntityType<? extends T>> type, BlockEntityRendererProvider<T> factory) {
                event.registerBlockEntityRenderer(type.get(), factory);
            }
        });
    }

    public static void modelLoading(ModelEvent.RegisterAdditional event) {
        ExtractinatorClient.onRegisterModels(event::register);
    }

    public static void postInit() {
        ExtractinatorClient.onRegisterItemRenderers(ExtractinatorClientForge::registerItemRenderer);
        ExtractinatorClient.onRegisterBlockRenderTypes(ExtractinatorClientForge::onRegisterBlockRenderTypes);
        hasInitializedRenderers = true;
    }

    public static BlockEntityWithoutLevelRenderer getItemRenderer(ItemLike item) {
        return ITEM_RENDERERS.get(item.asItem());
    }

    private static void registerItemRenderer(ItemLike item, BlockEntityWithoutLevelRenderer renderer) {
        ITEM_RENDERERS.put(item.asItem(), renderer);
    }

    private static void onRegisterBlockRenderTypes(RenderType type, List<Block> blocks) {
        blocks.forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, type));
    }

    public static boolean hasInitializedRenderers() {
        return hasInitializedRenderers;
    }
}
