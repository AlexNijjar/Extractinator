package dev.alexnijjar.extractinator.compat.emi;

import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import dev.alexnijjar.extractinator.registry.ModItems;
import dev.alexnijjar.extractinator.registry.ModRecipeTypes;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;

public class ExtractinatorEmiPlugin implements EmiPlugin {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(new ResourceLocation(Extractinator.MOD_ID, "extractinator"), EmiStack.of(ModItems.EXTRACTINATOR.get()));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
        registry.addWorkstation(CATEGORY, EmiStack.of(ModItems.EXTRACTINATOR.get()));

        for (ExtractinatorRecipe recipe : registry.getRecipeManager().getAllRecipesFor(ModRecipeTypes.EXTRACTINATOR_RECIPE.get())) {
            registry.addRecipe(new EmiExtractinatorRecipe(recipe));
        }
    }
}
