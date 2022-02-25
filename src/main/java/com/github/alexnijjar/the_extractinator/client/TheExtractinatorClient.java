package com.github.alexnijjar.the_extractinator.client;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.client.renderer.ExtractinatorBlockEntityRenderer;
import com.github.alexnijjar.the_extractinator.compat.rei.util.LootSlot;
import com.github.alexnijjar.the_extractinator.compat.rei.util.LootTable;
import com.github.alexnijjar.the_extractinator.compat.rei.util.Rarity;
import com.github.alexnijjar.the_extractinator.compat.rei.util.Tier;
import com.github.alexnijjar.the_extractinator.registry.TEBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.List;

@Environment(EnvType.CLIENT)
public class TheExtractinatorClient implements ClientModInitializer {

    public static List<LootTable> lootTables;

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(TEBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, ctx -> new ExtractinatorBlockEntityRenderer());

        Identifier path = new Identifier(TheExtractinator.MOD_ID, "block/extractinator_grinder");
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(path));

        // REI
        // Called when the player enters the server. It gets the loot from the server and displays it in REI.
        ClientPlayNetworking.registerGlobalReceiver(TheExtractinator.REI_DISPLAY_LOOT_PACKET_ID, (client, handler, buf, responseSender) -> lootTables = buf.readList(b -> {

            Tier tier = b.readEnumConstant(Tier.class);

            List<LootSlot> slots = b.readList(b2 -> {
                Identifier item = b2.readIdentifier();
                Rarity rarity = b2.readEnumConstant(Rarity.class);
                int[] range = b2.readIntArray();

                return new LootSlot(item, Range.between(range[0], range[1]), rarity);
            });

            String namespace = b.readString();

            return new LootTable(tier, slots, namespace);
        }));
    }
}