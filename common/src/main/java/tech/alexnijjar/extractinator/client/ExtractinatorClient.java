package tech.alexnijjar.extractinator.client;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.alexnijjar.extractinator.common.registry.ModBlockEntityTypes;
import tech.alexnijjar.extractinator.common.registry.ModBlocks;
import tech.alexnijjar.extractinator.common.registry.ModItems;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExtractinatorClient {
    public static void initializeClient() {
        registerRenderTypes();
    }

    public static void registerRenderTypes() {
        ClientPlatformUtils.registerBlockRenderType(RenderType.cutout(), ModBlocks.EXTRACTINATOR);
    }

    public static void onRegisterItemRenderers(BiConsumer<ItemLike, BlockEntityWithoutLevelRenderer> consumer) {
        consumer.accept(ModItems.EXTRACTINATOR.get(), new ExtractinatorRenderer.ExtractinatorItemRenderer());
    }

    @SuppressWarnings("rawtypes")
    public static void onRegisterBlockRenderers(BiConsumer<RegistryEntry<? extends BlockEntityType<?>>, BlockEntityRendererProvider> consumer) {
        consumer.accept(ModBlockEntityTypes.EXTRACTINATOR, ExtractinatorRenderer::new);
    }

    public static void onRegisterModels(Consumer<ResourceLocation> consumer) {
        consumer.accept(ExtractinatorRenderer.BASE);
        consumer.accept(ExtractinatorRenderer.PUMP);
        consumer.accept(ExtractinatorRenderer.TORQUE_WHEEL);
        consumer.accept(ExtractinatorRenderer.COGWHEEL);
        consumer.accept(ExtractinatorRenderer.CHIMNEY);
    }
}
