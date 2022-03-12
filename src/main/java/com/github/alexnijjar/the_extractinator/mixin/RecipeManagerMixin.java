package com.github.alexnijjar.the_extractinator.mixin;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    // Blocks all non-supported recipes from the config.
    @Inject(method = "apply", at = @At("HEAD"))
    public void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {

        List<Identifier> itemsToRemove = new ArrayList<>();

        map.forEach((id, json) -> {
            if (id.toString().contains(TheExtractinator.MOD_ID + ":extractinator/")) {
                String path = id.getPath();
                switch (TheExtractinator.CONFIG.extractinatorConfig.extractinatorRecipe) {
                    case NONE -> {
                        itemsToRemove.add(id);
                    }
                    case MINECRAFT -> {
                        if (!path.contains("minecraft")) itemsToRemove.add(id);
                    }
                    case MODERN_INDUSTRIALIZATION -> {
                        if (!path.contains("modern_industrialization") || !TEUtils.modLoaded("modern_industrialization")) itemsToRemove.add(id);
                    }
                    case TECH_REBORN -> {
                        if (!path.contains("techreborn") || !TEUtils.modLoaded("techreborn")) itemsToRemove.add(id);
                    }
                }
            }
        });

        itemsToRemove.forEach(map.keySet()::remove);
    }
}