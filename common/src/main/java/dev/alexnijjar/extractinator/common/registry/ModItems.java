package dev.alexnijjar.extractinator.common.registry;

import dev.alexnijjar.extractinator.common.block.ExtractinatorBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {
    public static final Supplier<Item> EXTRACTINATOR = register("extractinator", () -> new ExtractinatorBlockItem(ModBlocks.EXTRACTINATOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> SILT = register("silt", () -> new BlockItem(ModBlocks.SILT.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Item> SLUSH = register("slush", () -> new BlockItem(ModBlocks.SLUSH.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    private static <T extends Item> Supplier<T> register(String id, Supplier<T> item) {
        return ModRegistryHelpers.register(Registry.ITEM, id, item);
    }

    public static void init() {
    }
}
