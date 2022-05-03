package com.github.alexnijjar.the_extractinator.registry;

import java.util.List;

import com.github.alexnijjar.the_extractinator.util.ModIdentifier;
import com.github.alexnijjar.the_extractinator.util.ModUtils;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public final class ModItems {

    public static Item extractinatorItem;

    public static void register() {

        // Uses a different extractinator model for the extractinator block item.
        extractinatorItem = Registry.register(Registry.ITEM, new ModIdentifier("extractinator"), new BlockItem(ModBlocks.EXTRACTINATOR_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
            @Override
            public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
                if (!ModUtils.modLoaded("roughlyenoughitems") && ModUtils.modLoaded("subterrestrial")) {
                    tooltip.add((new TranslatableText("item.the_extractinator.extractinator.tooltip")));
                }
            }
        });

        Registry.register(Registry.ITEM, new ModIdentifier("silt"), new BlockItem(ModBlocks.SILT, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
                tooltip.add((new TranslatableText("item.the_extractinator.silt.tooltip")));
            }
        });

        Registry.register(Registry.ITEM, new ModIdentifier("slush"), new BlockItem(ModBlocks.SLUSH, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
                tooltip.add((new TranslatableText("item.the_extractinator.silt.tooltip")));
            }
        });
    }
}
