package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum ExtractinatorRecipe implements SelectionListEntry.Translatable {
    NONE, MINECRAFT, MODERN_INDUSTRIALIZATION, TECH_REBORN;

    @Override
    public @NotNull String getKey() {
        return I18n.translate("text.autoconfig.the_extractinator.extractinator_recipe." + name().toLowerCase(Locale.ROOT));
    }
}
