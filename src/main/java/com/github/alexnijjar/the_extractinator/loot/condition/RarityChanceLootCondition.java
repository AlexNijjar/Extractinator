package com.github.alexnijjar.the_extractinator.loot.condition;

import com.github.alexnijjar.the_extractinator.registry.TELootTables;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

public class RarityChanceLootCondition implements LootCondition {

    final float rarity;

    RarityChanceLootCondition(float chance) {
        this.rarity = chance;
    }

    public LootConditionType getType() {
        return TELootTables.RARITY_CHANCE;
    }

    public boolean test(LootContext lootContext) {
        return lootContext.getRandom().nextFloat() < this.rarity;
    }

    public static class Serializer implements JsonSerializer<RarityChanceLootCondition> {

        public Serializer() {
        }

        public void toJson(JsonObject jsonObject, RarityChanceLootCondition rarityChanceLootCondition, JsonSerializationContext jsonSerializationContext) {
            jsonObject.addProperty("rarity", rarityChanceLootCondition.rarity);
        }

        public RarityChanceLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {

            String value = JsonHelper.getString(jsonObject, "rarity");

            return new RarityChanceLootCondition(TEUtils.stringToPercent(value));
        }
    }
}
