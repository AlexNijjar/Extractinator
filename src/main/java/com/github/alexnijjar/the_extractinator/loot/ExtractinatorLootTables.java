package com.github.alexnijjar.the_extractinator.loot;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.util.LootSlot;
import com.github.alexnijjar.the_extractinator.compat.rei.util.LootTable;
import com.github.alexnijjar.the_extractinator.compat.rei.util.Tier;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
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

                    Tier tier = TEUtils.stringToTier(path);

                    tables.add(new LootTable(tier, loot, TEUtils.modIdFromPath(path)));
                }
            }
        });

        return tables;
    }
}
