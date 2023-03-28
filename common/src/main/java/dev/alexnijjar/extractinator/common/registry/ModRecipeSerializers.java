package dev.alexnijjar.extractinator.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.common.recipe.ExtractinatorRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializers {
    public static final ResourcefulRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_SERIALIZER, Extractinator.MOD_ID);

    public static final RegistryEntry<RecipeSerializer<ExtractinatorRecipe>> EXTRACTINATOR_SERIALIZER = RECIPE_SERIALIZERS.register("extractinating", () -> new CodecRecipeSerializer<>(ModRecipeTypes.EXTRACTINATOR_RECIPE.get(), ExtractinatorRecipe::codec, ExtractinatorRecipe::networkingCodec));
}
