package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.block.data.AlloyForgingTableData;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.screen.AlloyForgingTableScreenHandler;
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

public class AlloyForgingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<AlloyForgingTableData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private static final int ALLOY_INPUT_SLOT = 0;
    private static final int LAVA_INPUT_SLOT = 1;
    private static final int WATER_INPUT_SLOT = 2;
    private static final int TEMPLATE_SLOT = 3;
    private static final int HAMMER_INPUT_SLOT = 4;
    private static final int STICK_INPUT_SLOT = 5;
    private static final int OUTPUT_SLOT = 6;

    private static String recipes = null;
    private static Item zhiZuoItem = null;

    private static final String NBT_PROGRESS_KEY = "alloy_forging_table_block_entity";

    private static final int PROGRESS_INDEX = 0;
    private static final int MAX_PROGRESS_INDEX = 1;

    private int progress = 0;
    private int maxProgress = 88;

    protected final PropertyDelegate propertyDelegate;

    public AlloyForgingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_FORGING_TABLE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case PROGRESS_INDEX -> AlloyForgingTableBlockEntity.this.progress;
                    case MAX_PROGRESS_INDEX -> AlloyForgingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case PROGRESS_INDEX -> AlloyForgingTableBlockEntity.this.progress = value;
                    case MAX_PROGRESS_INDEX -> AlloyForgingTableBlockEntity.this.maxProgress = value;
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
        return Text.translatable("connect.alloy_forging_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlloyForgingTableScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    @Override
    public AlloyForgingTableData getScreenOpeningData(ServerPlayerEntity player) {
        return new AlloyForgingTableData(pos);
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
            case "TUNGSTEN_IRON_ALLOY_SWORD":
                decrementInputIfItemMatches(ALLOY_INPUT_SLOT, ModItems.TUNGSTEN_IRON_ALLOY_INGOT);
                decrementInputIfItemMatches(TEMPLATE_SLOT, ModItems.ALLOY_SWORD_TEMPLATE);
                decrementInputIfItemMatches(STICK_INPUT_SLOT, Items.STICK);
                zhiZuoItem = ModItems.TUNGSTEN_IRON_ALLOY_SWORD;
                recipes = null;
                break;
            case "COPPER_TIN_ALLOY_HAMMER":
                decrementInputIfItemMatches(ALLOY_INPUT_SLOT, ModItems.COPPER_TIN_ALLOY_INGOT);
                decrementInputIfItemMatches(TEMPLATE_SLOT, ModItems.ALLOY_HAMMER_TEMPLATE);
                decrementInputIfItemMatches(STICK_INPUT_SLOT, Items.STICK);
                zhiZuoItem = ModItems.COPPER_TIN_ALLOY_HAMMER;
                recipes = null;
                break;
            case "COPPER_IRON_ALLOY_HAMMER":
                decrementInputIfItemMatches(ALLOY_INPUT_SLOT, ModItems.COPPER_IRON_ALLOY_INGOT);
                decrementInputIfItemMatches(TEMPLATE_SLOT, ModItems.ALLOY_HAMMER_TEMPLATE);
                decrementInputIfItemMatches(STICK_INPUT_SLOT, Items.STICK);
                zhiZuoItem = ModItems.COPPER_IRON_ALLOY_HAMMER;
                recipes = null;
                break;
            case "ALUMINIUM_TIN_ALLOY_HAMMER":
                decrementInputIfItemMatches(ALLOY_INPUT_SLOT, ModItems.ALUMINIUM_TIN_ALLOY_INGOT);
                decrementInputIfItemMatches(TEMPLATE_SLOT, ModItems.ALLOY_HAMMER_TEMPLATE);
                decrementInputIfItemMatches(STICK_INPUT_SLOT, Items.STICK);
                zhiZuoItem = ModItems.ALUMINIUM_TIN_ALLOY_HAMMER;
                recipes = null;
                break;
            case "TUNGSTEN_IRON_ALLOY_HAMMER":
                decrementInputIfItemMatches(ALLOY_INPUT_SLOT, ModItems.TUNGSTEN_IRON_ALLOY_INGOT);
                decrementInputIfItemMatches(TEMPLATE_SLOT, ModItems.ALLOY_HAMMER_TEMPLATE);
                decrementInputIfItemMatches(STICK_INPUT_SLOT, Items.STICK);
                zhiZuoItem = ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
                recipes = null;
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

    private boolean hasRecipe() {
        recipes = null;
        // 检查铁锭和铜锭是否存在于 INPUT_SLOT_1 和 INPUT_SLOT_2
        final boolean HAS_TUNGSTEN_IRON_ALLOY = getStack(ALLOY_INPUT_SLOT).getItem() == ModItems.TUNGSTEN_IRON_ALLOY_INGOT;
        final boolean HAS_ALUMINIUM_TIN_ALLOY = getStack(ALLOY_INPUT_SLOT).getItem() == ModItems.ALUMINIUM_TIN_ALLOY_INGOT;
        final boolean HAS_COPPER_IRON_ALLOY = getStack(ALLOY_INPUT_SLOT).getItem() == ModItems.COPPER_IRON_ALLOY_INGOT;
        final boolean HAS_COPPER_TIN_ALLOY = getStack(ALLOY_INPUT_SLOT).getItem() == ModItems.COPPER_TIN_ALLOY_INGOT;
        final boolean HAS_STICK = getStack(STICK_INPUT_SLOT).getItem() == Items.STICK;
        final boolean HAS_ALLOY_SWORD_TEMPLATE = getStack(TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE;
        final boolean HAS_ALLOY_HAMMER_TEMPLATE = getStack(TEMPLATE_SLOT).getItem() == ModItems.ALLOY_HAMMER_TEMPLATE;
        final boolean GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.COPPER_TIN_ALLOY_HAMMER || getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.COPPER_IRON_ALLOY_HAMMER || getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.ALUMINIUM_TIN_ALLOY_HAMMER || getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
        final boolean GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.COPPER_IRON_ALLOY_HAMMER || getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.ALUMINIUM_TIN_ALLOY_HAMMER || getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
        final boolean GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.ALUMINIUM_TIN_ALLOY_HAMMER || getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
        final boolean GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).getItem() == ModItems.TUNGSTEN_IRON_ALLOY_HAMMER;
        final boolean hasLava = getStack(LAVA_INPUT_SLOT).getItem() == Items.LAVA_BUCKET;
        final boolean hasWater = getStack(WATER_INPUT_SLOT).getItem() == Items.WATER_BUCKET;

        // 只有当所有条件满足且输出槽可用时，才返回 true
        if (hasLava && hasWater) {
            if (GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER && HAS_ALLOY_SWORD_TEMPLATE && HAS_TUNGSTEN_IRON_ALLOY && HAS_STICK && canInsertIntoOutputSlot(null)) {
                recipes = "TUNGSTEN_IRON_ALLOY_SWORD";
                return true;
            }
            if (GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER && HAS_ALLOY_HAMMER_TEMPLATE && HAS_COPPER_TIN_ALLOY && HAS_STICK && canInsertIntoOutputSlot(null)) {
                recipes = "COPPER_TIN_ALLOY_HAMMER";
                return true;
            }
            if (GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER && HAS_ALLOY_HAMMER_TEMPLATE && HAS_COPPER_IRON_ALLOY && HAS_STICK && canInsertIntoOutputSlot(null)) {
                recipes = "COPPER_IRON_ALLOY_HAMMER";
                return true;
            }
            if (GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER && HAS_ALLOY_HAMMER_TEMPLATE && HAS_ALUMINIUM_TIN_ALLOY && HAS_STICK && canInsertIntoOutputSlot(null)) {
                recipes = "ALUMINIUM_TIN_ALLOY_HAMMER";
                return true;
            }
            if (GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER && HAS_ALLOY_HAMMER_TEMPLATE && HAS_TUNGSTEN_IRON_ALLOY && HAS_STICK && canInsertIntoOutputSlot(null)) {
                recipes = "TUNGSTEN_IRON_ALLOY_HAMMER";
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

    private void decrementInputIfItemMatches(int slot, Item item) {
        ItemStack stack = getStack(slot);
        if (stack.getItem() == item) {
            stack.decrement(1);
        }
    }

    private void addOutputItem(ItemStack result) {
        ItemStack outputStack = getStack(OUTPUT_SLOT);
        if (outputStack.isEmpty()) {
            setStack(OUTPUT_SLOT, result);
        } else if (outputStack.getItem() == result.getItem()) {
            outputStack.increment(result.getCount());
        }
    }

    private boolean canInsertIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getItem() == item;
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() <= this.getMaxCountPerStack();
    }
}

