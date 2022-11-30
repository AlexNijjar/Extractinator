package dev.alexnijjar.extractinator.mixin.forge;

import dev.alexnijjar.extractinator.blocks.ExtractinatorBlockItem;
import dev.alexnijjar.extractinator.forge.ExtractinatorClientForge;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(ExtractinatorBlockItem.class)
public abstract class ExtractinatorBlockItemMixin extends Item {

    public ExtractinatorBlockItemMixin(Properties settings) {
        super(settings);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        Item item = this;

        consumer.accept(new IClientItemExtensions() {
            private BlockEntityWithoutLevelRenderer renderer = null;
            private boolean hasCheckedSinceInit = false;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (ExtractinatorClientForge.hasInitializedRenderers() && !hasCheckedSinceInit) {
                    renderer = ExtractinatorClientForge.getItemRenderer(item);
                    hasCheckedSinceInit = true;
                }
                return renderer == null ? IClientItemExtensions.super.getCustomRenderer() : renderer;
            }
        });
    }
}
