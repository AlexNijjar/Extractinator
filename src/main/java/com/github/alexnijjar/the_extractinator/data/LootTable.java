package com.github.alexnijjar.the_extractinator.data;

import java.util.List;

import com.github.alexnijjar.the_extractinator.util.Tier;

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
