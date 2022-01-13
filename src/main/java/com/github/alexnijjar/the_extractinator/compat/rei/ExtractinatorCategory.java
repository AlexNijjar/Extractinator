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
    final int rowSize = 8;


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
        return (int) (slotSize * 9.5);
    }

    @Override
    public int getDisplayHeight() {
        return (int) (slotSize * 13.5);
    }

    @Override
    public List<Widget> setupDisplay(ExtractinatorDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - slotSize / 2, bounds.getCenterY() - slotSize * 4);
        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        // Input block item.
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y - slotSize))
                .entries(display.getInputEntries().get(0))
                .markInput());
        // Extractinator item.
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y))
                .entries(EntryIngredients.of(TEBlocks.EXTRACTINATOR_BLOCK))
                .markInput());

        // Yield text.
        int yield = TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks.get(display.index).yield;
        TranslatableText yieldText = new TranslatableText("the_extractinator.rei.extractinator.yield_chance", yield);
        widgets.add(Widgets.createLabel(new Point(startPoint.x - 5, startPoint.y + slotSize + 5), yieldText)
                .rightAligned()
                .noShadow()
                .color(0xFF404040, 0xFFBBBBBB));

        List<EntryIngredient> outputEntries = display.getOutputEntries();
        int size = outputEntries.size();
        int rows = (int) Math.ceil((double) size / rowSize);

        // List of items.
        for (int x = 0; x < rowSize; x++) {
            for (int y = 0; y < rows; y++) {
                if (rowSize * y + x >= size) {
                    break;
                }
                int index = rowSize * y + x;
                widgets.add(
                        Widgets.createSlot(new Point(startPoint.x - slotSize * 3 - slotSize / 2 + slotSize * x, startPoint.y + slotSize * 2 + slotSize * y))
                                .markOutput()
                                .entries(outputEntries.get(index))
                );
            }
        }
        return widgets;
    }
}
