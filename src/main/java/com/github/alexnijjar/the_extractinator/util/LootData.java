package com.github.alexnijjar.the_extractinator.util;

import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

public class LootData {

    public Identifier id;
    public Range<Integer> amount;
    public Range<Integer> slots;
    public float chance;


    public LootData(Identifier id, Range<Integer> amount, Range<Integer> slots, float chance) {
        this.id = id;
        this.amount = amount;
        this.slots = slots;
        this.chance = chance;
    }
}
