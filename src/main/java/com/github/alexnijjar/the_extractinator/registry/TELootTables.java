package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.loot.condition.RarityChanceLootCondition;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.util.registry.Registry;

public final class TELootTables {

    public static LootConditionType RARITY_CHANCE;

    public static void register() {
        RarityChanceLootCondition.Serializer serializer = new RarityChanceLootCondition.Serializer();
        RARITY_CHANCE = Registry.register(Registry.LOOT_CONDITION_TYPE, new TEIdentifier("rarity_chance"), new LootConditionType(serializer));
    }
}
