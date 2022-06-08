package com.github.alexnijjar.the_extractinator.data;

import java.util.List;

import com.github.alexnijjar.the_extractinator.util.Tier;

import net.minecraft.util.Identifier;

public class SupportedBlock {

    public Identifier id;
    public Tier tier;
    public float yield;
    public List<LootSlot> additionalDrops;
    public List<Identifier> disabledDrops;

    public SupportedBlock() {

    }

    public SupportedBlock(Identifier id, Tier tier, float yield, List<LootSlot> additionalDrops, List<Identifier> disabledDrops) {
        this.id = id;
        this.tier = tier;
        this.yield = yield;
        this.additionalDrops = additionalDrops;
        this.disabledDrops = disabledDrops;
    }
}
