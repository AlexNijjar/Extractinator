package dev.alexnijjar.extractinator.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.common.recipe.ExtractinatorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {
    public static final ResourcefulRegistry<RecipeType<?>> RECIPE_TYPES = ResourcefulRegistries.create(Registry.RECIPE_TYPE, Extractinator.MOD_ID);

    public static final RegistryEntry<RecipeType<ExtractinatorRecipe>> EXTRACTINATOR_RECIPE = RECIPE_TYPES.register("extractinating", () -> CodecRecipeType.of("extractinating"));
}
