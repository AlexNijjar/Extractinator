package com.github.alexnijjar.the_extractinator.config;

import com.github.alexnijjar.the_extractinator.compat.rei.Rarity;
import com.github.alexnijjar.the_extractinator.compat.rei.Tier;

import java.util.Arrays;
import java.util.List;

public final class SupportedBlocks {

    public static final List<SupportedBlocksConfig> supportedBlocks = Arrays.asList(

            new SupportedBlocksConfig("the_extractinator:silt", Tier.TIER_4, 75),

            new SupportedBlocksConfig("minecraft:gravel", Tier.TIER_3, 20,
                    List.of(
                            new AdditionalDropsConfig("minecraft:flint", 3, 6, Rarity.COMMON)
                    )),

            new SupportedBlocksConfig("minecraft:sand", Tier.TIER_2, 25,
                    Arrays.asList(
                            new AdditionalDropsConfig("modern_industrialization:iron_dust", Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:copper_dust", 1, 3, Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:gold_dust", Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:coal_dust", 1, 2, Rarity.COMMON),
                            new AdditionalDropsConfig("techreborn:lazurite_dust", Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:diamond_tiny_dust", 1, 3, Rarity.VERY_RARE),
                            new AdditionalDropsConfig("modern_industrialization:emerald_tiny_dust", 3, 9, Rarity.VERY_RARE),
                            new AdditionalDropsConfig("modern_industrialization:ruby_dust", 2, 4, Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:sapphire_dust", 1, 2, Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:peridot_dust", 1, 2, Rarity.VERY_RARE),
                            new AdditionalDropsConfig("modern_industrialization:lignite_coal_dust", 1, 3, Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:silicon_dust", 2, 4, Rarity.RARE)
                    ),
                    Arrays.asList(
                            "minecraft:raw_iron",
                            "minecraft:raw_copper",
                            "minecraft:raw_gold",
                            "minecraft:coal",
                            "minecraft:lapis_lazuli",
                            "modern_industrialization:raw_tin",
                            "modern_industrialization:lignite_coal",
                            "modern_industrialization:silicon_dust"
                    )
            ),

            new SupportedBlocksConfig("minecraft:cobblestone", Tier.TIER_1, 10),

            new SupportedBlocksConfig("minecraft:stone", Tier.TIER_1, 15),

            new SupportedBlocksConfig("minecraft:cobbled_deepslate", Tier.TIER_2, 10),

            new SupportedBlocksConfig("minecraft:deepslate", Tier.TIER_2, 15),

            new SupportedBlocksConfig("minecraft:granite", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:granite_dust", 3, 6, Rarity.COMMON),
                            new AdditionalDropsConfig("minecraft:quartz", Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:uranium_dust", 2, 4, Rarity.RARE)
                    ),
                    List.of(
                            "modern_industrialization:uranium_dust"
                    )
            ),

            new SupportedBlocksConfig("minecraft:andesite", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:andesite_dust", 3, 6, Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:sodium_tiny_dust", 3, 9, Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:basalt_dust", Rarity.VERY_RARE)
                    )
            ),

            new SupportedBlocksConfig("minecraft:diorite", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:diorite_dust", 3, 6, Rarity.COMMON),
                            new AdditionalDropsConfig("minecraft:quartz", Rarity.RARE)
                    )),

            new SupportedBlocksConfig("minecraft:tuff", Tier.TIER_2, 25),

            new SupportedBlocksConfig("minecraft:calcite", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:calcite_dust", 1, 2, Rarity.COMMON),
                            new AdditionalDropsConfig("minecraft:quartz", 1, 3, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("minecraft:amethyst_shard", Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:carbon_dust", Rarity.RARE)
                    ),
                    List.of(
                            "minecraft:amethyst_shard"
                    )
            ),

            new SupportedBlocksConfig("minecraft:smooth_basalt", Tier.TIER_3, 25,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:basalt_dust", 3, 6, Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:iron_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:olivine_dust", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("minecraft:amethyst_shard", Rarity.RARE)
                    ),
                    Arrays.asList(
                            "minecraft:raw_iron",
                            "minecraft:amethyst_shard"
                    )
            ),

            new SupportedBlocksConfig("minecraft:netherrack", Tier.TIER_1, 5,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:netherrack_dust", 10, 20, Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:coal_dust", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:sulfur_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:saltpeter_dust", Rarity.UNCOMMON)
                    ),
                    List.of(
                            "minecraft:coal"
                    )
            ),

            new SupportedBlocksConfig("minecraft:blackstone", Tier.TIER_2, 25,
                    Arrays.asList(
                            new AdditionalDropsConfig("modern_industrialization:coal_dust", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:sulfur_dust", 1, 3, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:carbon_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:obsidian_dust", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:pyrite_dust", 2, 4, Rarity.RARE)
                    ),
                    Arrays.asList(
                            "minecraft:coal",
                            "techreborn:pyrite_dust"
                    )
            ),

            new SupportedBlocksConfig("minecraft:ancient_debris", Tier.TIER_5, 100,
                    Arrays.asList(
                            new AdditionalDropsConfig("minecraft:netherite_scrap", Rarity.COMMON),
                            new AdditionalDropsConfig("minecraft:blaze_powder", 3, 9, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("minecraft:magma_cream", 2, 4, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:sulfur_dust", 10, 20, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:carbon_dust", 3, 9, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("minecraft:diamond", 3, 9, Rarity.RARE)
                    ),
                    List.of(
                            "minecraft:diamond"
                    )
            ),

            new SupportedBlocksConfig("minecraft:end_stone", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:endstone_dust", 3, 6, Rarity.COMMON),
                            new AdditionalDropsConfig("modern_industrialization:tungsten_tiny_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:platinum_tiny_dust", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:ender_pearl_dust", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:ender_eye_dust", 1, 3, Rarity.VERY_RARE)
                    ),
                    Arrays.asList(
                            "modern_industrialization:tungsten_dust",
                            "techreborn:tungsten_small_dust",
                            "modern_industrialization:platinum_tiny_dust",
                            "techreborn:platinum_small_dust"
                    )
            ),

            new SupportedBlocksConfig("blockus:marble", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("techreborn:marble_dust", 3, 6, Rarity.COMMON),
                            new AdditionalDropsConfig("techreborn:calcite_dust", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:obsidian_dust", Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:carbon_dust", Rarity.RARE)
                    )
            ),
            new SupportedBlocksConfig("blockus:bluestone", Tier.TIER_1, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("minecraft:blue_dye", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:lazurite_dust", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:sapphire_dust", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:sodalite_dust", Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:sulfur_dust", 1, 3, Rarity.RARE)
                    )
            ),
            new SupportedBlocksConfig("blockus:limestone", Tier.TIER_2, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("modern_industrialization:iron_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:calcite_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("minecraft:quartz", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:pyrite_dust", 2, 4, Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:carbon_dust", Rarity.RARE)
                    ),
                    Arrays.asList(
                            "minecraft:raw_iron",
                            "techreborn:pyrite_dust"
                    )
            ),
            new SupportedBlocksConfig("promenade:carbonite", Tier.TIER_2, 25,
                    Arrays.asList(
                            new AdditionalDropsConfig("modern_industrialization:coal_dust", 1, 2, Rarity.UNCOMMON),
                            new AdditionalDropsConfig("modern_industrialization:carbon_dust", 1, 3, Rarity.UNCOMMON)
                    ),
                    List.of(
                            "minecraft:coal"
                    )
            ),
            new SupportedBlocksConfig("promenade:blunite", Tier.TIER_1, 20,
                    Arrays.asList(
                            new AdditionalDropsConfig("minecraft:blue_dye", Rarity.UNCOMMON),
                            new AdditionalDropsConfig("techreborn:lazurite_dust", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:sapphire_dust", Rarity.RARE),
                            new AdditionalDropsConfig("techreborn:sodalite_dust", Rarity.RARE),
                            new AdditionalDropsConfig("modern_industrialization:sulfur_dust", 1, 3, Rarity.RARE)
                    )
            )
    );
}
