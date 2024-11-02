package com.shuangjiangguyi.block.custom;

import com.mojang.serialization.MapCodec;
import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.block.entity.AlloyAltarBlockEntity;
import com.shuangjiangguyi.block.entity.ModBlockEntities;
import com.shuangjiangguyi.tags.ModItemTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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

import java.util.stream.Stream;

public class AlloyAltar extends BlockWithEntity {
    public static double radius = 0;
    public static char ys = ' ';
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public static final DirectionProperty FACING = Properties.HOPPER_FACING;
    public static final MapCodec<AlloyAltar> CODEC = createCodec(AlloyAltar::new);
    public static final EnumProperty<Type> TYPE = EnumProperty.of("type", Type.class);
    public AlloyAltar(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(TYPE, Type.NOT_ACTIVATION));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(AlloyAltar.TYPE) == AlloyAltar.Type.ACTIVATION) {
            AlloyAltarBlockEntity alloyAltarBlockEntity = (AlloyAltarBlockEntity) world.getBlockEntity(pos);
            if (stack.isIn(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER)) {
                if (alloyAltarBlockEntity.judgmentRecipes()) {
                    alloyAltarBlockEntity.output(player);
                }
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(1, 0, 1, 15, 2, 15),
            Block.createCuboidShape(13, 2, 1, 15, 6, 15),
            Block.createCuboidShape(1, 2, 1, 3, 6, 15),
            Block.createCuboidShape(3, 2, 1, 13, 6, 3),
            Block.createCuboidShape(3, 2, 13, 13, 6, 15)
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
        final boolean HAS_ITEM_TABLE =
                determineIfItHasBlock(pos.east(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.west(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.north(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.south(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.east(2).south(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.east(2).north(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.west(2).south(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world) &&
                determineIfItHasBlock(pos.west(2).north(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world);
        final boolean HAS_ALLOY_BLOCK = isRelatedBlock(world, pos);
        final boolean HAS_ALLOY_ALTAR = determinesIfThereIsABlockWithinARange(9, 9, 9, pos, ModBlocks.ALLOY_ALTAR, world);
        if (!(HAS_ALLOY_BLOCK && HAS_ITEM_TABLE)) {
            return state.with(TYPE, Type.NOT_ACTIVATION);
        }else if(HAS_ALLOY_ALTAR) {
            return state.with(TYPE, Type.NOT_ACTIVATION);
        }else {
            if (state.get(TYPE) == Type.NOT_ACTIVATION) {
                radius = 0.5;
                ys = ' ';
            }
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
        if (!(determineIfItIsEqualToASquare(3, 3, 1, pos.down(1), ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, world))) {
            return false;
        } else if (!(determineIfItIsEqualToASquare(7, 7, 1, pos.down(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world))) {
            return false;
        } else if (!(determineIfItIsEqualToASquare(11, 11, 1, pos.down(3), ModBlocks.COPPER_IRON_ALLOY_BLOCK, world))) {
            return false;
        } else if (!(determineIfItIsEqualToASquare(15, 15, 1, pos.down(4), ModBlocks.COPPER_TIN_ALLOY_BLOCK, world))) {
            return false;
        } else if (!(determineIfItHasBlock(pos.down(1).east(2).south(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).west(2).south(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).west(2).north(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).east(2).north(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).south(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).west(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).north(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world) &&
                determineIfItHasBlock(pos.down(1).east(2), ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, world)
                )){
            return false;
        } else return true;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyAltarBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.ALLOY_ALTAR,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
    }

    public enum Type implements StringIdentifiable {
        ACTIVATION("activation"),
        NOT_ACTIVATION("not_activation"),
        HAS_WATER("has_water"),
        FINISH("finish");

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
