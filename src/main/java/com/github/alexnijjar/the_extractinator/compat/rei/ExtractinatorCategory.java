package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.TEBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.List;

public class ExtractinatorCategory implements DisplayCategory<ExtractinatorDisplay> {

    final int slotSize = 18;
    final int rows = 6;
    final int columns = 9;

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(TEBlocks.EXTRACTINATOR_BLOCK.asItem());
    }

    @Override
    public Text getTitle() {
        return new TranslatableText("the_extractinator.rei.extractinator.title");
    }

    @Override
    public CategoryIdentifier<? extends ExtractinatorDisplay> getCategoryIdentifier() {
        return TEClientPlugin.CATEGORY;
    }

    @Override
    public int getDisplayWidth(ExtractinatorDisplay display) {
        return DisplayCategory.super.getDisplayWidth(display) + 25;
    }

    @Override
    public int getDisplayHeight() {
        return DisplayCategory.super.getDisplayHeight() + 90;
    }

    @Override
    public int getMaximumDisplaysPerPage() {
        return 1;
    }

    @Override
    public List<Widget> setupDisplay(ExtractinatorDisplay display, Rectangle bounds) {
        Point startPoint = new Point((bounds.getCenterX() - slotSize * 0.5) + 1.5, bounds.getCenterY() - slotSize * 4);
        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        // Input block item.
        widgets.add(Widgets.createSlot(new Point(startPoint.x - slotSize * 1.5, startPoint.y))
                .entries(display.getInputEntries().get(0)).markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(startPoint.x - 4, startPoint.y)));

        // Extractinator item.
        widgets.add(Widgets.createSlot(new Point(startPoint.x + slotSize * 1.5, startPoint.y))
                .entries(EntryIngredients.of(TEBlocks.EXTRACTINATOR_BLOCK)).markInput());

        // Yield text.
        int yield = TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks_v2.get(display.index).yield;
        TranslatableText yieldText = new TranslatableText("the_extractinator.rei.extractinator.yield_chance", yield);
        widgets.add(Widgets.createLabel(new Point(startPoint.x - 8, startPoint.y + slotSize + 5), yieldText)
                .rightAligned()
                .noShadow()
                .color(0xFF404040, 0xFFBBBBBB));

        List<EntryIngredient> outputEntries = display.getOutputEntries();

        // List of items.
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {

                int index = columns * y + x;

                if (outputEntries.size() > index)
                    widgets.add(Widgets.createSlot(new Point(startPoint.x - slotSize * 3.5f - slotSize * 0.5 + slotSize * x, startPoint.y + slotSize * 2 + slotSize * y))
                            .markOutput().entries(outputEntries.get(index)));
                else
                    // Remaining slots
                    widgets.add(Widgets.createSlot(new Point(startPoint.x - slotSize * 3.5f - slotSize * 0.5 + slotSize * x, startPoint.y + slotSize * 2 + slotSize * y)));
            }
        }
        return widgets;
    }
}
