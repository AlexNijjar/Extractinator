package com.github.alexnijjar.the_extractinator.data;

import com.github.alexnijjar.the_extractinator.compat.rei.util.Rarity;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

public class SupportedBlocksParser {

    public static SupportedBlock parse(JsonObject json) {

        SupportedBlock supportedBlock = new SupportedBlock();

        supportedBlock.id = new Identifier(json.get("name").getAsString());
        supportedBlock.tier = TEUtils.intToTier(json.get("tier").getAsInt());
        supportedBlock.yield = json.get("yield").getAsFloat();
        List<LootSlot> slots = new ArrayList<>();
        List<Identifier> disabledDrops = new ArrayList<>();


        additionalElements:
        for (JsonElement element : json.get("additional_drops").getAsJsonArray()) {
            JsonObject elementObject = element.getAsJsonObject();

            if (elementObject.has("conditional_mods")) {
                JsonArray mods = elementObject.getAsJsonArray("conditional_mods");
                for (JsonElement mod : mods)
                    if (!TEUtils.modLoaded(mod.getAsString()))
                        continue additionalElements;
            }
            if (elementObject.has("incompatible_mods")) {
                JsonArray mods = elementObject.getAsJsonArray("incompatible_mods");
                for (JsonElement mod : mods)
                    if (TEUtils.modLoaded(mod.getAsString()))
                        continue additionalElements;
            }

            LootSlot slot = new LootSlot();
            slot.id = new Identifier(elementObject.get("name").getAsString());

            int min = 1;
            int max = 1;

            if (elementObject.has("min"))
                min = elementObject.get("min").getAsInt();
            if (elementObject.has("max"))
                max = elementObject.get("max").getAsInt();

            slot.range = Range.between(min, max);
            slot.rarity = Rarity.valueOf(elementObject.get("rarity").getAsString().toUpperCase());
            slots.add(slot);
        }


        for (JsonElement element : json.get("disabled_drops").getAsJsonArray()) {
            disabledDrops.add(new Identifier(element.getAsString()));
        }

        supportedBlock.additionalDrops = slots;
        supportedBlock.disabledDrops = disabledDrops;

        return supportedBlock;
    }
}
