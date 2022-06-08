// package com.github.alexnijjar.the_extractinator.client.rei;

// import java.util.ArrayList;
// import java.util.List;

// import com.github.alexnijjar.the_extractinator.registry.ModBlocks;

// import me.shedaniel.math.Point;
// import me.shedaniel.math.Rectangle;
// import me.shedaniel.rei.api.client.gui.Renderer;
// import me.shedaniel.rei.api.client.gui.widgets.Widget;
// import me.shedaniel.rei.api.client.gui.widgets.Widgets;
// import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
// import me.shedaniel.rei.api.common.category.CategoryIdentifier;
// import me.shedaniel.rei.api.common.entry.EntryIngredient;
// import me.shedaniel.rei.api.common.util.EntryIngredients;
// import me.shedaniel.rei.api.common.util.EntryStacks;
// import net.fabricmc.api.EnvType;
// import net.fabricmc.api.Environment;
// import net.minecraft.text.Text;

// @Environment(EnvType.CLIENT)
// public class ExtractinatorCategory implements DisplayCategory<ExtractinatorDisplay> {

//     private static final int SLOT_SIZE = 18;
//     private static final int ROWS = 6;
//     private static final int COLUMNS = 9;

//     @Override
//     public Renderer getIcon() {
//         return EntryStacks.of(ModBlocks.EXTRACTINATOR_BLOCK.asItem());
//     }

//     @Override
//     public Text getTitle() {
//         return Text.translatable("the_extractinator.rei.extractinator.title");
//     }

//     @Override
//     public CategoryIdentifier<? extends ExtractinatorDisplay> getCategoryIdentifier() {
//         return TheExtractinatorClientPlugin.CATEGORY;
//     }

//     @Override
//     public int getDisplayWidth(ExtractinatorDisplay display) {
//         return DisplayCategory.super.getDisplayWidth(display) + 25;
//     }

//     @Override
//     public int getDisplayHeight() {
//         return DisplayCategory.super.getDisplayHeight() + 90;
//     }

//     @Override
//     public int getMaximumDisplaysPerPage() {
//         return 1;
//     }

//     @Override
//     public List<Widget> setupDisplay(ExtractinatorDisplay display, Rectangle bounds) {
//         Point startPoint = new Point((bounds.getCenterX() - SLOT_SIZE * 0.5) + 1.5, bounds.getCenterY() - SLOT_SIZE * 4);
//         List<Widget> widgets = new ArrayList<>();

//         widgets.add(Widgets.createRecipeBase(bounds));

//         // Input block item.
//         widgets.add(Widgets.createSlot(new Point(startPoint.x - SLOT_SIZE * 1.5, startPoint.y)).entries(display.getInputEntries().get(0)).markInput());

//         // Arrow.
//         widgets.add(Widgets.createArrow(new Point(startPoint.x - 4, startPoint.y)));

//         // Extractinator item.
//         widgets.add(Widgets.createSlot(new Point(startPoint.x + SLOT_SIZE * 1.5, startPoint.y)).entries(EntryIngredients.of(ModBlocks.EXTRACTINATOR_BLOCK)).markInput());

//         // Yield text.
//         float yield = display.block.yield;
//         Text yieldText = Text.translatable("the_extractinator.rei.extractinator.yield_chance", yield);
//         widgets.add(Widgets.createLabel(new Point(startPoint.x - 8, startPoint.y + SLOT_SIZE + 5), yieldText).rightAligned().noShadow().color(0xFF404040, 0xFFBBBBBB));

//         List<EntryIngredient> outputEntries = display.getOutputEntries();

//         // List of items.
//         for (int x = 0; x < COLUMNS; x++) {
//             for (int y = 0; y < ROWS; y++) {

//                 int index = COLUMNS * y + x;

//                 if (outputEntries.size() > index) {
//                     widgets.add(Widgets.createSlot(new Point(startPoint.x - SLOT_SIZE * 3.5f - SLOT_SIZE * 0.5 + SLOT_SIZE * x, startPoint.y + SLOT_SIZE * 2 + SLOT_SIZE * y)).markOutput().entries(outputEntries.get(index)));
//                 } else {
//                     // Remaining slots
//                     widgets.add(Widgets.createSlot(new Point(startPoint.x - SLOT_SIZE * 3.5f - SLOT_SIZE * 0.5 + SLOT_SIZE * x, startPoint.y + SLOT_SIZE * 2 + SLOT_SIZE * y)));
//                 }
//             }
//         }
//         return widgets;
//     }
// }
