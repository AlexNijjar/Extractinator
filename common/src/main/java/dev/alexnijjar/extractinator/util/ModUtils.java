package dev.alexnijjar.extractinator.util;

import dev.alexnijjar.extractinator.config.ExtractinatorConfig;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class
ModUtils {
    public static List<ItemStack> extractItem(Level level, ItemStack stack) {
        ExtractinatorRecipe recipe = ExtractinatorRecipe.findFirst(level, f -> f.input().test(stack));
        if (recipe != null) {
            RandomSource random = level.getRandom();

            List<ItemStack> drops = new ArrayList<>();
            for (ExtractinatorRecipe.Drop drop : recipe.outputs()) {
                int dropCount = (int) ((random.nextInt(drop.maxDropCount() - drop.minDropCount() + 1) + drop.minDropCount()) * ExtractinatorConfig.lootMultiplier);
                double randomPercent = random.nextDouble();
                if (randomPercent <= drop.dropChance()) {
                    drop.drops().getRandomElement(random).ifPresent(d -> drops.add(new ItemStack(d.value(), dropCount)));
                }
            }

            return drops;
        }
        return List.of();
    }

    public static boolean isValidInput(Level level, ItemStack stack) {
        return ExtractinatorRecipe.getRecipes(level).stream().anyMatch(r -> r.input().test(stack));
    }
}
