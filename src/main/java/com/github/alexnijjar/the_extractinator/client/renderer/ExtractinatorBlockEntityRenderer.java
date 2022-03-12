package com.github.alexnijjar.the_extractinator.client.renderer;

import com.github.alexnijjar.the_extractinator.blocks.ExtractinatorBlock;
import com.github.alexnijjar.the_extractinator.blocks.entity.ExtractinatorBlockEntity;
import com.github.alexnijjar.the_extractinator.client.TheExtractinatorClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class ExtractinatorBlockEntityRenderer implements BlockEntityRenderer<ExtractinatorBlockEntity> {

    public ExtractinatorBlockEntityRenderer() {
    }

    @Override
    public void render(ExtractinatorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        BakedModelManager manager = minecraftClient.getBakedModelManager();
        BakedModel grinderModel = BakedModelManagerHelper.getModel(manager, TheExtractinatorClient.GRINDER_PATH);

        World world = entity.getWorld();
        if (world == null) return;

        Direction direction = entity.getCachedState().get(ExtractinatorBlock.FACING);

        // 156 quads
        ExtractinatorRenderer.render(grinderModel, direction, world, matrices, vertexConsumers, light, overlay, tickDelta);
    }
}
