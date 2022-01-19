package com.github.alexnijjar.the_extractinator.blocks.entity;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import com.github.alexnijjar.the_extractinator.registry.TEBlockEntities;
import com.github.alexnijjar.the_extractinator.util.output.ExtractinatorBlockExtraction;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

public class ExtractinatorBlockEntity extends BlockEntity implements ExtractinatorInventory, SidedInventory {

    private final DefaultedList<ItemStack> ITEMS = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private BakedModel model;
    private int transferCooldown;

    public ExtractinatorBlockEntity(BlockPos pos, BlockState state) {
        super(TEBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, pos, state);
        this.transferCooldown = -1;
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, ExtractinatorBlockEntity blockEntity) {

        --blockEntity.transferCooldown;

        ItemStack input = blockEntity.ITEMS.get(0);
        BlockState aboveBlock = world.getBlockState(pos.up());

        if (aboveBlock.isAir()) {
            if (!blockEntity.needsCooldown()) {
                if (ExtractinatorBlockEntity.isSupported(input.getItem())) {

                    Block inputBlock = Block.getBlockFromItem(input.getItem());
                    placeBlockSilently(world, pos.up(), inputBlock);

                    blockEntity.ITEMS.get(0).decrement(1);

                    blockEntity.setCooldown(TheExtractinator.CONFIG.extractinatorConfig.inputCooldown);

                    markDirty(world, pos, state);
                }
            }
        } else if (ExtractinatorBlockEntity.isSupported(aboveBlock.getBlock().asItem())) {
            world.breakBlock(pos.up(), false);
            ExtractinatorBlockExtraction.extractMaterials(aboveBlock, (ServerWorld) world, pos);
        }
    }

    // This places a block above silently, preventing the block from playing a sound twice.
    public static void placeBlockSilently(World world, BlockPos pos, Block block) {
        WorldChunk chunk = world.getWorldChunk(pos);
        chunk.setBlockState(pos, block.getDefaultState(), false);
        world.updateNeighbors(pos, block);
    }

    public static boolean isSupported(Item item) {

        if (item != Items.AIR)
            for (SupportedBlocksConfig supportedBlocks : TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks) {

                Block supportedBlock = Registry.BLOCK.get(new Identifier(supportedBlocks.name));

                if (item == supportedBlock.asItem()) return true;
            }

        return false;
    }

    // Used for the ExtractinatorBlockEntityRenderer.
    public BakedModel getModel(World world) {
        if (this.model == null) {
            if (world.isClient) {
                MinecraftClient client = MinecraftClient.getInstance();
                BakedModelManager manager = client.getBakedModelManager();
                Identifier path = new TEIdentifier("block/extractinator_grinder");
                this.model = BakedModelManagerHelper.getModel(manager, path);
            }
        }
        return this.model;
    }

    private void setCooldown(int cooldown) {
        this.transferCooldown = cooldown;
    }

    private boolean needsCooldown() {
        return this.transferCooldown > 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, ITEMS);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, ITEMS);
        super.writeNbt(nbt);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return isSupported(stack.getItem());
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return ITEMS;
    }
}
