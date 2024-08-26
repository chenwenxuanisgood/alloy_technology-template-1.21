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
    private static final int OUTPUT_SLOT_1 = 4;

    private static final String NBT_PROGRESS_KEY = "alloy_synthesizer";

    private static final int PROGRESS_INDEX = 0;
    private static final int MAX_PROGRESS_INDEX = 1;

    private int progress = 0;
    private int maxProgress = 88;

    protected final PropertyDelegate propertyDelegate;

    public AlloySynthesizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_SYNTHESIZER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case PROGRESS_INDEX -> AlloySynthesizerBlockEntity.this.progress;
                    case MAX_PROGRESS_INDEX -> AlloySynthesizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case PROGRESS_INDEX -> AlloySynthesizerBlockEntity.this.progress = value;
                    case MAX_PROGRESS_INDEX -> AlloySynthesizerBlockEntity.this.maxProgress = value;
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
        nbt.putInt(NBT_PROGRESS_KEY, progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt(NBT_PROGRESS_KEY);
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (isOutputSlotAvailable() && hasRecipe()) {
            increaseCraftingProgress();
            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }

        markDirty(world, pos, state);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        // 确定铜锭和铁锭分别在哪个槽位
        decrementInputIfItemMatches(INPUT_SLOT_1, Items.IRON_INGOT);
        decrementInputIfItemMatches(INPUT_SLOT_2, Items.COPPER_INGOT);

        // 合成物品并放入输出槽
        addOutputItem(new ItemStack(ModItems.COPPER_IRON_ALLOY_INGOT));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        // 检查铁锭和铜锭是否存在于 INPUT_SLOT_1 和 INPUT_SLOT_2
        boolean hasIron = containsItem(INPUT_SLOT_1, Items.IRON_INGOT) || containsItem(INPUT_SLOT_2, Items.IRON_INGOT);
        boolean hasCopper = containsItem(INPUT_SLOT_1, Items.COPPER_INGOT) || containsItem(INPUT_SLOT_2, Items.COPPER_INGOT);

        // 检查岩浆桶和水桶是否在特定的槽位上
        boolean hasLava = getStack(INPUT_SLOT_3).getItem() == Items.LAVA_BUCKET;
        boolean hasWater = getStack(INPUT_SLOT_4).getItem() == Items.WATER_BUCKET;

        // 只有当所有条件满足且输出槽可用时，才返回 true
        return hasIron && hasCopper && hasLava && hasWater && canInsertIntoOutputSlot(ModItems.COPPER_IRON_ALLOY_INGOT);
    }

    private boolean containsItem(int slot, Item item) {
        return getStack(slot).getItem() == item;
    }


    private void decrementInputIfItemMatches(int slot, Item item) {
        ItemStack stack = getStack(slot);
        if (stack.getItem() == item) {
            stack.decrement(1);
        }
    }

    private void addOutputItem(ItemStack result) {
        ItemStack outputStack = getStack(OUTPUT_SLOT_1);
        if (outputStack.isEmpty()) {
            setStack(OUTPUT_SLOT_1, result);
        } else if (outputStack.getItem() == result.getItem()) {
            outputStack.increment(result.getCount());
        }
    }

    private boolean canInsertIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT_1).isEmpty() ||
                this.getStack(OUTPUT_SLOT_1).getItem() == item;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT_1).getCount() + result.getCount() <= this.getMaxCountPerStack();
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT_1).isEmpty() ||
                this.getStack(OUTPUT_SLOT_1).getCount() <= this.getMaxCountPerStack();
    }
}
