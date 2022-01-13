package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TEStats {

    public static final Identifier BLOCKS_EXTRACTINATED = new TEIdentifier("blocks_extractinated");

    public static void register() {
        Registry.register(Registry.CUSTOM_STAT, "blocks_extractinated", BLOCKS_EXTRACTINATED);
    }
}
