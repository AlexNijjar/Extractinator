package com.github.alexnijjar.the_extractinator.client;

import java.util.ArrayList;
import java.util.List;

import com.github.alexnijjar.the_extractinator.client.networking.ModS2CPackets;
import com.github.alexnijjar.the_extractinator.client.renderer.ExtractinatorBlockEntityRenderer;
import com.github.alexnijjar.the_extractinator.client.renderer.ExtractinatorItemRenderer;
import com.github.alexnijjar.the_extractinator.data.LootTable;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
import com.github.alexnijjar.the_extractinator.registry.ModBlockEntities;
import com.github.alexnijjar.the_extractinator.registry.ModItems;
import com.github.alexnijjar.the_extractinator.util.ModIdentifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.util.Identifier;

public class TheExtractinatorClient implements ClientModInitializer {

    public static final Identifier GRINDER_PATH = new ModIdentifier("block/extractinator_grinder");
    public static final Identifier EXTRACTINATOR_BLOCK_PATH = new ModIdentifier("block/extractinator_block");

    // Only for REI, the server handles the real loot.
    public static List<SupportedBlock> supportedBlocks = new ArrayList<>();
    public static List<LootTable> lootTables = new ArrayList<>();

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, ctx -> new ExtractinatorBlockEntityRenderer());

        // Register baked models.
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(GRINDER_PATH));
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(EXTRACTINATOR_BLOCK_PATH));

        // Register item renderer.
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.extractinatorItem, new ExtractinatorItemRenderer());

        // Networking.
        ModS2CPackets.Register();
    }
}