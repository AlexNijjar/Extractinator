package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.client.TheExtractinatorClient;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import com.github.alexnijjar.the_extractinator.registry.TEBlocks;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class TEClientPlugin implements REIClientPlugin {

    static final CategoryIdentifier<ExtractinatorDisplay> CATEGORY = CategoryIdentifier.of(new TEIdentifier("extractinator"));

    @Override
    public void registerCategories(CategoryRegistry registry) {

        registry.add(new ExtractinatorCategory());
        registry.addWorkstations(CATEGORY, EntryStacks.of(TEBlocks.EXTRACTINATOR_BLOCK));
        registry.removePlusButton(CATEGORY);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {

        List<SupportedBlocksConfig> block = TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks_v2;

        for (int i = 0; i < block.size(); i++) {
            Identifier id = new Identifier(block.get(i).name);
            String namespace = id.getNamespace();

            // Only add screen if the proper mod is installed.
            if (namespace.equals("minecraft") || FabricLoader.getInstance().isModLoaded(namespace))
                registry.add(new ExtractinatorDisplay(id, i, TheExtractinatorClient.lootTables));
        }

        // Extractinator info.
        if (TEUtils.modLoaded("subterrestrial")) {
            DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStacks.of(TEBlocks.EXTRACTINATOR_BLOCK), new TranslatableText("the_extractinator.rei.extractinator.info.title"));
            info.lines(new TranslatableText("the_extractinator.rei.extractinator.info.body"));
            registry.add(info);
        }
    }
}
