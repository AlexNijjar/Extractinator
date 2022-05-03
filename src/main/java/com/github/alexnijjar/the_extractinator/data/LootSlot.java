package com.github.alexnijjar.the_extractinator.data;

import com.github.alexnijjar.the_extractinator.compat.rei.util.Rarity;

import org.apache.commons.lang3.Range;

import net.minecraft.util.Identifier;

public final class LootSlot {

    public Identifier id;
    public Rarity rarity;
    public Range<Integer> range;

    public LootSlot() {

    }

    public LootSlot(Identifier id, Rarity rarity, Range<Integer> range) {
        this.id = id;
        this.rarity = rarity;
        this.range = range;
    }
}
