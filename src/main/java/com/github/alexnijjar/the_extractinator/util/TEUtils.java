package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.LootTable;
import com.github.alexnijjar.the_extractinator.config.ExtractinatorConfig;
import com.github.alexnijjar.the_extractinator.config.SupportedModsConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class TEUtils {

    // Converts a Tier to a string tier.
    public static String tierPath(Tier tier) {
        String string = "";
        switch (tier) {
            case TIER_1 -> string = "tier_1";
            case TIER_2 -> string = "tier_2";
            case TIER_3 -> string = "tier_3";
            case TIER_4 -> string = "tier_4";
            case TIER_5 -> string = "tier_5";
        }
        return string;
    }

    // Converts a Rarity to a float percent.
    public static float rarityValue(Rarity rarity) {
        float percent = 0.0f;
        ExtractinatorConfig config = TheExtractinator.CONFIG.extractinatorConfig;
        switch (rarity) {
            case COMMON -> percent = config.commonItemChance;
            case UNCOMMON -> percent = config.uncommonItemChance;
            case RARE -> percent = config.rareItemChance;
            case VERY_RARE -> percent = config.veryRareItemChance;
            case EXTREMELY_RARE -> percent = config.extremelyRareItemChance;
        }
        return percent / 100;
    }

    public static boolean modIsLoaded(SupportedMods supportedMod) {
        String id = "";
        switch (supportedMod) {
            case MI -> id = "modern_industrialization";
            case TR -> id = "techreborn";
            case INDREV -> id = "indrev";
            case AE2 -> id = "ae2";
        }
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static Item getItem(Identifier id) {
        return Registry.ITEM.get(id);
    }

    public static String getModIDFromPath(String path) {
        String modID = "";

        if (path.contains("modern_industrialization")) {
            modID = "modern_industrialization";
        } else if (path.contains("techreborn/addon")) {
            modID = "techreborn/addon";
        } else if (path.contains("techreborn")) {
            modID = "techreborn";
        } else if (path.contains("indrev")) {
            modID = "indrev";
        } else if (path.contains("ae2")) {
            modID = "ae2";
        } else if (path.contains("minecraft/addon")) {
            modID = "minecraft/addon";
        } else if (path.contains("minecraft")) {
            modID = "minecraft";
        }
        return  modID;
    }

    public static Tier getTierFromPath(String path) {

        Tier tier = Tier.NONE;

        if (path.contains("tier_1")) {
            tier = Tier.TIER_1;
        } else if (path.contains("tier_2")) {
            tier = Tier.TIER_2;
        } else if (path.contains("tier_3")) {
            tier = Tier.TIER_3;
        } else if (path.contains("tier_4")) {
            tier = Tier.TIER_4;
        } else if (path.contains("tier_5")) {
            tier = Tier.TIER_5;
        }

        return tier;
    }

    public static boolean validLootTableMod(LootTable table) {

        String namespace = table.namespace;
        SupportedModsConfig support = TheExtractinator.CONFIG.extractinatorConfig.supportedMods;
        
        if (support.modern_industrialization_support && TEUtils.modIsLoaded(SupportedMods.MI)) {

            if (namespace.equals("modern_industrialization")) {
                return true;
            } else if (support.techreborn_support && TEUtils.modIsLoaded(SupportedMods.TR) && namespace.equals("techreborn/addon")) {
                return true;
            } else if (support.minecraft_support && TEUtils.modIsLoaded(SupportedMods.TR) && namespace.equals("minecraft/addon")) {
                return true;
            }
        } else if (support.techreborn_support && TEUtils.modIsLoaded(SupportedMods.TR)) {

            if (namespace.equals("techreborn")) {
                return true;
            } else if (support.minecraft_support && TEUtils.modIsLoaded(SupportedMods.TR) && namespace.equals("minecraft/addon")) {
                return true;
            }
        } else if (support.minecraft_support && namespace.equals("minecraft")) {
            return true;
        }

        if (support.indrev_support && TEUtils.modIsLoaded(SupportedMods.INDREV) && namespace.equals("indrev")) {
            return true;
        }

        if (support.ae2_support && TEUtils.modIsLoaded(SupportedMods.AE2) && namespace.equals("ae2")) {
            return true;
        }
        return false;
    }
}
