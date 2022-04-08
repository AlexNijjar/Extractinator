package com.github.alexnijjar.the_extractinator.networking;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.client.TheExtractinatorClient;
import com.github.alexnijjar.the_extractinator.compat.rei.util.Rarity;
import com.github.alexnijjar.the_extractinator.compat.rei.util.Tier;
import com.github.alexnijjar.the_extractinator.data.LootSlot;
import com.github.alexnijjar.the_extractinator.data.LootTable;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.List;

@Environment(EnvType.CLIENT)
public class TES2CPackets {

    public static void Register() {

        ClientPlayNetworking.registerGlobalReceiver(TEC2SPackets.LOOT_TABLE_PACKET_ID, (client, handler, buf, responseSender) -> {
            try {
                TheExtractinatorClient.lootTables = buf.readList(buf2 -> {
                    String namespace = buf2.readString();
                    Tier tier = buf2.readEnumConstant(Tier.class);
                    List<LootSlot> slots = buf2.readList(buf3 -> {
                        Identifier item = buf3.readIdentifier();
                        Rarity rarity = buf3.readEnumConstant(Rarity.class);
                        int[] range = buf3.readIntArray();
                        return new LootSlot(item, rarity, Range.between(range[0], range[1]));
                    });
                    return new LootTable(namespace, tier, slots);
                });
            } catch (Exception e) {
                TheExtractinator.LOGGER.error("Failed to get The Extractinator REI loot tables packet from server: " + e);
                e.printStackTrace();
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(TEC2SPackets.SUPPORTED_BLOCKS_PACKET_ID, (client, handler, buf, responseSender) -> {
            try {
                TheExtractinatorClient.supportedBlocks = buf.readList(buf2 -> {
                    Identifier id = buf2.readIdentifier();
                    Tier tier = buf2.readEnumConstant(Tier.class);
                    float yield = buf2.readFloat();

                    List<LootSlot> additionalDrops = buf2.readList(buf3 -> {
                        Identifier item = buf3.readIdentifier();
                        Rarity rarity = buf3.readEnumConstant(Rarity.class);
                        int[] range = buf3.readIntArray();
                        return new LootSlot(item, rarity, Range.between(range[0], range[1]));
                    });

                    List<Identifier> disabledDrops = buf2.readList(PacketByteBuf::readIdentifier);

                    return new SupportedBlock(id, tier, yield, additionalDrops, disabledDrops);
                });
            } catch (Exception e) {
                TheExtractinator.LOGGER.error("Failed to get The Extractinator REI supported blocks packet from server: " + e);
                e.printStackTrace();
            }
        });
    }
}
