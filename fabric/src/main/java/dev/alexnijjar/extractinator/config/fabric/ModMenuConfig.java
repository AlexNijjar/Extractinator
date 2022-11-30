package dev.alexnijjar.extractinator.config.fabric;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.config.ExtractinatorConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuConfig implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ResourcefulConfig config = Extractinator.CONFIGURATOR.getConfig(ExtractinatorConfig.class);
            if (config == null) {
                return null;
            }
            return new ConfigScreen(null, config);
        };
    }
}