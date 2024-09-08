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

    public static String recipes = null;
    public static String left_is_what = null;

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
        Item zhiZuoItem = null;
        switch (recipes) {
            case "COPPER_IRON_ALLOY_INGOT":
                if (left_is_what.equals("IRON_INGOT")) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.IRON_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.COPPER_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.COPPER_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.IRON_INGOT);
                }
                // 合成物品并放入输出槽
                zhiZuoItem = ModItems.COPPER_IRON_ALLOY_INGOT;
                temp = 1;
                recipes = null;
                left_is_what = null;
                break;
            case "COPPER_TIN_ALLOY_INGOT":
                if (left_is_what.equals("TIN_INGOT")) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.TIN_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.COPPER_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.COPPER_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.TIN_INGOT);
                }
                // 合成物品并放入输出槽
                zhiZuoItem = ModItems.COPPER_TIN_ALLOY_INGOT;
                recipes = null;
                left_is_what = null;
                break;
            case "TUNGSTEN_IRON_ALLOY_INGOT":
                if (left_is_what.equals("TUNGSTEN_INGOT")) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.TUNGSTEN_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, Items.IRON_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, Items.IRON_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.TUNGSTEN_INGOT);
                }
                // 合成物品并放入输出槽
                zhiZuoItem = ModItems.TUNGSTEN_IRON_ALLOY_INGOT;
                recipes = null;
                left_is_what = null;
                break;
            case "ALUMINIUM_TIN_ALLOY_INGOT":
                if (left_is_what.equals("ALUMINIUM_INGOT")) {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.ALUMINIUM_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.TIN_INGOT);
                }
                else {
                    decrementInputIfItemMatches(INPUT_SLOT_1, ModItems.TIN_INGOT);
                    decrementInputIfItemMatches(INPUT_SLOT_2, ModItems.ALUMINIUM_INGOT);
                }
                // 合成物品并放入输出槽
                recipes = null;
                left_is_what = null;
                break;
        }
        if (zhiZuoItem != null) {
            addOutputItem(new ItemStack(zhiZuoItem));
        }

    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean Determine_if_metal_is_in_input_slot(Item item) {
        return containsItem(INPUT_SLOT_1, item) || containsItem(INPUT_SLOT_2, item);
    }

    private boolean hasRecipe() {
        left_is_what = null;
        recipes = null;
        // 检查铁锭和铜锭是否存在于 INPUT_SLOT_1 和 INPUT_SLOT_2
        final boolean hasIron = Determine_if_metal_is_in_input_slot(Items.IRON_INGOT);
        final boolean hasCopper = Determine_if_metal_is_in_input_slot(Items.COPPER_INGOT);
        final boolean hasTin = Determine_if_metal_is_in_input_slot(ModItems.TIN_INGOT);
        final boolean hasTungsten = Determine_if_metal_is_in_input_slot(ModItems.TUNGSTEN_INGOT);
        final boolean hasAluminium = Determine_if_metal_is_in_input_slot(ModItems.ALUMINIUM_INGOT);

        String IronIngot = "IRON_INGOT";
        String CopperIngot = "COPPER_INGOT";
        String TinIngot = "TIN_INGOT";
        String TungstenIngot = "TUNGSTEN_INGOT";
        String AluminiumIngot = "ALUMINIUM_INGOT";

        // 检查岩浆桶和水桶是否在特定的槽位上
        final boolean hasLava = getStack(INPUT_SLOT_3).getItem() == Items.LAVA_BUCKET;
        final boolean hasWater = getStack(INPUT_SLOT_4).getItem() == Items.WATER_BUCKET;

        // 只有当所有条件满足且输出槽可用时，才返回 true
        if (hasLava && hasWater) {
            if (hasIron && hasCopper && canInsertIntoOutputSlot(ModItems.COPPER_IRON_ALLOY_INGOT)) {
                recipes = "COPPER_IRON_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, Items.IRON_INGOT)) {
                    left_is_what = IronIngot;
                } else {
                    left_is_what = CopperIngot;
                }
                return true;
            } else if (hasCopper && hasTin && canInsertIntoOutputSlot(ModItems.COPPER_TIN_ALLOY_INGOT)) {
                recipes = "COPPER_TIN_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, ModItems.TIN_INGOT)) {
                    left_is_what = TinIngot;
                } else {
                    left_is_what = CopperIngot;
                }
                return true;
            } else if (hasTungsten && hasIron && canInsertIntoOutputSlot(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)) {
                recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, ModItems.TUNGSTEN_INGOT)) {
                    left_is_what = TungstenIngot;
                } else {
                    left_is_what = IronIngot;
                }
                return true;
            } else if (hasAluminium && hasTin && canInsertIntoOutputSlot(ModItems.ALUMINIUM_TIN_ALLOY_INGOT)) {
                recipes = "ALUMINIUM_TIN_ALLOY_INGOT";
                if (containsItem(INPUT_SLOT_1, ModItems.ALUMINIUM_INGOT)) {
                    left_is_what = AluminiumIngot;
                } else {
                    left_is_what = TinIngot;
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

