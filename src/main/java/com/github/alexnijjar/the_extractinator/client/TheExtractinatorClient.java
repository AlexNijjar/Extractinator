package com.github.alexnijjar.the_extractinator.client;

import com.github.alexnijjar.the_extractinator.client.renderer.ExtractinatorBlockEntityRenderer;
import com.github.alexnijjar.the_extractinator.client.renderer.ExtractinatorItemRenderer;
import com.github.alexnijjar.the_extractinator.data.LootTable;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
import com.github.alexnijjar.the_extractinator.networking.TES2CPackets;
import com.github.alexnijjar.the_extractinator.registry.TEBlockEntities;
import com.github.alexnijjar.the_extractinator.registry.TEItems;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TheExtractinatorClient implements ClientModInitializer {

    public static final Identifier GRINDER_PATH = new TEIdentifier("block/extractinator_grinder");
    public static final Identifier EXTRACTINATOR_BLOCK_PATH = new TEIdentifier("block/extractinator_block");

    // Only for REI, the server handles the real loot.
    public static List<SupportedBlock> supportedBlocks = new ArrayList<>();
    public static List<LootTable> lootTables = new ArrayList<>();

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(TEBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, ctx -> new ExtractinatorBlockEntityRenderer());

        // Register baked models.
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(GRINDER_PATH));
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(EXTRACTINATOR_BLOCK_PATH));

        // Register item renderer.
        BuiltinItemRendererRegistry.INSTANCE.register(TEItems.extractinatorItem, new ExtractinatorItemRenderer());

        // Networking.
        TES2CPackets.Register();
    }
}