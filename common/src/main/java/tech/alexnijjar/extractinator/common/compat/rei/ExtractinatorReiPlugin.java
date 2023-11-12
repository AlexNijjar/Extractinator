package tech.alexnijjar.extractinator.common.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.resources.ResourceLocation;
import tech.alexnijjar.extractinator.Extractinator;
import tech.alexnijjar.extractinator.common.recipe.ExtractinatorRecipe;
import tech.alexnijjar.extractinator.common.registry.ModBlocks;
import tech.alexnijjar.extractinator.common.registry.ModRecipeTypes;

public class ExtractinatorReiPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<ExtractinatorDisplay> CATEGORY = CategoryIdentifier.of(new ResourceLocation(Extractinator.MOD_ID, "extractinator"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ExtractinatorCategory());
        registry.addWorkstations(CATEGORY, EntryStacks.of(ModBlocks.EXTRACTINATOR.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(ExtractinatorRecipe.class, ModRecipeTypes.EXTRACTINATOR_RECIPE.get(), ExtractinatorDisplay::new);
    }
}
