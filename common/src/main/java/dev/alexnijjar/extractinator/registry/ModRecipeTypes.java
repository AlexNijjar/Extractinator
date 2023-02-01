package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {
    public static final ResourcefulRegistry<RecipeType<?>> RECIPE_TYPES = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_TYPE, Extractinator.MOD_ID);

    public static final RegistryEntry<RecipeType<ExtractinatorRecipe>> EXTRACTINATOR_RECIPE = RECIPE_TYPES.register("extractinating", () -> CodecRecipeType.of("extractinating"));
}
