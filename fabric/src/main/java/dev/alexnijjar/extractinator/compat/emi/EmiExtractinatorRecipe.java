package dev.alexnijjar.extractinator.compat.emi;

import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class EmiExtractinatorRecipe implements EmiRecipe {
    private final ResourceLocation id;
    ExtractinatorRecipe recipe;
    private final EmiIngredient input;
    private final List<EmiIngredient> outputs;

    public EmiExtractinatorRecipe(ExtractinatorRecipe recipe) {
        this.id = recipe.getId();
        this.recipe = recipe;
        this.input = EmiIngredient.of(recipe.input());
        this.outputs = recipe.getOutputs().stream().map(i -> EmiIngredient.of(i, i.getItems()[0].getCount())).toList();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ExtractinatorEmiPlugin.CATEGORY;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(this.input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.outputs.stream().map(EmiIngredient::getEmiStacks).flatMap(List::stream).toList();
    }

    @Override
    public int getDisplayWidth() {
        return 144;
    }

    @Override
    public int getDisplayHeight() {
        return 144 + 3;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(this.input, 63, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                int index = 8 * j + i;
                if (this.outputs.size() > index) {
                    widgets.addSlot(this.outputs.get(index), i * 18 + 1, j * 18 + 21);
                } else {
                    widgets.addSlot(i * 18 + 1, j * 18 + 21);
                }
            }
        }
    }
}
