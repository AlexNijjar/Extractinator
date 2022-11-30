package dev.alexnijjar.extractinator;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import dev.alexnijjar.extractinator.config.ExtractinatorConfig;
import dev.alexnijjar.extractinator.registry.*;

public class Extractinator {
    public static final String MOD_ID = "extractinator";
    public static final Configurator CONFIGURATOR = new Configurator();

    public static void init() {
        CONFIGURATOR.registerConfig(ExtractinatorConfig.class);

        ModBlocks.init();
        ModBlockEntities.init();
        ModItems.init();
        ModRecipeTypes.init();
        ModRecipeSerializers.init();
        ModOres.init();
    }
}