package com.github.alexnijjar.the_extractinator.compat.rei.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.data.LootSlot;
import me.shedaniel.rei.api.common.entry.EntryStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class REIUtils {

    // Displays a tooltip.
    public static Function<EntryStack<?>, List<Text>> getSettings(LootSlot slot, float percent) {

        // TranslatableText rarityText = getRarity(slot.rarity);
        TranslatableText rarityText = getRarity(slot.rarity);
        TranslatableText percentText = new TranslatableText("the_extractinator.rei.extractinator.yield_chance", Math.round(percent * 100.0) / 100.0);
        TranslatableText rangeText = getRange(slot.range);
        rangeText.setStyle(Style.EMPTY.withColor(Formatting.YELLOW));

        List<Text> tooltips = new ArrayList<>();
        if (!rarityText.getKey().isEmpty()) {
            tooltips.add(rarityText);
        }
        if (TheExtractinator.CONFIG.extractinatorConfig.enableReiPercent) {
            tooltips.add(percentText);
        }
        if (!rangeText.getKey().isEmpty()) {
            tooltips.add(rangeText);
        }

        return stack -> tooltips;
    }

    public static TranslatableText getRarity(Rarity rarity) {

        TranslatableText text = new TranslatableText("");
        switch (rarity) {
        case COMMON -> {
            text = new TranslatableText("text.autoconfig.the_extractinator.rarity.common");
            text.setStyle(Style.EMPTY.withColor(16777215)); // White
        }
        case UNCOMMON -> {
            text = new TranslatableText("text.autoconfig.the_extractinator.rarity.uncommon");
            text.setStyle(Style.EMPTY.withColor(65344)); // Lime
        }
        case RARE -> {
            text = new TranslatableText("text.autoconfig.the_extractinator.rarity.rare");
            text.setStyle(Style.EMPTY.withColor(65535)); // Cyan
        }
        case VERY_RARE -> {
            text = new TranslatableText("text.autoconfig.the_extractinator.rarity.very_rare");
            text.setStyle(Style.EMPTY.withColor(16711935)); // Magenta
        }
        case EXTREMELY_RARE -> {
            text = new TranslatableText("text.autoconfig.the_extractinator.rarity.extremely_rare");
            text.setStyle(Style.EMPTY.withColor(16711680)); // Red
        }
        }
        return text;
    }

    public static TranslatableText getRange(Range<Integer> range) {

        float multiplier = TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier;
        Integer min = (int) Math.ceil(range.getMinimum() * multiplier);
        Integer max = (int) Math.ceil(range.getMaximum() * multiplier);

        if (min.equals(max)) {
            return new TranslatableText("");
        }
        else
            return new TranslatableText("the_extractinator.rei.extractinator.drop_range", min, max);
    }
}
