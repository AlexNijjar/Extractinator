package com.github.alexnijjar.the_extractinator.loot.condition;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.TELootTables;
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

        public Serializer() {}

        public void toJson(JsonObject jsonObject, RarityChanceLootCondition rarityChanceLootCondition, JsonSerializationContext jsonSerializationContext) {
            jsonObject.addProperty("rarity", rarityChanceLootCondition.rarity);
        }

        public RarityChanceLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {

            String value = JsonHelper.getString(jsonObject, "rarity");
            float chance = 0;

            switch (value) {
                case "common" -> chance = TheExtractinator.CONFIG.extractinatorConfig.commonItemChance;
                case "uncommon" -> chance = TheExtractinator.CONFIG.extractinatorConfig.uncommonItemChance;
                case "rare" -> chance = TheExtractinator.CONFIG.extractinatorConfig.rareItemChance;
                case "very_rare" -> chance = TheExtractinator.CONFIG.extractinatorConfig.veryRareItemChance;
                case "extremely_rare" -> chance = TheExtractinator.CONFIG.extractinatorConfig.extremelyRareItemChance;
                default -> TheExtractinator.LOGGER.error("The value \"" + value + "\" is not valid. Use \"common\", \"uncommon\", \"rare\", \"very_rare\", or \"extremely_rare.\"");
            }
            chance /= 100;
            return new RarityChanceLootCondition(chance);
        }
    }
}
