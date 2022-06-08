package com.github.alexnijjar.the_extractinator.data;

import org.apache.commons.lang3.Range;

import com.github.alexnijjar.the_extractinator.util.Rarity;

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
