package com.shuangjiangguyi.block.custom;

import com.mojang.serialization.MapCodec;
import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.block.entity.AlloyAltarItemTableBlockEntity;
import com.shuangjiangguyi.block.entity.AlloyDismantlingTableBlockEntity;
import com.shuangjiangguyi.block.entity.ModBlockEntities;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.tags.ModItemTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public class AlloyAltarItemTable extends BlockWithEntity {

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    private String itemToString(Item item) {
        return item.toString();
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        AlloyAltarItemTableBlockEntity alloyAltarItemTableBlockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(pos);
        if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            if (alloyAltarItemTableBlockEntity.storedItems != null) {
                String[] itemString = {
                        itemToString(ModItems.COPPER_TIN_ALLOY_INGOT),
                        itemToString(ModItems.COPPER_IRON_ALLOY_INGOT),
                        itemToString(ModItems.ALUMINIUM_TIN_ALLOY_INGOT),
                        itemToString(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)
                };
                Item[] Items = {
                        ModItems.COPPER_TIN_ALLOY_INGOT,
                        ModItems.COPPER_IRON_ALLOY_INGOT,
                        ModItems.ALUMINIUM_TIN_ALLOY_INGOT,
                        ModItems.TUNGSTEN_IRON_ALLOY_INGOT
                };
                for (int i = 0; i < itemString.length; i++) {
                    if (Objects.equals(alloyAltarItemTableBlockEntity.storedItems, itemString[i])) {
                        player.getInventory().insertStack(new ItemStack(Items[i], 1));
                    }
                }
                alloyAltarItemTableBlockEntity.storedItems = null;
            }
        } else {
            if (alloyAltarItemTableBlockEntity.storedItems == null) {
                if (player.getStackInHand(Hand.MAIN_HAND).isIn(ModItemTags.ALLOY_INGOT)) {
                    alloyAltarItemTableBlockEntity.storedItems = player.getStackInHand(Hand.MAIN_HAND).getItem().toString();
                    player.getStackInHand(Hand.MAIN_HAND).decrement(1);
                }
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    public static final DirectionProperty FACING = Properties.HOPPER_FACING;
    public static final MapCodec<AlloyAltarItemTable> CODEC = createCodec(AlloyAltarItemTable::new);
    public static final EnumProperty<Type> TYPE = EnumProperty.of("type", Type.class);
    public AlloyAltarItemTable(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(TYPE, Type.NOT_ACTIVATION));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(4, 0, 4, 12, 2, 12),
            Block.createCuboidShape(6, 2, 6, 10, 8, 10),
            Block.createCuboidShape(3, 8, 3, 13, 9, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    public static BlockState getRelatedBlockState(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
        final boolean HAS_ALLOY_BLOCK = isRelatedBlock(world, pos);
        if (!HAS_ALLOY_BLOCK) {
            return state.with(TYPE, Type.NOT_ACTIVATION);
        }else {
            return state.with(TYPE, Type.ACTIVATION);
        }
    }

    private static boolean determinesIfThereIsABlockWithinARange(int length, int width, int height, BlockPos pos, Block block, WorldAccess world) {
        boolean whetherThereIsABlockInTheRange = false;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                for (int l = 0; l < length; l++) {
                    int x = pos.getX() - width / 2 + w;
                    int y = pos.getY() - height / 2 + h;
                    int z = pos.getZ() - length / 2 + l;
                    if (!new BlockPos(pos.getX(), pos.getY(), pos.getZ()).equals(new BlockPos(x, y, z))) {
                        if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == block) {
                            whetherThereIsABlockInTheRange = true;
                            break;
                        }
                    }
                }
                if (whetherThereIsABlockInTheRange) {
                    break;
                }
            }
            if (whetherThereIsABlockInTheRange) {
                break;
            }
        }
        return whetherThereIsABlockInTheRange;
    }

    private static boolean determineIfItHasItemTable(BlockPos pos, WorldAccess world) {
        return determineIfItHasBlock(pos, ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world);
    }

    private static boolean judgeTheSurroundingItemTable(BlockPos pos, WorldAccess world) {
        if (determineIfItHasBlock(pos.east(2).north(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.east(2).north(2), world);
            return (determineIfItHasItemTable(pos.north(2), world) &&
                    determineIfItHasItemTable(pos.north(2).east(4), world) &&
                    determineIfItHasItemTable(pos.north(4), world) &&
                    determineIfItHasItemTable(pos.north(4).east(2), world) &&
                    determineIfItHasItemTable(pos.north(4).east(4), world) &&
                    determineIfItHasItemTable(pos.east(2), world) &&
                    determineIfItHasItemTable(pos.east(4), world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.north(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.north(2), world);
            return (determineIfItHasItemTable(pos.east(2), world) &&
                    determineIfItHasItemTable(pos.west(2), world) &&
                    determineIfItHasItemTable(pos.north(2).east(2), world) &&
                    determineIfItHasItemTable(pos.north(2).west(2), world) &&
                    determineIfItHasItemTable(pos.north(4), world) &&
                    determineIfItHasItemTable(pos.north(4).east(2), world) &&
                    determineIfItHasItemTable(pos.north(4).west(2), world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.west(2).north(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.west(2).north(2), world);
            return (determineIfItHasItemTable(pos.north(2), world) &&
                    determineIfItHasItemTable(pos.north(2).west(4), world) &&
                    determineIfItHasItemTable(pos.north(4), world) &&
                    determineIfItHasItemTable(pos.north(4).west(2), world) &&
                    determineIfItHasItemTable(pos.north(4).west(4), world) &&
                    determineIfItHasItemTable(pos.west(2), world) &&
                    determineIfItHasItemTable(pos.west(4), world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.west(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.west(2), world);
            return (determineIfItHasItemTable(pos.north(2), world) &&
                    determineIfItHasItemTable(pos.south(2), world) &&
                    determineIfItHasItemTable(pos.west(2).north(2), world) &&
                    determineIfItHasItemTable(pos.west(2).south(2), world) &&
                    determineIfItHasItemTable(pos.west(4), world) &&
                    determineIfItHasItemTable(pos.west(4).north(2), world) &&
                    determineIfItHasItemTable(pos.west(4).south(2),world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.west(2).south(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.west(2).south(2), world);
            return (determineIfItHasItemTable(pos.south(2), world) &&
                    determineIfItHasItemTable(pos.south(2).west(4), world) &&
                    determineIfItHasItemTable(pos.south(4), world) &&
                    determineIfItHasItemTable(pos.south(4).west(2), world) &&
                    determineIfItHasItemTable(pos.south(4).west(4), world) &&
                    determineIfItHasItemTable(pos.west(2), world) &&
                    determineIfItHasItemTable(pos.west(4), world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.south(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.south(2), world);
            return (determineIfItHasItemTable(pos.east(2), world) &&
                    determineIfItHasItemTable(pos.west(2), world) &&
                    determineIfItHasItemTable(pos.south(2).east(2), world) &&
                    determineIfItHasItemTable(pos.south(2).west(2), world) &&
                    determineIfItHasItemTable(pos.south(4), world) &&
                    determineIfItHasItemTable(pos.south(4).east(2), world) &&
                    determineIfItHasItemTable(pos.south(4).west(2), world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.east(2).south(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.east(2).south(2), world);
            return (determineIfItHasItemTable(pos.south(2), world) &&
                    determineIfItHasItemTable(pos.south(2).east(4), world) &&
                    determineIfItHasItemTable(pos.south(4), world) &&
                    determineIfItHasItemTable(pos.south(4).east(2), world) &&
                    determineIfItHasItemTable(pos.south(4).east(4), world) &&
                    determineIfItHasItemTable(pos.east(2), world) &&
                    determineIfItHasItemTable(pos.east(4), world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else if (determineIfItHasBlock(pos.east(2), ModBlocks.ALLOY_ALTAR, world)) {
            BlockState state = determinesIfThereIsABlockWithinARangeState(pos.east(2), world);
            return (determineIfItHasItemTable(pos.north(2), world) &&
                    determineIfItHasItemTable(pos.south(2), world) &&
                    determineIfItHasItemTable(pos.east(2).north(2), world) &&
                    determineIfItHasItemTable(pos.east(2).south(2), world) &&
                    determineIfItHasItemTable(pos.east(4), world) &&
                    determineIfItHasItemTable(pos.east(4).north(2), world) &&
                    determineIfItHasItemTable(pos.east(4).south(2),world)) && state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION;
        } else return false;
    }

    public static BlockState determinesIfThereIsABlockWithinARangeState(BlockPos pos, WorldAccess world) {
        return world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));

    }

    private static boolean determineIfItIsEqualToASquare(int length, int width, int height, BlockPos pos, Block block, WorldAccess world) {
        boolean notEqual = false;
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                for (int l = 0; l < length; l++) {
                    int x = centerX - width / 2 + w;
                    int y = centerY - height / 2 + h;
                    int z = centerZ - length / 2 + l;

                    if (!(world.getBlockState(new BlockPos(x, y, z)).getBlock() == block)) {
                        notEqual = true;
                        break;
                    }
                }
                if (notEqual) {
                    break;
                }
            }
            if (notEqual) {
                break;
            }
        }
        return !notEqual;
    }

    private static boolean determineIfItHasBlock(int z, int y, int x, Block block, WorldAccess world) {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock() == block;
    }

    public static boolean determineIfItHasBlock(BlockPos pos, Block block, WorldAccess world) {
        return world.getBlockState(pos).getBlock() == block;
    }

    public static boolean isRelatedBlock(WorldAccess world, BlockPos pos) {
        if (((!determinesIfThereIsABlockWithinARange(5, 5, 1, pos, ModBlocks.ALLOY_ALTAR, world)))) {
            return false;
        }
        else if (!judgeTheSurroundingItemTable(pos, world)) {
            return false;
        } else return true;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyAltarItemTableBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.ALLOY_ALTAR_ITEM_TABLE,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
    }

    public enum Type implements StringIdentifiable {
        ACTIVATION("activation"),
        NOT_ACTIVATION("not_activation");

        private final String name;
        Type(String name) {
            this.name = name;
        }
        @Override
        public String asString() {
            return name;
        }
    }
}
