package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.recipe.DynamicRecipeUtil;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

public final class TERecipes {

    public static JsonObject EXTRACTINATOR_RECIPE = null;

    // Creates dynamic recipes for extractinators based on the config.
    public static void register() {
        switch (TheExtractinator.CONFIG.extractinatorConfig.extractinatorRecipe_v1) {
            case MINECRAFT -> EXTRACTINATOR_RECIPE = DynamicRecipeUtil.createShapedRecipeJson(
                    Lists.newArrayList(
                            'H',
                            'I',
                            'P',
                            'C',
                            'R',
                            'G'
                    ),
                    Lists.newArrayList(
                            new Identifier("minecraft:hopper"),
                            new Identifier("minecraft:iron_block"),
                            new Identifier("minecraft:piston"),
                            new Identifier("minecraft:chain"),
                            new Identifier("minecraft:redstone_block"),
                            new Identifier("minecraft:glass_pane")
                    ),
                    Lists.newArrayList("item", "item", "item", "item", "item", "item"),
                    Lists.newArrayList(
                            " H ",
                            "IPC",
                            "IRG"
                    ),
                    new TEIdentifier("extractinator")
            );
            case MODERN_INDUSTRIALIZATION -> EXTRACTINATOR_RECIPE = DynamicRecipeUtil.createShapedRecipeJson(
                    Lists.newArrayList(
                            'I',
                            'P',
                            'S',
                            'B',
                            'C',
                            'M',
                            'E',
                            'G'
                    ),
                    Lists.newArrayList(
                            new Identifier("modern_industrialization:invar_rotary_blade"),
                            new Identifier("modern_industrialization:piston"),
                            new Identifier("modern_industrialization:steel_gear"),
                            new Identifier("modern_industrialization:basic_machine_hull"),
                            new Identifier("modern_industrialization:conveyor"),
                            new Identifier("modern_industrialization:motor"),
                            new Identifier("modern_industrialization:electronic_circuit"),
                            new Identifier("minecraft:glass_pane")
                    ),
                    Lists.newArrayList("item", "item", "item", "item", "item", "item", "item", "item"),
                    Lists.newArrayList(
                            "IPI",
                            "SBC",
                            "MEG"
                    ),
                    new TEIdentifier("extractinator")
            );
            case TECH_REBORN -> EXTRACTINATOR_RECIPE = DynamicRecipeUtil.createShapedRecipeJson(
                    Lists.newArrayList(
                            'D',
                            'E',
                            'A',
                            'P',
                            '#',
                            'M',
                            'B'
                    ),
                    Lists.newArrayList(
                            new Identifier("techreborn:diamond_grinding_head"),
                            new Identifier("techreborn:extractor"),
                            new Identifier("techreborn:advanced_circuit"),
                            new Identifier("minecraft:piston"),
                            new Identifier("c", "steel_plates"),
                            new Identifier("techreborn:advanced_machine_frame"),
                            new Identifier("techreborn:basic_display")
                    ),
                    Lists.newArrayList("item", "item", "item", "item", "tag", "item", "item"),
                    Lists.newArrayList(
                            "DED",
                            "APA",
                            "#MB"
                    ),
                    new TEIdentifier("extractinator")
            );
        }
    }
}
