package dev.alexnijjar.extractinator.fabric;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;

public class ExtractinatorClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ExtractinatorClient.onRegisterItemRenderers(ExtractinatorClientFabric::registerItemRenderer);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> ExtractinatorClient.onRegisterModels(out));
        ExtractinatorClient.onRegisterBlockRenderTypes(ExtractinatorClientFabric::registerBlockRenderTypes);
        ExtractinatorClient.registerBlockRenderers(new ExtractinatorClient.BlockRendererRegistry() {
            @Override
            public <T extends BlockEntity> void register(RegistryEntry<? extends BlockEntityType<? extends T>> type, BlockEntityRendererProvider<T> factory) {
                BlockEntityRendererRegistry.register(type.get(), factory);
            }
        });
    }

    private static void registerBlockRenderTypes(RenderType type, List<Block> blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(type, blocks.toArray(new Block[0]));
    }

    private static void registerItemRenderer(ItemLike item, BlockEntityWithoutLevelRenderer renderer) {
        BuiltinItemRendererRegistry.INSTANCE.register(item.asItem(), renderer::renderByItem);
    }
}
