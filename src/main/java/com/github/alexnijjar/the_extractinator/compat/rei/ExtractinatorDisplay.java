package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.github.alexnijjar.the_extractinator.util.output.ExtractinatorBlockOutput;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ExtractinatorDisplay implements Display {

    public final Identifier id;
    public final int index;
    List<LootTable> tables;

    public ExtractinatorDisplay(Identifier id, int index, List<LootTable> tables) {

        this.id = id;
        this.index = index;
        this.tables = tables;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {

        List<EntryIngredient> items = new ArrayList<>();
        items.add(EntryIngredients.of(TEUtils.getItem(id)));

        return items;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {

        List<EntryIngredient> items = new ArrayList<>();

        ExtractinatorBlockOutput.getOutput(id, tables).forEach((slot) -> {

            EntryStack<ItemStack> entry = EntryStacks.of(new ItemStack(TEUtils.getItem(slot.item), (int) Math.ceil(slot.range.getMaximum() * TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier)))
                    .setting(EntryStack.Settings.TOOLTIP_APPEND_EXTRA, REIUtils.getSettings(slot.rarity, slot.range));
            EntryIngredient ingredient = EntryIngredient.of(entry);

            items.add(ingredient);
        });

        return items;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TEClientPlugin.CATEGORY;
    }
}
