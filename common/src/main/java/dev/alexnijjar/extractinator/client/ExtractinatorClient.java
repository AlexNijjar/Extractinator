package dev.alexnijjar.extractinator.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.alexnijjar.extractinator.registry.ModBlockEntities;
import dev.alexnijjar.extractinator.registry.ModBlocks;
import dev.alexnijjar.extractinator.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExtractinatorClient {
    public static void initializeClient() {
    }

    public static void onRegisterItemRenderers(BiConsumer<ItemLike, BlockEntityWithoutLevelRenderer> register) {
        register.accept(ModItems.EXTRACTINATOR.get(), new ExtractinatorRenderer.ExtractinatorItemRenderer());
    }

    public static void registerBlockRenderers(BlockRendererRegistry registry) {
        registry.register(ModBlockEntities.EXTRACTINATOR, ExtractinatorRenderer::new);
    }

    public static void onRegisterModels(Consumer<ResourceLocation> register) {
        register.accept(ExtractinatorRenderer.BASE);
        register.accept(ExtractinatorRenderer.PUMP);
        register.accept(ExtractinatorRenderer.TORQUE_WHEEL);
        register.accept(ExtractinatorRenderer.COGWHEEL);
        register.accept(ExtractinatorRenderer.CHIMNEY);
    }

    public static void onRegisterBlockRenderTypes(BiConsumer<RenderType, List<Block>> register) {
        register.accept(RenderType.cutout(), List.of(ModBlocks.EXTRACTINATOR.get()));
    }

    public static abstract class BlockRendererRegistry {
        public abstract <T extends BlockEntity> void register(Supplier<? extends BlockEntityType<? extends T>> type, BlockEntityRendererProvider<T> factory);
    }

    public static void renderBlock(ResourceLocation model, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Minecraft minecraft = Minecraft.getInstance();
        ModelManager manager = minecraft.getModelManager();
        BakedModel baked = ClientUtils.getModel(manager, model);

        VertexConsumer vertexConsumer1 = buffer.getBuffer(RenderType.entitySolid(InventoryMenu.BLOCK_ATLAS));
        List<BakedQuad> quads1 = baked.getQuads(null, null, minecraft.level.random);
        PoseStack.Pose entry1 = poseStack.last();

        for (BakedQuad quad : quads1) {
            vertexConsumer1.putBulkData(entry1, quad, 1, 1, 1, packedLight, packedOverlay);
        }
    }
}
