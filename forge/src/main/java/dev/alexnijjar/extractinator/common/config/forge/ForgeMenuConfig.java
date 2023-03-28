package dev.alexnijjar.extractinator.common.config.forge;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.common.config.ExtractinatorConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public class ForgeMenuConfig {
    public static void register() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> {
                    ResourcefulConfig config = Extractinator.CONFIGURATOR.getConfig(ExtractinatorConfig.class);
                    if (config == null) {
                        return null;
                    }
                    return new ConfigScreen(null, config);
                })
        );
    }
}
