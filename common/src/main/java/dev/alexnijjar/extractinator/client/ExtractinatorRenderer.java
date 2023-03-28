package dev.alexnijjar.extractinator.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.common.block.ExtractinatorBlock;
import dev.alexnijjar.extractinator.common.block.ExtractinatorBlockEntity;
import dev.alexnijjar.extractinator.common.registry.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ExtractinatorRenderer implements BlockEntityRenderer<ExtractinatorBlockEntity> {
    public static final ResourceLocation BASE = new ResourceLocation(Extractinator.MOD_ID, "block/extractinator/base");
    public static final ResourceLocation PUMP = new ResourceLocation(Extractinator.MOD_ID, "block/extractinator/pump");
    public static final ResourceLocation TORQUE_WHEEL = new ResourceLocation(Extractinator.MOD_ID, "block/extractinator/torque_wheel");
    public static final ResourceLocation COGWHEEL = new ResourceLocation(Extractinator.MOD_ID, "block/extractinator/cogwheel");
    public static final ResourceLocation CHIMNEY = new ResourceLocation(Extractinator.MOD_ID, "block/extractinator/chimney");

    public ExtractinatorRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(ExtractinatorBlockEntity extractinator, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        long time = extractinator.getLevel() == null ? 0 : extractinator.getLevel().getGameTime();
        Direction dir = extractinator.getBlockState().getValue(ExtractinatorBlock.FACING);
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.translate(0.5, 1.0, 0.5);
            poseStack.mulPose(Axis.YN.rotationDegrees(dir.toYRot()));
            poseStack.mulPose(Axis.YN.rotationDegrees(180));
            poseStack.translate(-0.5, -1.0, -0.5);
            try (var ignored1 = new CloseablePoseStack(poseStack)) {
                renderPump(time, poseStack, bufferSource, packedLight, packedOverlay);
                renderCogwheel(time, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
                renderChimney(time, poseStack, bufferSource, packedLight, packedOverlay);
            }
        }
    }

    protected static void renderPump(long time, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        long animationTime = time % 80;
        long frameTime = time % 20;
        float translation = (int) (animationTime / 20f) * 0.0625f;
        if ((int) (animationTime / 20f) < 3) {
            if (frameTime > 17) {
                translation += (frameTime - 17) * (0.0625f / 2f);
            }
        } else {
            translation = 0.1875f;
            if (frameTime > 14) {
                translation -= (frameTime - 14) * (0.1875f / 5f);
            }
        }
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.translate(0, -translation, 0);
            renderBlock(PUMP, poseStack, buffer, packedLight, packedOverlay);
            renderTorqueWheel(time, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    protected static void renderTorqueWheel(long time, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        long animationTime = time % 80;
        long frameTime = time % 20;
        float offset = ((int) (animationTime / 20.0f) - 1) * 90;
        float frameRot;
        if (frameTime < 2) {
            frameRot = 0;
        } else if (frameTime < 6) {
            frameRot = (frameTime - 2) * 11.25f;
        } else if (frameTime < 10) {
            frameRot = 45 + (frameTime - 5) * 2.5f;
        } else if (frameTime < 16) {
            frameRot = 55 + (frameTime - 9) * (45 / 6f);
        } else {
            frameRot = 100 - ((frameTime - 15f) * (10f / 4f));
        }
        float angle = Math.min(offset + frameRot, offset + 100);
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.translate(10.5 / 16, 0, 5.5 / 16);
            poseStack.mulPose(Axis.YP.rotationDegrees(angle));
            renderBlock(TORQUE_WHEEL, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    protected static void renderCogwheel(long time, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.translate(5.5 / 16, 0, 10.5 / 16);
            poseStack.mulPose(Axis.YP.rotationDegrees((float) ((time + partialTick) * -5.0)));
            renderBlock(COGWHEEL, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    protected static void renderChimney(long time, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        long animationTime = time % 80;
        float scale = 1;
        if (animationTime > 74) {
            scale += (0.4f - (animationTime - 70) * 0.04f);
        } else if (animationTime > 70) {
            scale += (animationTime - 70) * (0.4f / 5);
        }
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.translate(2f / 16, 10f / 16, 4f / 16);
            poseStack.scale(scale, scale, scale);
            renderBlock(CHIMNEY, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    public static class ExtractinatorItemRenderer extends BlockEntityWithoutLevelRenderer {
        public ExtractinatorItemRenderer() {
            super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }

        @Override
        public void renderByItem(ItemStack stack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
            renderBlock(BASE, poseStack, buffer, packedLight, packedOverlay);
            Minecraft minecraft = Minecraft.getInstance();
            long time = minecraft.level == null ? 0 : minecraft.level.getGameTime();
            renderPump(time, poseStack, buffer, packedLight, packedOverlay);
            renderCogwheel(time, minecraft.getDeltaFrameTime(), poseStack, buffer, packedLight, packedOverlay);
            renderChimney(time, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    protected static void renderBlock(ResourceLocation model, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        BakedModel blockModel = ClientPlatformUtils.getModel(Minecraft.getInstance().getModelManager(), model);
        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                buffer.getBuffer(Sheets.cutoutBlockSheet()),
                ModBlocks.EXTRACTINATOR.get().defaultBlockState(),
                blockModel,
                1f,
                1f,
                1f,
                packedLight, packedOverlay);
    }
}