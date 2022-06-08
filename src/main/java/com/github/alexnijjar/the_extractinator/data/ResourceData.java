package com.github.alexnijjar.the_extractinator.data;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.ModIdentifier;
import com.github.alexnijjar.the_extractinator.util.ModUtils;
import com.github.alexnijjar.the_extractinator.util.Tier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class ResourceData {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void register() {

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {

            @Override
            public Identifier getFabricId() {
                return new ModIdentifier("output");
            }

            @Override
            public void reload(ResourceManager manager) {

                List<SupportedBlock> supportedBlocks = new ArrayList<>();
                List<LootTable> lootTables = new ArrayList<>();

                // Get the Supported Mods data.
                for (Identifier id : manager.findResources("output/supported_blocks", path -> path.getPath().endsWith(".json")).keySet()) {
                    try {
                        for (Resource resource : manager.getAllResources(id)) {
                            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
                            JsonObject jsonObject = JsonHelper.deserialize(GSON, reader, JsonObject.class);

                            if (jsonObject != null) {
                                supportedBlocks.add(SupportedBlocksParser.parse(jsonObject));
                            }
                        }
                    } catch (Exception e) {
                        TheExtractinator.LOGGER.error("Failed to load The Extractinator supported blocks from \"" + id.toString() + "\"", e);
                        e.printStackTrace();
                    }
                }

                // Get the Tier data.
                for (Identifier id : manager.findResources("output/tiers", path ->  path.getPath().endsWith(".json")).keySet()) {
                    try {
                        for (Resource resource : manager.getAllResources(id)) {
                            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
                            JsonObject jsonObject = JsonHelper.deserialize(GSON, reader, JsonObject.class);

                            if (jsonObject != null) {
                                List<LootSlot> loot = LootTableParser.parse(jsonObject);
                                String path = id.getPath();

                                Tier tier = ModUtils.stringToTier(path);

                                String mod = path.split("/")[2].split("/")[0];
                                lootTables.add(new LootTable(mod, tier, loot));
                            }
                        }
                    } catch (Exception e) {
                        TheExtractinator.LOGGER.error("Failed to load The Extractinator tiers from \"" + id.toString() + "\"", e);
                        e.printStackTrace();
                    }
                }
                TheExtractinator.supportedBlocks = supportedBlocks;
                TheExtractinator.lootTables = lootTables;
            }
        });
    }
}
