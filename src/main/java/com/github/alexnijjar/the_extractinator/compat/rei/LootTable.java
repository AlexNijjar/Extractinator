package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.util.Tier;

import java.util.List;

public final class LootTable {

    public Tier tier;
    public List<LootSlot> slots;
    public String namespace;


    public LootTable(Tier tier, List<LootSlot> slots, String namespace) {
        this.tier = tier;
        this.slots = slots;
        this.namespace = namespace;
    }
}
