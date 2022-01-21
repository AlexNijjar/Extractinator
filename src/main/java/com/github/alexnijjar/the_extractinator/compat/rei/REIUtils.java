package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import me.shedaniel.rei.api.common.entry.EntryStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.function.Function;

public class REIUtils {

    // Displays a tooltip.
    public static Function<EntryStack<?>, List<Text>> getSettings(Rarity rarity, Range<Integer> range) {

        TranslatableText rarityText = getRarity(rarity);
        TranslatableText rangeText = getRange(range);
        rangeText.setStyle(Style.EMPTY.withColor(Formatting.YELLOW));
        return stack -> List.of(rarityText, rangeText);
    }

    public static TranslatableText getRarity(Rarity rarity) {

        TranslatableText text;
        switch (rarity) {
            case COMMON -> {
                text = new TranslatableText("text.autoconfig.the_extractinator.rarity.common");
                text.setStyle(Style.EMPTY.withColor(Formatting.GRAY));
            }
            case UNCOMMON -> {
                text = new TranslatableText("text.autoconfig.the_extractinator.rarity.uncommon");
                text.setStyle(Style.EMPTY.withColor(Formatting.YELLOW));
            }
            case RARE -> {
                text = new TranslatableText("text.autoconfig.the_extractinator.rarity.rare");
                text.setStyle(Style.EMPTY.withColor(Formatting.GREEN));
            }
            case VERY_RARE -> {
                text = new TranslatableText("text.autoconfig.the_extractinator.rarity.very_rare");
                text.setStyle(Style.EMPTY.withColor(Formatting.AQUA));
            }
            case EXTREMELY_RARE -> {
                text = new TranslatableText("text.autoconfig.the_extractinator.rarity.extremely_rare");
                text.setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE));
            }
            default -> {
                text = new TranslatableText("text.autoconfig.the_extractinator.rarity.invalid");
                text.setStyle(Style.EMPTY.withColor(Formatting.RED));
            }
        }
        return text;
    }

    public static TranslatableText getRange(Range<Integer> range) {

        float multiplier = TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier;
        Integer min = (int) Math.ceil(range.getMinimum() * multiplier);
        Integer max = (int) Math.ceil(range.getMaximum() * multiplier);

        if (min.equals(max)) return new TranslatableText("");
        else return new TranslatableText("the_extractinator.rei.extractinator.drop_range", min, max);
    }
}
