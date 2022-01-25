package com.github.alexnijjar.the_extractinator.loot;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.LootSlot;
import com.github.alexnijjar.the_extractinator.compat.rei.Rarity;
import com.github.alexnijjar.the_extractinator.config.ExtractinatorConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

// Gets the name of each item, its rarity and its uniform range from a custom loot table in this mod.
public final class LootTableParser {

    public static List<LootSlot> parse(JsonElement json) {

        List<LootSlot> loot = new ArrayList<>();

        JsonArray pools = json.getAsJsonObject().get("pools").getAsJsonArray();

        ExtractinatorConfig config = TheExtractinator.CONFIG.extractinatorConfig;

        for (JsonElement pool : pools) {

            JsonArray entries = pool.getAsJsonObject().get("entries").getAsJsonArray();

            JsonElement condition = pool.getAsJsonObject().get("conditions");
            float rarityObject = condition.getAsJsonArray().get(0).getAsJsonObject().get("rarity").getAsFloat() * 100;

            for (JsonElement entry : entries) {

                JsonObject jsonObject = entry.getAsJsonObject();

                String name = jsonObject.get("name").getAsString();

                JsonElement functions = jsonObject.get("functions");

                Rarity rarity = null;
                int min = 1;
                int max = 1;

                if (functions != null) {

                    JsonElement function = functions.getAsJsonArray().get(0);

                    JsonElement count = function.getAsJsonObject().get("count");
                    min = count.getAsJsonObject().get("min").getAsInt();
                    max = count.getAsJsonObject().get("max").getAsInt();
                }

                Range<Integer> range = Range.between(min, max);

                if (rarityObject == config.commonItemChance_v1) {
                    rarity = Rarity.COMMON;
                } else if (rarityObject == config.uncommonItemChance_v1) {
                    rarity = Rarity.UNCOMMON;
                } else if (rarityObject == config.rareItemChance_v1) {
                    rarity = Rarity.RARE;
                } else if (rarityObject == config.veryRareItemChance_v1) {
                    rarity = Rarity.VERY_RARE;
                } else if (rarityObject == config.extremelyRareItemChance_v1) {
                    rarity = Rarity.EXTREMELY_RARE;
                }

                loot.add(new LootSlot(new Identifier(name), range, rarity));
            }
        }

        return loot;
    }
}
