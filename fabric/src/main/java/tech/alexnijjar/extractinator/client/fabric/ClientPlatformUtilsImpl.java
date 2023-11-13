package tech.alexnijjar.extractinator.client.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ClientPlatformUtilsImpl {
    public static void registerBlockRenderType(RenderType type, Supplier<Block> block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block.get(), type);
    }

    public static BakedModel getModel(ModelManager dispatcher, ResourceLocation id) {
        return BakedModelManagerHelper.getModel(dispatcher, id);
    }

}
