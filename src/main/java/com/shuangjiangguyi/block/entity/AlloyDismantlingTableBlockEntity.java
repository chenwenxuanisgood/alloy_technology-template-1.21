package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.block.data.AlloyDismantlingTableData;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.screen.AlloyDismantlingTableScreenHandler;
import com.shuangjiangguyi.tags.ModItemTags;
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

public class AlloyDismantlingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<AlloyDismantlingTableData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private static final int ALLOY_ITEM_INPUT_SLOT = 0;
    private static final int LAVA_INPUT_SLOT = 1;
    private static final int WATER_INPUT_SLOT = 2;
    private static final int HAMMER_INPUT_SLOT = 3;
    private static final int EMPTY_TEMPLATE_SLOT = 4;
    private static final int OUTPUT_TEMPLATE_SLOT = 5;
    private static final int OUTPUT_SLOT = 6;

    private static String recipes = null;
    private static Item zhiZuoItem = null;

    private static final String NBT_PROGRESS_KEY = "alloy_dismantling_table_block_entity";

    private static final int PROGRESS_INDEX = 0;
    private static final int MAX_PROGRESS_INDEX = 1;

    private int progress = 0;
    private static int maxProgress = 88;

    protected final PropertyDelegate propertyDelegate;

    private static void setTime(int time, boolean hasEmptyTemplate) {
        if (hasEmptyTemplate) {
            maxProgress = (time * 15) * 2 + 30;
        }else {
            maxProgress = (time * 10) * 2 + 30;
        }
    }

    public AlloyDismantlingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_DISMANTLING_TABLE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case PROGRESS_INDEX -> AlloyDismantlingTableBlockEntity.this.progress;
                    case MAX_PROGRESS_INDEX -> maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case PROGRESS_INDEX -> AlloyDismantlingTableBlockEntity.this.progress = value;
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
        return Text.translatable("connect.alloy_dismantling_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlloyDismantlingTableScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    @Override
    public AlloyDismantlingTableData getScreenOpeningData(ServerPlayerEntity player) {
        return new AlloyDismantlingTableData(pos);
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
            setTimeProcess();
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

    private void setTimeProcess() {
        final boolean hasEmptyTemplate = getStack(EMPTY_TEMPLATE_SLOT).getItem() == ModItems.EMPTY_ALLOY_TEMPLATE;
        switch (recipes) {
            case "COPPER_TIN_ALLOY_INGOT":
                setTime(3, hasEmptyTemplate);
                break;
            case "COPPER_IRON_ALLOY_INGOT":
                setTime(4, hasEmptyTemplate);
                break;
            case "ALUMINIUM_TIN_ALLOY_INGOT":
                setTime(2, hasEmptyTemplate);
                break;
            case "TUNGSTEN_IRON_ALLOY_INGOT":
                setTime(9, hasEmptyTemplate);
                break;

        }
    }

    private void craftItem() {
        switch (recipes) {
            case "COPPER_TIN_ALLOY_INGOT":
                zhiZuoItem = ModItems.COPPER_TIN_ALLOY_INGOT;
                recipes = null;
                break;
            case "COPPER_IRON_ALLOY_INGOT":
                zhiZuoItem = ModItems.COPPER_IRON_ALLOY_INGOT;
                recipes = null;
                break;
            case "ALUMINIUM_TIN_ALLOY_INGOT":
                zhiZuoItem = ModItems.ALUMINIUM_TIN_ALLOY_INGOT;
                recipes = null;
                break;
            case "TUNGSTEN_IRON_ALLOY_INGOT":
                zhiZuoItem = ModItems.TUNGSTEN_IRON_ALLOY_INGOT;
                recipes = null;
                break;
        }
        getStack(HAMMER_INPUT_SLOT).setDamage(getStack(HAMMER_INPUT_SLOT).getDamage() + 5);
        if (getStack(HAMMER_INPUT_SLOT).getDamage() >= getStack(HAMMER_INPUT_SLOT).getMaxDamage()) {
            getStack(HAMMER_INPUT_SLOT).decrement(1);
        }
        if (getStack(EMPTY_TEMPLATE_SLOT).getItem() == ModItems.EMPTY_ALLOY_TEMPLATE) {
            if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.SWORD) && (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty())) {
                decrementInputIfItemMatches(EMPTY_TEMPLATE_SLOT, ModItems.EMPTY_ALLOY_TEMPLATE);
                addTemplateOutputItem(new ItemStack(ModItems.ALLOY_SWORD_TEMPLATE));
            }
            if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.HAMMER) && (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_HAMMER_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty())) {
                decrementInputIfItemMatches(EMPTY_TEMPLATE_SLOT, ModItems.EMPTY_ALLOY_TEMPLATE);
                addTemplateOutputItem(new ItemStack(ModItems.ALLOY_HAMMER_TEMPLATE));
            }
        }
        decrementInputIfItemMatches(ALLOY_ITEM_INPUT_SLOT, getStack(ALLOY_ITEM_INPUT_SLOT).getItem());
        addOutputItem(new ItemStack(zhiZuoItem));
        zhiZuoItem = null;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        final boolean HAS_TUNGSTEN_IRON_ALLOY_ITEM = getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.TUNGSTEN_IRON_ALLOY_ITEM);
        final boolean HAS_ALUMINIUM_TIN_ALLOY_ITEM = getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.ALUMINIUM_TIN_ALLOY_ITEM);
        final boolean HAS_COPPER_IRON_ALLOY_ITEM = getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.COPPER_IRON_ALLOY_ITEM);
        final boolean HAS_COPPER_TIN_ALLOY_ITEM = getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.COPPER_TIN_ALLOY_ITEM);
        final boolean HAS_EMPTY_TEMPLATE = getStack(EMPTY_TEMPLATE_SLOT).getItem() == ModItems.EMPTY_ALLOY_TEMPLATE;
        final boolean GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).isIn(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER);
        final boolean GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).isIn(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER);
        final boolean GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).isIn(ModItemTags.GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER);
        final boolean GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER = getStack(HAMMER_INPUT_SLOT).isIn(ModItemTags.GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER);
        final boolean hasLava = getStack(LAVA_INPUT_SLOT).getItem() == Items.LAVA_BUCKET;
        final boolean hasWater = getStack(WATER_INPUT_SLOT).getItem() == Items.WATER_BUCKET;
        recipes = null;
        if (hasLava && hasWater) {
            if (GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER && HAS_COPPER_TIN_ALLOY_ITEM && canInsertIntoOutputSlot(ModItems.COPPER_TIN_ALLOY_INGOT)) {
                if (HAS_EMPTY_TEMPLATE) {
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.SWORD)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "COPPER_TIN_ALLOY_INGOT";
                            return true;
                        }
                    }
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.HAMMER)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "COPPER_TIN_ALLOY_INGOT";
                            return true;
                        }
                    }
                }
                else {
                    recipes = "COPPER_TIN_ALLOY_INGOT";
                    return true;
                }
            }
            if (GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER && HAS_COPPER_IRON_ALLOY_ITEM && canInsertIntoOutputSlot(ModItems.COPPER_IRON_ALLOY_INGOT)) {
                if (HAS_EMPTY_TEMPLATE) {
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.SWORD)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "COPPER_IRON_ALLOY_INGOT";
                            return true;
                        }
                    }
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.HAMMER)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "COPPER_IRON_ALLOY_INGOT";
                            return true;
                        }
                    }
                }
                else {
                    recipes = "COPPER_IRON_ALLOY_INGOT";
                    return true;
                }
            }
            if (GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER && HAS_ALUMINIUM_TIN_ALLOY_ITEM && canInsertIntoOutputSlot(ModItems.ALUMINIUM_TIN_ALLOY_INGOT)) {
                if (HAS_EMPTY_TEMPLATE) {
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.SWORD)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "ALUMINIUM_TIN_ALLOY_INGOT";
                            return true;
                        }
                    }
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.HAMMER)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_HAMMER_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "ALUMINIUM_TIN_ALLOY_INGOT";
                            return true;
                        }
                    }
                }
                else {
                    recipes = "ALUMINIUM_TIN_ALLOY_INGOT";
                    return true;
                }
            }
            if (GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER && HAS_TUNGSTEN_IRON_ALLOY_ITEM && canInsertIntoOutputSlot(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)) {
                if (getStack(EMPTY_TEMPLATE_SLOT).getItem() == ModItems.EMPTY_ALLOY_TEMPLATE) {
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.SWORD)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                            return true;
                        }
                    }
                    if (getStack(ALLOY_ITEM_INPUT_SLOT).isIn(ModItemTags.HAMMER)) {
                        if (getStack(OUTPUT_TEMPLATE_SLOT).getItem() == ModItems.ALLOY_SWORD_TEMPLATE || getStack(OUTPUT_TEMPLATE_SLOT).isEmpty()) {
                            recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                            return true;
                        }
                        recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    recipes = "TUNGSTEN_IRON_ALLOY_INGOT";
                    return true;
                }
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
    private void addTemplateOutputItem(ItemStack result) {
        ItemStack outputStack = getStack(OUTPUT_TEMPLATE_SLOT);
        if (outputStack.isEmpty()) {
            setStack(OUTPUT_TEMPLATE_SLOT, result);
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

