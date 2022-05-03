package com.github.alexnijjar.the_extractinator.data;

import com.github.alexnijjar.the_extractinator.compat.rei.util.Rarity;
import com.github.alexnijjar.the_extractinator.util.ModUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

// Gets the name of each item, its rarity and its uniform range from a custom loot table in this mod.
public final class LootTableParser {

    public static List<LootSlot> parse(JsonObject json) {

        List<LootSlot> loot = new ArrayList<>();

        loot.addAll(getDrops(json.get("common_drops").getAsJsonArray(), Rarity.COMMON));
        loot.addAll(getDrops(json.get("uncommon_drops").getAsJsonArray(), Rarity.UNCOMMON));
        loot.addAll(getDrops(json.get("rare_drops").getAsJsonArray(), Rarity.RARE));
        loot.addAll(getDrops(json.get("very_rare_drops").getAsJsonArray(), Rarity.VERY_RARE));
        loot.addAll(getDrops(json.get("extremely_rare_drops").getAsJsonArray(), Rarity.EXTREMELY_RARE));

        if (json.has("mod_compat")) {
            JsonObject modCompat = json.get("mod_compat").getAsJsonObject();

            if (modCompat.has("scale_with_mods")) {
                JsonArray scaleWithMods = modCompat.getAsJsonArray("scale_with_mods");
                for (JsonElement scale : scaleWithMods) {
                    JsonArray conditionalMods = scale.getAsJsonObject().get("conditional_mods").getAsJsonArray();
                    for (JsonElement mod : conditionalMods) {
                        if (ModUtils.modLoaded(mod.getAsString())) {
                            float factor = scale.getAsJsonObject().get("factor").getAsFloat();
                            for (LootSlot lootSlot : loot) {
                                int min = lootSlot.range.getMinimum();
                                int max = lootSlot.range.getMaximum();
                                lootSlot.range = Range.between(Math.round(min * factor), Math.round(max * factor));
                            }
                            break;
                        }
                    }
                }
            }

            if (modCompat.has("disable_with_mods")) {
                JsonArray scaleWithMods = modCompat.getAsJsonArray("disable_with_mods");
                for (JsonElement scale : scaleWithMods) {
                    JsonArray conditionalMods = scale.getAsJsonObject().get("conditional_mods").getAsJsonArray();
                    for (JsonElement mod : conditionalMods) {
                        if (ModUtils.modLoaded(mod.getAsString())) {
                            JsonArray items = scale.getAsJsonObject().get("items").getAsJsonArray();
                            for (JsonElement val : items) {
                                String item = val.getAsString();
                                loot.removeIf(lootSlot -> lootSlot.id.toString().equals(item));
                            }
                            break;
                        }
                    }
                }
            }
        }

        return loot;
    }

    private static List<LootSlot> getDrops(JsonArray array, Rarity rarity) {

        List<LootSlot> loot = new ArrayList<>();

        for (JsonElement drops : array) {
            JsonObject jsonObject = drops.getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            int min = 1;
            int max = 1;

            if (jsonObject.has("min")) {
                min = jsonObject.get("min").getAsInt();
            }
            if (jsonObject.has("max")) {
                max = jsonObject.get("max").getAsInt();
            }

            Range<Integer> range = Range.between(min, max);

            LootSlot lootSlot = new LootSlot();
            lootSlot.id = new Identifier(name);
            lootSlot.range = range;
            lootSlot.rarity = rarity;
            loot.add(lootSlot);
        }

        return loot;
    }
}
