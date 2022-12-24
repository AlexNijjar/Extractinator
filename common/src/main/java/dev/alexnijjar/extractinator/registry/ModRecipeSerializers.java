package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ModRecipeSerializers {

    public static final Supplier<RecipeSerializer<ExtractinatorRecipe>> EXTRACTINATOR_SERIALIZER = register("extractinating", () -> new CodecRecipeSerializer<>(ModRecipeTypes.EXTRACTINATOR_RECIPE.get(), ExtractinatorRecipe::codec, ExtractinatorRecipe::networkCodec));

    private static <T extends RecipeSerializer<E>, E extends Recipe<?>> Supplier<T> register(String id, Supplier<T> object) {
        return RegistryHelpers.register(Registry.RECIPE_SERIALIZER, id, object);
    }

    public static void init() {
    }
}
