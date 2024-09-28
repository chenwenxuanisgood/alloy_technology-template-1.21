package com.shuangjiangguyi.screen;

import com.shuangjiangguyi.block.data.DiamondAlloySynthesizerData;
import com.shuangjiangguyi.block.data.IronAlloySynthesizerData;
import com.shuangjiangguyi.block.entity.DiamondAlloySynthesizerBlockEntity;
import com.shuangjiangguyi.block.entity.IronAlloySynthesizerBlockEntity;
import com.shuangjiangguyi.tags.ModItemTags;
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

public class DiamondAlloySynthesizerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final DiamondAlloySynthesizerBlockEntity blockEntity;
    public DiamondAlloySynthesizerScreenHandler(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate, BlockEntity blockEntity) {
        super(ModScreenHandlers.DIAMOND_ALLOY_SYNTHESIZER, syncId);
        checkSize((Inventory) blockEntity, 6);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);

        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (DiamondAlloySynthesizerBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 33, 12){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY);
            }
        });
        this.addSlot(new Slot(inventory, 1, 33, 32){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY);
            }
        });
        this.addSlot(new Slot(inventory, 2, 33, 52){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY);
            }
        });
        this.addSlot(new Slot(inventory, 3, 58, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.LAVA_BUCKET;
            }
        });
        this.addSlot(new Slot(inventory, 4, 87, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.WATER_BUCKET;
            }
        });
        this.addSlot(new Slot(inventory, 5, 113, 32){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModItemTags.ALLOY_INGOT);
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }
    public DiamondAlloySynthesizerScreenHandler(int syncId, PlayerInventory playerInventory, DiamondAlloySynthesizerData data) {
        this(syncId,playerInventory, new ArrayPropertyDelegate(6), playerInventory.player.getWorld().getBlockEntity(data.pos()));
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
