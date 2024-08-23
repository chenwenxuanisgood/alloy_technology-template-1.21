package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.block.data.AlloySynthesizerData;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.recipe.AlloySynthesizerRecipe;
import com.shuangjiangguyi.screen.AlloySynthesizerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloySynthesizerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<AlloySynthesizerData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int INPUT_SLOT_5 = 4;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 88;
    public AlloySynthesizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_SYNTHESIZER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlloySynthesizerBlockEntity.this.progress;
                    case 1 -> AlloySynthesizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlloySynthesizerBlockEntity.this.progress = value;
                    case 1 -> AlloySynthesizerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("connect.alloy_synthesizer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlloySynthesizerScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    @Override
    public AlloySynthesizerData getScreenOpeningData(ServerPlayerEntity player) {
        return new AlloySynthesizerData(pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
        nbt.putInt("alloy_synthesizer", progress);
   }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt("alloy_synthesizer");
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (isOutputSlotAvailable()) {
            if (hasRecipe()) {
                this.increaseCakeProgress();
                markDirty(world, pos, state);
                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        if (getStack(INPUT_SLOT_3).getItem() == Items.WATER_BUCKET && getStack(INPUT_SLOT_4).getItem() == Items.COPPER_INGOT) {
            if ((getStack(INPUT_SLOT_1).getItem() == Items.IRON_INGOT) && (getStack(INPUT_SLOT_2).getItem() == Items.COPPER_INGOT) || (getStack(INPUT_SLOT_1).getItem() == Items.COPPER_INGOT) && (getStack(INPUT_SLOT_2).getItem() == Items.IRON_INGOT)) {
                ItemStack resultTool = new ItemStack(ModItems.COPPER_IRON_ALLOY_INGOT);
                craftOutput(INPUT_SLOT_5, resultTool, 1, INPUT_SLOT_1, 1, INPUT_SLOT_2);
            }
        }
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCakeProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {

    }

    private boolean canInsertIntoOutputSlot(Item item) {
        return this.getStack(INPUT_SLOT_5).isEmpty() ||
                this.getStack(INPUT_SLOT_5).getItem() == item;
    }

    private boolean canInsertAmoutIntoOutputSlot(ItemStack result) {
        return this.getStack(INPUT_SLOT_5).getCount() + result.getCount() <= this.getMaxCountPerStack();
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(INPUT_SLOT_5).isEmpty() ||
                this.getStack(INPUT_SLOT_5).getCount() <= this.getMaxCountPerStack();
    }
    private void craftOutput(int outputSlot, ItemStack result, int decrement, int inputSlot) {
        setStack(outputSlot, new ItemStack(result.getItem(), getStack(outputSlot).getCount() + result.getCount()));
        getStack(inputSlot).decrement(decrement);
    }
}