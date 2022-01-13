package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.util.Rarity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ExtractinatorItemEntry {
    public ItemStack stack;
    public Rarity rarity;
    public List<Integer> range;

    public ExtractinatorItemEntry(ItemStack stack, Rarity rarity, List<Integer> range) {
        this.stack = stack;
        this.rarity = rarity;
        this.range = range;
    }

}
