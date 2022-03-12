package com.github.alexnijjar.the_extractinator.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ExtractinatorRenderer {

    static void render(BakedModel model, World world, boolean oscillate, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, float tickDelta) {

        matrices.push();
        // Grinder oscillation.
        if (oscillate) {
            double sine = Math.sin((world.getTime() + tickDelta) / 1.5);
            matrices.translate(0, sine * 0.09, 0);
            matrices.translate(0, 0.1, 0);
        }

        // Render model.
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE));
        List<BakedQuad> quads = model.getQuads(null, null, world.random);
        MatrixStack.Entry entry = matrices.peek();

        for (BakedQuad quad : quads) {
            vertexConsumer.quad(entry, quad, 1, 1, 1, light, overlay);
        }

        matrices.pop();
    }

    // Turn extractinator upright (for block version) and then render it.
    static void render(BakedModel model, Direction direction, World world, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, float tickDelta) {

        // Make the grinder face the same direction as the extractinator.
        matrices.multiply(direction.getRotationQuaternion());

        // rotate the grinder upright.
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));

        // Adjust the grinder so that it fits in the designated space.
        switch (direction) {
            case EAST -> matrices.translate(0, 0, -1);
            case SOUTH -> matrices.translate(-1, 0, -1);
            case WEST -> matrices.translate(-1, 0, 0);

        }

        render(model, world, true, matrices, vertexConsumers, light, overlay, tickDelta);
    }
}
