package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;

import net.minecraft.util.Identifier;

public final class ModIdentifier extends Identifier {

    public ModIdentifier(String path) {
        super(TheExtractinator.MOD_ID, path);
    }
}
