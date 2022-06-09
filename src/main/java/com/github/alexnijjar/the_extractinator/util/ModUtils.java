package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.ExtractinatorConfig;
import net.fabricmc.loader.api.FabricLoader;

public final class ModUtils {

    public static boolean modLoaded(String string) {
        return FabricLoader.getInstance().isModLoaded(string);
    }

    public static Tier stringToTier(String string) {
        for (Tier tier : Tier.values()) {
            String tierId = tier.name().toLowerCase();
            if (string.contains(tierId)) {
                return tier;
            }
        }

        TheExtractinator.LOGGER.error("Invalid tier: " + string);
        return Tier.NONE;
    }

    public static Tier intToTier(int value) {

        if (value <= Tier.values().length) {
            return Tier.values()[value];
        } else {
            TheExtractinator.LOGGER.error("Invalid tier: " + value);
            return Tier.NONE;
        }
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

        return percent / 100.0f;
    }
}