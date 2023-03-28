package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeTab;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.blocks.ExtractinatorBlockItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, Extractinator.MOD_ID);
    public static final Supplier<CreativeModeTab> TAB = new ResourcefulCreativeTab(new ResourceLocation(Extractinator.MOD_ID, "main"))
            .setItemIcon(ModBlocks.EXTRACTINATOR)
            .addRegistry(ITEMS)
            .build();

    public static final RegistryEntry<Item> EXTRACTINATOR = ITEMS.register("extractinator", () -> new ExtractinatorBlockItem(ModBlocks.EXTRACTINATOR.get(), new Item.Properties()));
    public static final RegistryEntry<Item> SILT = ITEMS.register("silt", () -> new BlockItem(ModBlocks.SILT.get(), new Item.Properties()));
    public static final RegistryEntry<Item> SLUSH = ITEMS.register("slush", () -> new BlockItem(ModBlocks.SLUSH.get(), new Item.Properties()));
}
