package dev.alexnijjar.extractinator.util;

import dev.alexnijjar.extractinator.config.ExtractinatorConfig;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModUtils {
	private static final Random ModUtilRandom = new Random();
	private static final Object2ObjectOpenHashMap<Map.Entry<Level, Item>, List<ExtractinatorRecipe>> recipeCacheMap = new Object2ObjectOpenHashMap<>();
    public static List<ItemStack> extractItem(Level level, ItemStack stack) {
        ExtractinatorRecipe recipe = getRandomMatching(level, stack);
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
	public static void clearCache() {
		recipeCacheMap.clear();
	}
    public static boolean isValidInput(Level level, ItemStack stack) {
        return recipeCacheMap.containsKey(Map.entry(level, stack.getItem())) || ExtractinatorRecipe.getRecipes(level).stream().anyMatch(r -> r.input().test(stack));
    }

	private static ExtractinatorRecipe getRandomMatching(Level level, ItemStack stack) {
		Item item = stack.getItem();
		List<ExtractinatorRecipe> recipes = recipeCacheMap.computeIfAbsent(Map.entry(level, item), (entry) ->
			 ExtractinatorRecipe.findMatching(level, f -> f.input().test(stack)
			));
		if (recipes.isEmpty()) return null;
		int randomIndex = ModUtilRandom.nextInt(recipes.size());
		return recipes.get(randomIndex);
	}
}
