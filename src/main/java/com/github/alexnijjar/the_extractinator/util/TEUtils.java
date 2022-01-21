package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.Rarity;
import com.github.alexnijjar.the_extractinator.compat.rei.Tier;
import com.github.alexnijjar.the_extractinator.config.ExtractinatorConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public final class TEUtils {

    public static String tierToString(Tier tier) {
        return tier.name().toLowerCase();
    }

    public static Tier stringToTier(String string) {

        for (Tier tier : Tier.values()) {
            String tierId = tier.name().toLowerCase();
            if (string.contains(tierId)) return tier;
        }

        TheExtractinator.LOGGER.error("Invalid tier: " + string);
        return Tier.NONE;
    }

    public static float rarityToPercent(Rarity rarity) {
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

    public static Rarity stringToRarity(String string) {
        return Rarity.valueOf(string.toUpperCase());
    }

    public static float stringToPercent(String string) {
        return rarityToPercent((stringToRarity(string)));
    }

    public static SupportedMods stringToSupportedMods(String string) {
        return SupportedMods.valueOf(string.toUpperCase());
    }

    public static boolean modLoaded(SupportedMods supportedMod) {
        return modLoaded(supportedMod.name().toLowerCase());
    }

    public static boolean modLoaded(String string) {
        return FabricLoader.getInstance().isModLoaded(string);
    }

    public static Item getItem(Identifier id) {
        return Registry.ITEM.get(id);
    }

    public static String modIdFromPath(String path) {

        for (SupportedMods mod : SupportedMods.values()) {
            String modId = mod.name().toLowerCase();
            String modIdAddon = mod.name().toLowerCase() + "/addon";

            if (path.contains(modIdAddon)) return modIdAddon;
            if (path.contains(modId)) return modId;
        }

        TheExtractinator.LOGGER.error("Invalid mod ID: " + path);
        return path;
    }

    public static boolean modEnabled(Identifier identifier) {
        return modEnabled(identifier.getNamespace());
    }

    public static boolean modEnabled(String string) {

        List<String> supportedMods = TheExtractinator.CONFIG.extractinatorConfig.modsSupported;

        for (SupportedMods mod : SupportedMods.values()) {
            String modId = mod.name().toLowerCase();
            if (string.equals(modId)) for (String supported : supportedMods) {
                if (supported.equals(modId)) return true;
            }
        }

        return false;
    }

    public static List<String> modsToList() {
        List<String> values = new ArrayList<>();

        for (SupportedMods mod : SupportedMods.values()) {
            values.add(mod.toString().toLowerCase());
        }

        return values;
    }

    public static String modToString(SupportedMods mod) {
        return mod.toString().toLowerCase();
    }

    public static boolean majorTechModInstalled() {
        return checkMod(SupportedMods.MODERN_INDUSTRIALIZATION) || checkMod(SupportedMods.TECHREBORN);
    }

    public static boolean checkMod(SupportedMods mod) {
        return checkMod(mod.name().toLowerCase());
    }

    // Checks if the mod is loaded and enabled in the config.
    public static boolean checkMod(String mod) {
        boolean loaded = modLoaded(stringToSupportedMods(mod));
        boolean supported = modEnabled(mod);

        return loaded && supported;
    }

    // Checks if the mod's loot table should be included, based, on the current mods installed.
    // For example, it prevents Mythic Metals from loading if a tech mod like Modern
    // Industrialization is installed.
    public static boolean validMod(String mod) {

        String modId = mod.replace("/addon", "");
        boolean valid = checkMod(modId);
        boolean isAddon = mod.contains("/addon");

        SupportedMods support = stringToSupportedMods(modId);

        // Enable Tech Reborn drops if Modern Industrialization is not installed.
        if (!checkMod(SupportedMods.MODERN_INDUSTRIALIZATION)) {
            if (support.equals(SupportedMods.TECHREBORN) && isAddon) return false;
            if (support.equals(SupportedMods.TECHREBORN)) return valid;
        } else {
            if (support.equals(SupportedMods.TECHREBORN) && isAddon) return valid;
            else if (support.equals(SupportedMods.TECHREBORN)) return false;
        }

        // Loot tables to use if a major tech mod is not installed.
        if (!majorTechModInstalled()) {
            if (support.equals(SupportedMods.MINECRAFT) && isAddon) return false;
            else if (support.equals(SupportedMods.MYTHICMETALS) || support.equals(SupportedMods.MINECRAFT))
                return valid;
        } else {
            if (support.equals(SupportedMods.MINECRAFT) && isAddon) return valid;
            else if (support.equals(SupportedMods.MINECRAFT) || support.equals(SupportedMods.MYTHICMETALS)) {
                return false;
            }
        }

        return valid;
    }
}
