package tech.alexnijjar.extractinator.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class ClientPlatformUtils {
    @ExpectPlatform
    public static void registerBlockRenderType(RenderType type, Supplier<Block> block) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static BakedModel getModel(ModelManager dispatcher, ResourceLocation id) {
        throw new NotImplementedException();
    }
}
