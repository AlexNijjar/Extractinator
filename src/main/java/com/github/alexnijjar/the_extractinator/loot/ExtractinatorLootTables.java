package com.github.alexnijjar.the_extractinator.loot;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.LootSlot;
import com.github.alexnijjar.the_extractinator.compat.rei.LootTable;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.github.alexnijjar.the_extractinator.util.Tier;
import com.google.gson.JsonElement;
import net.minecraft.loot.LootManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Gets the loot tables from "gameplay/extractinator" for REI to display.
public class ExtractinatorLootTables {

    public static List<LootTable> getLoot(LootManager manager) {

        Set<Identifier> ids = manager.getTableIds();
        List<LootTable> tables = new ArrayList<>();

        ids.forEach((id) -> {

            if (id.getNamespace().equals(TheExtractinator.MOD_ID)) {

                String path = id.getPath();

                if (path.contains("gameplay")) {

                    JsonElement json = LootManager.toJson(manager.getTable(id));

                    // Converts the raw loot table json to a loot slot for REI to display.
                    List<LootSlot> loot = LootTableParser.parse(json);

                    Tier tier = TEUtils.getTierFromPath(path);

                    String modID = TEUtils.getModIDFromPath(path);

                    tables.add(new LootTable(tier, loot, modID));
                }
            }
        });

        return tables;
    }
}
