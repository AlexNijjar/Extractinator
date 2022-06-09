package com.github.alexnijjar.the_extractinator.client.rei.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.Range;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.data.LootSlot;
import com.github.alexnijjar.the_extractinator.util.Rarity;

import me.shedaniel.rei.api.common.entry.EntryStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class REIUtils {

    // Displays a tooltip.
    public static Function<EntryStack<?>, List<Text>> getSettings(LootSlot slot, float percent) {

        // TranslatableText rarityText = getRarity(slot.rarity);
        MutableText rarityText = getRarity(slot.rarity);
        MutableText percentText = Text.translatable("the_extractinator.rei.extractinator.yield_chance", Math.round(percent * 100.0) / 100.0);
        MutableText rangeText = getRange(slot.range);
        rangeText.setStyle(Style.EMPTY.withColor(Formatting.YELLOW));

        List<Text> tooltips = new ArrayList<>();
        tooltips.add(rarityText);
        if (TheExtractinator.CONFIG.extractinatorConfig.enableReiPercent) {
            tooltips.add(percentText);
        }
        tooltips.add(rangeText);

        return stack -> tooltips;
    }

    public static MutableText getRarity(Rarity rarity) {

        MutableText text = Text.translatable("");
        switch (rarity) {
        case COMMON -> {
            text = Text.translatable("text.autoconfig.the_extractinator.rarity.common");
            text.setStyle(Style.EMPTY.withColor(16777215)); // White
        }
        case UNCOMMON -> {
            text = Text.translatable("text.autoconfig.the_extractinator.rarity.uncommon");
            text.setStyle(Style.EMPTY.withColor(65344)); // Lime
        }
        case RARE -> {
            text = Text.translatable("text.autoconfig.the_extractinator.rarity.rare");
            text.setStyle(Style.EMPTY.withColor(65535)); // Cyan
        }
        case VERY_RARE -> {
            text = Text.translatable("text.autoconfig.the_extractinator.rarity.very_rare");
            text.setStyle(Style.EMPTY.withColor(16711935)); // Magenta
        }
        case EXTREMELY_RARE -> {
            text = Text.translatable("text.autoconfig.the_extractinator.rarity.extremely_rare");
            text.setStyle(Style.EMPTY.withColor(16711680)); // Red
        }
        }
        return text;
    }

    public static MutableText getRange(Range<Integer> range) {

        float multiplier = TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier;
        Integer min = (int) Math.ceil(range.getMinimum() * multiplier);
        Integer max = (int) Math.ceil(range.getMaximum() * multiplier);

        if (min.equals(max)) {
            return Text.translatable("");
        } else
            return Text.translatable("the_extractinator.rei.extractinator.drop_range", min, max);
    }
}
