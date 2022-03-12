package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.client.TheExtractinatorClient;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
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
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class TheExtractinatorClientPlugin implements REIClientPlugin {

    static final CategoryIdentifier<ExtractinatorDisplay> CATEGORY = CategoryIdentifier.of(new TEIdentifier("extractinator"));

    @Override
    public void registerCategories(CategoryRegistry registry) {

        registry.add(new ExtractinatorCategory());
        registry.addWorkstations(CATEGORY, EntryStacks.of(TEBlocks.EXTRACTINATOR_BLOCK));
        registry.removePlusButton(CATEGORY);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {

        for (SupportedBlock block : TheExtractinatorClient.supportedBlocks) {
            String namespace = block.id.getNamespace();
            if (TEUtils.modLoaded(namespace))
                registry.add(new ExtractinatorDisplay(block));
        }

        // Extractinator info.
        if (TEUtils.modLoaded("subterrestrial")) {
            DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStacks.of(TEBlocks.EXTRACTINATOR_BLOCK), new TranslatableText("the_extractinator.rei.extractinator.info.title"));
            info.lines(new TranslatableText("the_extractinator.rei.extractinator.info.body"));
            registry.add(info);
        }
    }
}
