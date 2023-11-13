package tech.alexnijjar.extractinator.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import tech.alexnijjar.extractinator.common.config.ExtractinatorConfig;

public class ExtractinatorBlockItem extends BlockItem {
    public ExtractinatorBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack stack, BlockState state) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof ExtractinatorBlockEntity entity) {
                CompoundTag tag = stack.getOrCreateTag();
                if (tag.contains("RemainingUsages")) {
                    entity.remainingUsages = tag.getInt("RemainingUsages");
                } else {
                    entity.remainingUsages = ExtractinatorConfig.extractinatorDurability;
                }
            }
        }
        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }
}