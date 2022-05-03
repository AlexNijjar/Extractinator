package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.util.ModIdentifier;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStats {

    public static final Identifier BLOCKS_EXTRACTINATED = new ModIdentifier("blocks_extractinated");

    public static void register() {
        Registry.register(Registry.CUSTOM_STAT, "blocks_extractinated", BLOCKS_EXTRACTINATED);
    }
}
