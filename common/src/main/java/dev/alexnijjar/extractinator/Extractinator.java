package dev.alexnijjar.extractinator;

import dev.alexnijjar.extractinator.registry.*;

public class Extractinator {
    public static final String MOD_ID = "extractinator";

    public static void init() {
        ModBlocks.init();
        ModBlockEntities.init();
        ModItems.init();
        ModRecipeTypes.init();
        ModRecipeSerializers.init();
        ModOres.init();
    }
}