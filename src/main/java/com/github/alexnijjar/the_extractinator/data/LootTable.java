package com.github.alexnijjar.the_extractinator.data;

import com.github.alexnijjar.the_extractinator.compat.rei.util.Tier;

import java.util.List;

public final class LootTable {

    public String namespace;
    public Tier tier;
    public List<LootSlot> slots;

    public LootTable(String namespace, Tier tier, List<LootSlot> slots) {
        this.namespace = namespace;
        this.tier = tier;
        this.slots = slots;
    }
}
