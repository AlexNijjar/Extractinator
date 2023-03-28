package dev.alexnijjar.extractinator.fabric;

import dev.alexnijjar.extractinator.client.ExtractinatorClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ExtractinatorClientFabric implements ClientModInitializer {
    @SuppressWarnings("unchecked")
    @Override
    public void onInitializeClient() {
        ExtractinatorClient.onRegisterItemRenderers((item, renderer) -> BuiltinItemRendererRegistry.INSTANCE.register(item.asItem(), renderer::renderByItem));
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> ExtractinatorClient.onRegisterModels(out));
        ExtractinatorClient.onRegisterBlockRenderers((type, factory) -> BlockEntityRenderers.register(type.get(), factory));
    }
}
