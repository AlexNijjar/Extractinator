package com.github.alexnijjar.the_extractinator.client.renderer;

import com.github.alexnijjar.the_extractinator.client.TheExtractinatorClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ExtractinatorItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        BakedModelManager manager = minecraftClient.getBakedModelManager();
        BakedModel grinderModel = BakedModelManagerHelper.getModel(manager, TheExtractinatorClient.GRINDER_PATH);
        BakedModel extractinatorBlockModel = BakedModelManagerHelper.getModel(manager, TheExtractinatorClient.EXTRACTINATOR_BLOCK_PATH);

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return;

        // 156 quads
        ExtractinatorRenderer.render(grinderModel, world, true, matrices, vertexConsumers, light, overlay, 0);
        // 168 quads
        ExtractinatorRenderer.render(extractinatorBlockModel, world, false, matrices, vertexConsumers, light, overlay, 0);

        // lower quads = better performance.
    }
}
