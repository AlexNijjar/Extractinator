package com.github.alexnijjar.the_extractinator.compat.rei;

import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

public final class LootSlot {

    public Identifier item;
    public Rarity rarity;
    public Range<Integer> range;

    public LootSlot(Identifier item, Range<Integer> range, Rarity rarity) {
        this.item = item;
        this.range = range;
        this.rarity = rarity;
    }
}
