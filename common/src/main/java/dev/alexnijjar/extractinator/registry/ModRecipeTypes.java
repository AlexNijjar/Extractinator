package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipeTypes {

    public static final Supplier<RecipeType<ExtractinatorRecipe>> EXTRACTINATOR_RECIPE = register("extractinating", () -> CodecRecipeType.of("extractinating"));

    private static <T extends RecipeType<E>, E extends Recipe<?>> Supplier<T> register(String id, Supplier<T> object) {
        return RegistryHelpers.register(Registry.RECIPE_TYPE, id, object);
    }

    public static void init() {
    }
}
