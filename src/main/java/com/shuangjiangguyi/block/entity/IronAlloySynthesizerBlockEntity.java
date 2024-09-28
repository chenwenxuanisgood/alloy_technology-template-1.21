package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.block.data.IronAlloySynthesizerData;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.screen.IronAlloySynthesizerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IronAlloySynthesizerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<IronAlloySynthesizerData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    public static int temp;

    private static final int ALLOY_INPUT_SLOT_1 = 0;
    private static final int ALLOY_INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int OUTPUT_SLOT_1 = 4;

    private static String recipes = null;
    private static Item zhiZuoItem = null;

    private static final String NBT_PROGRESS_KEY = "iron_alloy_synthesizer";

    private static final int PROGRESS_INDEX = 0;
    private static final int MAX_PROGRESS_INDEX = 1;

    private int progress = 0;
    private static int maxProgress = 0;

    protected final PropertyDelegate propertyDelegate;

    private static void setTime(int time) {
        maxProgress = (time * 30) * 2 + 30;
    }

    public IronAlloySynthesizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_ALLOY_SYNTHESIZER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case PROGRESS_INDEX -> IronAlloySynthesizerBlockEntity.this.progress;
                    case MAX_PROGRESS_INDEX -> maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case PROGRESS_INDEX -> IronAlloySynthesizerBlockEntity.this.progress = value;
                    case MAX_PROGRESS_INDEX -> maxProgress = value;
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
        return Text.translatable("connect.iron_alloy_synthesizer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new IronAlloySynthesizerScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    @Override
    public IronAlloySynthesizerData getScreenOpeningData(ServerPlayerEntity player) {
        return new IronAlloySynthesizerData(pos);
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
        switch (recipes) {
            case "COPPER_IRON_ALLOY_INGOT":
                zhiZuoItem = ModItems.COPPER_IRON_ALLOY_INGOT;
                recipes = null;
                break;
            case "COPPER_TIN_ALLOY_INGOT":
                zhiZuoItem = ModItems.COPPER_TIN_ALLOY_INGOT;
                recipes = null;
                break;
            case "TUNGSTEN_IRON_ALLOY_INGOT":
                zhiZuoItem = ModItems.TUNGSTEN_IRON_ALLOY_INGOT;
                recipes = null;
                break;
            case "ALUMINIUM_TIN_ALLOY_INGOT":
                zhiZuoItem = ModItems.ALUMINIUM_TIN_ALLOY_INGOT;
                recipes = null;
                break;
        }
        decrementInputIfItemMatches(ALLOY_INPUT_SLOT_1, getStack(ALLOY_INPUT_SLOT_1).getItem());
        decrementInputIfItemMatches(ALLOY_INPUT_SLOT_2, getStack(ALLOY_INPUT_SLOT_2).getItem());
        addOutputItem(new ItemStack(zhiZuoItem));
        zhiZuoItem = null;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean determineIfMetalIsInInputSlot(Item item) {
        return containsItem(ALLOY_INPUT_SLOT_1, item) || containsItem(ALLOY_INPUT_SLOT_2, item);
    }

    private boolean hasRecipe() {
        recipes = null;
        // 检查铁锭和铜锭是否存在于 INPUT_SLOT_1 和 INPUT_SLOT_2
        final boolean HAS_IRON = determineIfMetalIsInInputSlot(Items.IRON_INGOT);
        final boolean HAS_COPPER = determineIfMetalIsInInputSlot(Items.COPPER_INGOT);
        final boolean HAS_TIN = determineIfMetalIsInInputSlot(ModItems.TIN_INGOT);
        final boolean HAS_TUNGSTEN = determineIfMetalIsInInputSlot(ModItems.TUNGSTEN_INGOT);
        final boolean HAS_ALUMINIUM = determineIfMetalIsInInputSlot(ModItems.ALUMINIUM_INGOT);

        // 检查岩浆桶和水桶是否在特定的槽位上
        final boolean hasLava = getStack(INPUT_SLOT_3).getItem() == Items.LAVA_BUCKET;
        final boolean hasWater = getStack(INPUT_SLOT_4).getItem() == Items.WATER_BUCKET;

        // 只有当所有条件满足且输出槽可用时，才返回 true
        if (hasLava && hasWater) {
            if (HAS_IRON && HAS_COPPER && canInsertIntoOutputSlot(ModItems.COPPER_IRON_ALLOY_INGOT)) {
                setTime(4);
                recipes = "COPPER_IRON_ALLOY_INGOT";
                return true;
            } else if (HAS_COPPER && HAS_TIN && canInsertIntoOutputSlot(ModItems.COPPER_TIN_ALLOY_INGOT)) {
                setTime(3);
                recipes = "COPPER_TIN_ALLOY_INGOT";
                return true;
            } else if (HAS_TUNGSTEN && HAS_IRON && canInsertIntoOutputSlot(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)) {
                setTime(9);
                recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                return true;
            } else if (HAS_ALUMINIUM && HAS_TIN && canInsertIntoOutputSlot(ModItems.ALUMINIUM_TIN_ALLOY_INGOT)) {
                setTime(2);
                recipes = "ALUMINIUM_TIN_ALLOY_INGOT";
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
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

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT_1).isEmpty() ||
                this.getStack(OUTPUT_SLOT_1).getCount() <= this.getMaxCountPerStack();
    }
}

