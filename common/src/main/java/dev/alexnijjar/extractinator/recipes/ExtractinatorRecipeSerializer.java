package dev.alexnijjar.extractinator.recipes;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import dev.alexnijjar.extractinator.registry.ModRecipeTypes;
import net.minecraft.world.item.crafting.RecipeType;

public class ExtractinatorRecipeSerializer extends CodecRecipeSerializer<ExtractinatorRecipe> {

    public ExtractinatorRecipeSerializer() {
        super(null, ExtractinatorRecipe::codec, ExtractinatorRecipe::networkCodec);
    }

    @Override
    public RecipeType<ExtractinatorRecipe> type() {
        return ModRecipeTypes.EXTRACTINATOR_RECIPE.get();
    }
}
