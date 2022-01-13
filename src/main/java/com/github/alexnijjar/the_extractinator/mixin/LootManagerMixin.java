package com.github.alexnijjar.the_extractinator.mixin;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.google.gson.JsonElement;
import net.minecraft.loot.LootManager;
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

// Stops modded loot tables from loading if the mod is not installed, effectively preventing the "Couldn't parse loot table" error.
@Mixin(LootManager.class)
public class LootManagerMixin {

    @Inject(method = "apply", at = @At("HEAD"))
    public void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {


        List<Identifier> itemsToRemove = new ArrayList<>();

        // Gets the path for every loot table.
        map.forEach((id, json) -> {

            if (id.getNamespace().equals(TheExtractinator.MOD_ID)) {

                String path = id.getPath();

                if (path.contains("gameplay")) {

                    String modID = TEUtils.getModIDFromPath(path);

                    boolean modInstalled = false;

                    // Removes the path from the map if its corresponding mod is not loaded.
                    if (modID.equals("modern_industrialization") && TEUtils.modIsLoaded(SupportedMods.MI)) {
                        modInstalled = true;
                    } else if ((modID.equals("techreborn") || modID.equals("techreborn/addon")) && TEUtils.modIsLoaded(SupportedMods.TR)) {
                        modInstalled = true;
                    } else if ((modID.equals("minecraft/addon")) && (TEUtils.modIsLoaded(SupportedMods.TR) || TEUtils.modIsLoaded(SupportedMods.MI))) {
                        modInstalled = true;
                    } else if (modID.equals("minecraft")) {
                        modInstalled = true;
                    } else if (modID.equals("indrev") && TEUtils.modIsLoaded(SupportedMods.INDREV)) {
                        modInstalled = true;
                    } else if (modID.equals("ae2") && TEUtils.modIsLoaded(SupportedMods.AE2)) {
                        modInstalled = true;
                    }

                    if (!modInstalled) {
                        itemsToRemove.add(id);
                    }
                }
            }
        });

        itemsToRemove.forEach(map.keySet()::remove);

    }
}