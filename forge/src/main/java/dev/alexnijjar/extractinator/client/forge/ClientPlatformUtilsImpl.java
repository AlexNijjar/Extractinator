package dev.alexnijjar.extractinator.client.forge;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ClientPlatformUtilsImpl {
    @SuppressWarnings("removal")
    public static void registerBlockRenderType(RenderType type, Supplier<Block> block) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), type);
    }

    public static BakedModel getModel(ModelManager dispatcher, ResourceLocation id) {
        return dispatcher.getModel(id);
    }
}
