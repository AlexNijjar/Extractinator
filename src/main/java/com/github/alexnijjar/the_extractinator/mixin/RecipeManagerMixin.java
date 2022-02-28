package com.github.alexnijjar.the_extractinator.mixin;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.recipe.ExtractinatorRecipe;
import com.github.alexnijjar.the_extractinator.registry.TERecipes;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
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

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        if (TERecipes.EXTRACTINATOR_RECIPE != null) {
            ExtractinatorRecipe recipe = TheExtractinator.CONFIG.extractinatorConfig.extractinatorRecipe_v2;
            if ((recipe.equals(ExtractinatorRecipe.MODERN_INDUSTRIALIZATION) && TEUtils.modLoaded(SupportedMods.MODERN_INDUSTRIALIZATION)) || (recipe.equals(ExtractinatorRecipe.TECH_REBORN) && TEUtils.modLoaded(SupportedMods.TECHREBORN)) || recipe.equals(ExtractinatorRecipe.MINECRAFT))
                map.put(new TEIdentifier("extractinator"), TERecipes.EXTRACTINATOR_RECIPE);
        }
    }

}