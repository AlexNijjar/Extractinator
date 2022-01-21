package com.github.alexnijjar.the_extractinator.compat.rei;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum Tier implements SelectionListEntry.Translatable {
    NONE, TIER_1, TIER_2, TIER_3, TIER_4, TIER_5;

    @Override
    public @NotNull String getKey() {
        return I18n.translate("text.autoconfig.the_extractinator.tier." + name().toLowerCase(Locale.ROOT));
    }
}
