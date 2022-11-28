package dev.alexnijjar.extractinator.registry;

import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipeSerializer;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ModRecipeSerializers {

    public static final Supplier<RecipeSerializer<ExtractinatorRecipe>> EXTRACTINATOR_SERIALIZER = register("extractinating", ExtractinatorRecipeSerializer::new);

    private static <T extends RecipeSerializer<E>, E extends Recipe<?>> Supplier<T> register(String id, Supplier<T> object) {
        return RegistryHelpers.register(Registry.RECIPE_SERIALIZER, id, object);
    }

    public static void init() {
    }
}
