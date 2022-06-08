package com.github.alexnijjar.the_extractinator.networking;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.ModIdentifier;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ModC2SPackets {
    public static final Identifier LOOT_TABLE_PACKET_ID = new ModIdentifier("loot_table_packet");
    public static final Identifier SUPPORTED_BLOCKS_PACKET_ID = new ModIdentifier("supported_blocks_packet");

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, minecraftServer) -> {

            try {
                sender.sendPacket(LOOT_TABLE_PACKET_ID, createLootTableBuf());
            } catch (Exception e) {
                TheExtractinator.LOGGER.error("Failed to send The Extractinator REI loot table packet to client: " + e);
                e.printStackTrace();
            }

            try {
                sender.sendPacket(SUPPORTED_BLOCKS_PACKET_ID, createSupportedBlocksBuf());
            } catch (Exception e) {
                TheExtractinator.LOGGER.error("Failed to send The Extractinator REI supported blocks packet to client: " + e);
                e.printStackTrace();
            }

            if (minecraftServer.isDedicated()) {
                TheExtractinator.LOGGER.info("Sent REI Loot info to " + handler.player.getDisplayName().getString());
            }
        });
    }

    public static PacketByteBuf createLootTableBuf() throws RuntimeException {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeCollection(TheExtractinator.lootTables, (buf2, loot) -> {
            buf2.writeString(loot.namespace);
            buf2.writeEnumConstant(loot.tier);
            buf2.writeCollection(loot.slots, (buf3, slot) -> {
                buf3.writeIdentifier(slot.id);
                buf3.writeEnumConstant(slot.rarity);
                buf3.writeIntArray(new int[] { slot.range.getMinimum(), slot.range.getMaximum() });
            });
        });

        return buf;
    }

    public static PacketByteBuf createSupportedBlocksBuf() throws RuntimeException {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeCollection(TheExtractinator.supportedBlocks, (buf2, block) -> {
            buf2.writeIdentifier(block.id);
            buf2.writeEnumConstant(block.tier);
            buf2.writeFloat(block.yield);
            buf2.writeCollection(block.additionalDrops, (buf3, slot) -> {
                buf3.writeIdentifier(slot.id);
                buf3.writeEnumConstant(slot.rarity);
                buf3.writeIntArray(new int[] { slot.range.getMinimum(), slot.range.getMaximum() });
            });
            buf2.writeCollection(block.disabledDrops, PacketByteBuf::writeIdentifier);
        });

        return buf;
    }
}
