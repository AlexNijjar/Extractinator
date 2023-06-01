package dev.alexnijjar.extractinator;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import dev.alexnijjar.extractinator.common.config.ExtractinatorConfig;
import dev.alexnijjar.extractinator.common.registry.*;

public class Extractinator {
    public static final String MOD_ID = "extractinator";
    public static final Configurator CONFIGURATOR = new Configurator();

    public static void init() {
        CONFIGURATOR.registerConfig(ExtractinatorConfig.class);

        ModBlocks.BLOCKS.init();
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.init();
        ModItems.init();
        ModRecipeTypes.RECIPE_TYPES.init();
        ModRecipeSerializers.RECIPE_SERIALIZERS.init();
        ModFeatures.FEATURES.init();
    }
}