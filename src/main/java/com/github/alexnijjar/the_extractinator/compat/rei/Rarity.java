package com.github.alexnijjar.the_extractinator.compat.rei;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum Rarity implements SelectionListEntry.Translatable {
    COMMON, UNCOMMON, RARE, VERY_RARE, EXTREMELY_RARE;

    @Override
    public @NotNull String getKey() {
        return I18n.translate("text.autoconfig.the_extractinator.rarity." + name().toLowerCase(Locale.ROOT));
    }
}
