package tech.alexnijjar.extractinator.common.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import tech.alexnijjar.extractinator.common.recipe.ExtractinatorRecipe;

import java.util.List;

public record ExtractinatorDisplay(ExtractinatorRecipe recipe) implements Display {
    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(EntryIngredients.ofIngredient(recipe().input()));
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return EntryIngredients.ofIngredients(recipe().getOutputs());
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ExtractinatorReiPlugin.CATEGORY;
    }
}
