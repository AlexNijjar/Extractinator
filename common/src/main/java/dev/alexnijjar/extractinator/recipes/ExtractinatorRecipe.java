package dev.alexnijjar.extractinator.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.IngredientCodec;
import com.teamresourceful.resourcefullib.common.codecs.tags.HolderSetCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import dev.alexnijjar.extractinator.registry.ModRecipeSerializers;
import dev.alexnijjar.extractinator.registry.ModRecipeTypes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public record ExtractinatorRecipe(ResourceLocation id, Ingredient input, HolderSet<Item> commonDrops,
                                  HolderSet<Item> rareDrops, HolderSet<Item> veryRareDrops, double dropChance,
                                  int minDropCount, int maxDropCount) implements CodecRecipe<Container> {

    public static Codec<ExtractinatorRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                IngredientCodec.CODEC.fieldOf("input").forGetter(ExtractinatorRecipe::input),
                HolderSetCodec.of(Registry.ITEM).fieldOf("common_drops").forGetter(ExtractinatorRecipe::commonDrops),
                HolderSetCodec.of(Registry.ITEM).fieldOf("rare_drops").forGetter(ExtractinatorRecipe::rareDrops),
                HolderSetCodec.of(Registry.ITEM).fieldOf("very_rare_drops").forGetter(ExtractinatorRecipe::veryRareDrops),
                Codec.DOUBLE.fieldOf("drop_chance").forGetter(ExtractinatorRecipe::dropChance),
                Codec.INT.fieldOf("min_drop_count").forGetter(ExtractinatorRecipe::minDropCount),
                Codec.INT.fieldOf("max_drop_count").forGetter(ExtractinatorRecipe::maxDropCount)
        ).apply(instance, ExtractinatorRecipe::new));
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public @NotNull ResourceLocation id() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.EXTRACTINATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.EXTRACTINATOR_RECIPE.get();
    }

    public static ExtractinatorRecipe findFirst(Level level, Predicate<ExtractinatorRecipe> filter) {
        return getRecipes(level).stream().filter(filter).findFirst().orElse(null);
    }

    public static List<ExtractinatorRecipe> getRecipes(Level level) {
        return level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.EXTRACTINATOR_RECIPE.get());
    }
}