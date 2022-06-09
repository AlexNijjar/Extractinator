package com.github.alexnijjar.the_extractinator.client.rei;

import java.util.ArrayList;
import java.util.List;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.client.rei.util.REILootDisplay;
import com.github.alexnijjar.the_extractinator.client.rei.util.REIUtils;
import com.github.alexnijjar.the_extractinator.data.LootSlot;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
import com.github.alexnijjar.the_extractinator.util.ModUtils;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class ExtractinatorDisplay implements Display {

    public final SupportedBlock block;

    public ExtractinatorDisplay(SupportedBlock block) {

        this.block = block;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {

        List<EntryIngredient> items = new ArrayList<>();
        items.add(EntryIngredients.of(Registry.ITEM.get(block.id)));

        return items;
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<EntryIngredient> getOutputEntries() {

        List<EntryIngredient> items = new ArrayList<>();

        List<LootSlot> slots = REILootDisplay.getOutput(block);

        int[] rarities = new int[5];

        for (LootSlot slot : slots) {
            int index = slot.rarity.ordinal();
            rarities[index]++;
        }

        for (LootSlot slot : slots) {
            ItemStack stack = new ItemStack(Registry.ITEM.get(slot.id), (int) Math.ceil(slot.range.getMaximum() * TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier));
            EntryStack<ItemStack> entry = EntryStacks.of(stack).setting(EntryStack.Settings.TOOLTIP_APPEND_EXTRA, REIUtils.getSettings(slot, ModUtils.rarityToPercent(slot.rarity) / rarities[slot.rarity.ordinal()] * block.yield));
            EntryIngredient ingredient = EntryIngredient.of(entry);

            items.add(ingredient);
        }

        return items;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TheExtractinatorClientPlugin.CATEGORY;
    }
}
