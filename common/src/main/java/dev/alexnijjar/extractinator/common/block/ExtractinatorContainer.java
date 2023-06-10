package dev.alexnijjar.extractinator.common.block;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@MethodsReturnNonnullByDefault
public interface ExtractinatorContainer extends WorldlyContainer {

    NonNullList<ItemStack> getInventory();

    boolean isValidInput(ItemStack stack);

    @Override
    default int[] getSlotsForFace(Direction side) {
        int[] result = new int[getInventory().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    default void addItemToInput(ItemStack stack) {
        if (isValidInput(stack)) {
            ItemStack input = getItem(0);
            if (input.isEmpty() || ItemStack.isSameItem(stack, input)) {
                getInventory().set(0, new ItemStack(stack.getItem(), input.getCount() + 1));
                stack.shrink(1);
            }
        }
    }

    default ItemStack addItem(ItemStack stack) {
        ItemStack itemStack = stack.copy();
        this.moveItemToOccupiedSlotsWithSameType(itemStack);
        if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            this.moveItemToEmptySlots(itemStack);
            return itemStack.isEmpty() ? ItemStack.EMPTY : itemStack;
        }
    }

    private void moveItemToOccupiedSlotsWithSameType(ItemStack stack) {
        for (int i = 0; i < this.getInventory().size(); ++i) {
            ItemStack itemStack = this.getItem(i);
            if (ItemStack.isSameItemSameTags(itemStack, stack)) {
                this.moveItemsBetweenStacks(stack, itemStack);
                if (stack.isEmpty()) {
                    return;
                }
            }
        }
    }

    private void moveItemToEmptySlots(ItemStack stack) {
        for (int i = 1; i < this.getInventory().size(); ++i) {
            ItemStack itemStack = this.getItem(i);
            if (itemStack.isEmpty()) {
                this.setItem(i, stack.copy());
                stack.setCount(0);
                return;
            }
        }
    }

    private void moveItemsBetweenStacks(ItemStack stack, ItemStack other) {
        int i = Math.min(this.getMaxStackSize(), other.getMaxStackSize());
        int j = Math.min(stack.getCount(), i - other.getCount());
        if (j > 0) {
            other.grow(j);
            stack.shrink(j);
            this.setChanged();
        }

    }

    @Override
    default boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return index == 0 && isValidInput(itemStack);
    }

    @Override
    default boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index != 0;
    }

    @Override
    default int getContainerSize() {
        return getInventory().size();
    }

    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getInventory().size(); i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    default ItemStack getItem(int slot) {
        return getInventory().get(slot);
    }

    @Override
    default ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(getInventory(), slot, amount);
        if (!result.isEmpty()) {
            setChanged();
        }
        return result;
    }

    @Override
    default ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getInventory(), slot);
    }

    @Override
    default void setItem(int slot, ItemStack stack) {
        getInventory().set(slot, stack);
        if (stack.getCount() > 64) {
            stack.setCount(64);
        }
    }

    @Override
    default boolean stillValid(Player player) {
        return true;
    }

    @Override
    default void clearContent() {
        getInventory().clear();
    }
}