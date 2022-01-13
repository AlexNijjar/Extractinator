package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import net.minecraft.util.Identifier;

public final class TEIdentifier extends Identifier {
    public TEIdentifier(String path) {
        super(TheExtractinator.MOD_ID, path);
    }

    public static Identifier getMcID(String path) {
        return new Identifier("minecraft", path);
    }
    public static Identifier getMiID(String path) {
        return new Identifier("modern_industrialization", path);
    }
    public static Identifier getTrID(String path) {
        return new Identifier("techreborn", path);
    }
    public static Identifier getIndrevID(String path) {
        return new Identifier("indrev", path);
    }
    public static Identifier getAe2ID(String path) {
        return new Identifier("ae2", path);
    }
}
