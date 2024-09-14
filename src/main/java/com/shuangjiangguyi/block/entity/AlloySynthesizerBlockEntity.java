package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.block.data.AlloySynthesizerData;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.screen.AlloySynthesizerScreenHandler;
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

public class AlloySynthesizerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<AlloySynthesizerData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    public static int temp;

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int OUTPUT_SLOT_1 = 4;

    final String IRON_INGOT = "IRON_INGOT";
    final String COPPER_INGOT = "COPPER_INGOT";
    final String TIN_INGOT = "TIN_INGOT";
    final String TUNGSTEN_INGOT = "TUNGSTEN_INGOT";
    final String ALUMINIUM_INGOT = "ALUMINIUM_INGOT";

    private static String recipes = null;
    private static String left_is_what = null;
    private static Item zhiZuoItem = null;

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
        switch (recipes) {
            case "COPPER_IRON_ALLOY_INGOT":
                if (left_is_what.equals(IRON_INGOT)) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.IRON_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.COPPER_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.COPPER_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.IRON_INGOT);
                }
                zhiZuoItem = ModItems.COPPER_IRON_ALLOY_INGOT;
                temp = 1;
                recipes = null;
                left_is_what = null;
                break;
            case "COPPER_TIN_ALLOY_INGOT":
                if (left_is_what.equals(TIN_INGOT)) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.TIN_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.COPPER_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.COPPER_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.TIN_INGOT);
                }
                zhiZuoItem = ModItems.COPPER_TIN_ALLOY_INGOT;
                recipes = null;
                left_is_what = null;
                break;
            case "TUNGSTEN_IRON_ALLOY_INGOT":
                if (left_is_what.equals(TUNGSTEN_INGOT)) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.TUNGSTEN_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.IRON_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.IRON_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.TUNGSTEN_INGOT);
                }
                zhiZuoItem = ModItems.TUNGSTEN_IRON_ALLOY_INGOT;
                recipes = null;
                left_is_what = null;
                break;
            case "ALUMINIUM_TIN_ALLOY_INGOT":
                if (left_is_what.equals(ALUMINIUM_INGOT)) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.ALUMINIUM_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.TIN_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.TIN_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.ALUMINIUM_INGOT);
                }
                zhiZuoItem = ModItems.ALUMINIUM_TIN_ALLOY_INGOT;
                recipes = null;
                left_is_what = null;
                break;
        }
        addOutputItem(new ItemStack(zhiZuoItem));
        zhiZuoItem = null;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean determineIfMetalIsInInputSlot(Item item) {
        return containsItem(INPUT_SLOT_1, item) || containsItem(INPUT_SLOT_2, item);
    }

    private boolean hasRecipe() {
        left_is_what = null;
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
                recipes = "COPPER_IRON_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, Items.IRON_INGOT)) {
                    left_is_what = IRON_INGOT;
                } else {
                    left_is_what = COPPER_INGOT;
                }
                return true;
            } else if (HAS_COPPER && HAS_TIN && canInsertIntoOutputSlot(ModItems.COPPER_TIN_ALLOY_INGOT)) {
                recipes = "COPPER_TIN_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, ModItems.TIN_INGOT)) {
                    left_is_what = TIN_INGOT;
                } else {
                    left_is_what = COPPER_INGOT;
                }
                return true;
            } else if (HAS_TUNGSTEN && HAS_IRON && canInsertIntoOutputSlot(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)) {
                recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, ModItems.TUNGSTEN_INGOT)) {
                    left_is_what = TUNGSTEN_INGOT;
                } else {
                    left_is_what = IRON_INGOT;
                }
                return true;
            } else if (HAS_ALUMINIUM && HAS_TIN && canInsertIntoOutputSlot(ModItems.ALUMINIUM_TIN_ALLOY_INGOT)) {
                recipes = "ALUMINIUM_TIN_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, ModItems.ALUMINIUM_INGOT)) {
                    left_is_what = ALUMINIUM_INGOT;
                } else {
                    left_is_what = TIN_INGOT;
                }
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

