package com.shuangjiangguyi.screen;

import com.shuangjiangguyi.block.data.AlloyForgingTableData;
import com.shuangjiangguyi.block.entity.AlloyForgingTableBlockEntity;
import com.shuangjiangguyi.item.ModItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AlloyForgingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final AlloyForgingTableBlockEntity blockEntity;
    public AlloyForgingTableScreenHandler(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate, BlockEntity blockEntity) {
        super(ModScreenHandlers.ALLOY_FORGING_TABLE, syncId);
        checkSize((Inventory) blockEntity, 7);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);

        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (AlloyForgingTableBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 33, 32){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.COPPER_IRON_ALLOY_INGOT || stack.getItem() == ModItems.COPPER_TIN_ALLOY_INGOT || stack.getItem() == ModItems.TUNGSTEN_IRON_ALLOY_INGOT || stack.getItem() == ModItems.ALUMINIUM_TIN_ALLOY_INGOT;
            }
        });
        this.addSlot(new Slot(inventory, 1, 58, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.LAVA_BUCKET;
            }
        });
        this.addSlot(new Slot(inventory, 2, 87, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.WATER_BUCKET;
            }
        });
        this.addSlot(new Slot(inventory, 3, 58, 12){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.ALLOY_SWORD_TEMPLATE || stack.getItem() == ModItems.ALLOY_HAMMER_TEMPLATE;
            }
        });
        this.addSlot(new Slot(inventory, 4, 87, 12){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.COPPER_TIN_ALLOY_HAMMER || stack.getItem() == ModItems.COPPER_TIN_ALLOY_HAMMER || stack.getItem() == ModItems.COPPER_IRON_ALLOY_HAMMER || stack.getItem() == ModItems.ALUMINIUM_TIN_ALLOY_HAMMER || stack.getItem() == ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
            }
        });
        this.addSlot(new Slot(inventory, 5, 113, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.STICK;
            }
        });
        this.addSlot(new Slot(inventory, 6, 113, 32){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.TUNGSTEN_IRON_ALLOY_SWORD || stack.getItem() == ModItems.COPPER_TIN_ALLOY_HAMMER || stack.getItem() == ModItems.COPPER_IRON_ALLOY_HAMMER || stack.getItem() == ModItems.ALUMINIUM_TIN_ALLOY_HAMMER || stack.getItem() == ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }
    public AlloyForgingTableScreenHandler(int syncId, PlayerInventory playerInventory, AlloyForgingTableData data) {
        this(syncId,playerInventory, new ArrayPropertyDelegate(2), playerInventory.player.getWorld().getBlockEntity(data.pos()));
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory,j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot invSlot = this.slots.get(slot);
        if (invSlot != null && invSlot.hasStack()) {
            ItemStack originalStack = invSlot.getStack();
            newStack = originalStack.copy();
            if (slot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                invSlot.setStack(ItemStack.EMPTY);
            }else {
                invSlot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    public boolean isCraft() {
        return propertyDelegate.get(0) > 0;
    }
    public int getScaledProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int progressArrowSize = 52;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }


}
