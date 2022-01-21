package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class TEItems {

    public static void register() {

        // Uses a different extractinator model for the extractinator block item.
        Registry.register(Registry.ITEM, new TEIdentifier("extractinator"), new BlockItem(TEBlocks.EXTRACTINATOR_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
            @Override
            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                if (!FabricLoader.getInstance().isModLoaded("roughlyenoughitems"))
                    tooltip.add((new TranslatableText("item.the_extractinator.extractinator.tooltip")));
            }
        });

        Registry.register(Registry.ITEM, new TEIdentifier("silt"), new BlockItem(TEBlocks.SILT, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                tooltip.add((new TranslatableText("item.the_extractinator.silt.tooltip")));
            }
        });
    }
}
