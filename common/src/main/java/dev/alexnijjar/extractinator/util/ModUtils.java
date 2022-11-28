package dev.alexnijjar.extractinator.util;

import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModUtils {
    public static List<ItemStack> extractItem(Level level, ItemStack stack) {
        ExtractinatorRecipe recipe = ExtractinatorRecipe.findFirst(level, f -> f.input().test(stack));
        if (recipe != null) {
            Random random = level.getRandom();
            if (recipe.dropChance() >= random.nextDouble()) {
                List<ItemStack> drops = new ArrayList<>();
                int dropCount = random.nextInt(recipe.maxDropCount() - recipe.minDropCount() + 1) + recipe.minDropCount();
                double randomPercent = random.nextDouble();
                recipe.commonDrops().getRandomElement(random).ifPresent(d -> drops.add(new ItemStack(d.value(), dropCount)));
                if (randomPercent <= 0.10) {
                    recipe.rareDrops().getRandomElement(random).ifPresent(d -> drops.add(new ItemStack(d.value(), dropCount)));
                }
                if (randomPercent <= 0.01) {
                    recipe.veryRareDrops().getRandomElement(random).ifPresent(d -> drops.add(new ItemStack(d.value(), dropCount)));
                }

                return drops;
            }
        }
        return List.of();
    }

    public static boolean isValidInput(Level level, ItemStack stack) {
        return ExtractinatorRecipe.getRecipes(level).stream().anyMatch(r -> r.input().test(stack));
    }
}
