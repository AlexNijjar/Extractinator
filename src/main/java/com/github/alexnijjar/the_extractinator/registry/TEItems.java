package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
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

import java.util.List;


public final class TEItems {

    public static Item extractinatorItem;

    public static void register() {

        // Uses a different extractinator model for the extractinator block item.
        extractinatorItem = Registry.register(Registry.ITEM, new TEIdentifier("extractinator"), new BlockItem(TEBlocks.EXTRACTINATOR_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
            @Override
            public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
                if (!TEUtils.modLoaded("roughlyenoughitems") && TEUtils.modLoaded("subterrestrial"))
                    tooltip.add((new TranslatableText("item.the_extractinator.extractinator.tooltip")));
            }
        });

        Registry.register(Registry.ITEM, new TEIdentifier("silt"), new BlockItem(TEBlocks.SILT, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
                tooltip.add((new TranslatableText("item.the_extractinator.silt.tooltip")));
            }
        });

        Registry.register(Registry.ITEM, new TEIdentifier("slush"), new BlockItem(TEBlocks.SLUSH, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
                tooltip.add((new TranslatableText("item.the_extractinator.silt.tooltip")));
            }
        });
    }
}
