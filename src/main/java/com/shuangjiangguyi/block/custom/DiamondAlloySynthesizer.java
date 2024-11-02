package com.shuangjiangguyi.block.custom;

import com.mojang.serialization.MapCodec;
import com.shuangjiangguyi.block.entity.DiamondAlloySynthesizerBlockEntity;
import com.shuangjiangguyi.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class DiamondAlloySynthesizer extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HOPPER_FACING;

    public static final MapCodec<DiamondAlloySynthesizer> CODEC = createCodec(DiamondAlloySynthesizer::new);
    public static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 9, 14),
            Block.createCuboidShape(5, 9, 0, 6, 12, 16),
            Block.createCuboidShape(9, 9, 0, 10, 12, 16),
            Block.createCuboidShape(0, 9, 0, 2, 12, 16),
            Block.createCuboidShape(13, 9, 0, 16, 12, 16),
            Block.createCuboidShape(2, 9, 0, 5, 12, 4),
            Block.createCuboidShape(6, 9, 0, 9, 12, 4),
            Block.createCuboidShape(2, 9, 12, 5, 12, 16),
            Block.createCuboidShape(6, 9, 12, 9, 12, 16),
            Block.createCuboidShape(10, 9, 12, 13, 12, 16),
            Block.createCuboidShape(10, 9, 0, 13, 12, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 9, 14),
            Block.createCuboidShape(0, 9, 10, 16, 12, 11),
            Block.createCuboidShape(0, 9, 6, 16, 12, 7),
            Block.createCuboidShape(0, 9, 14, 16, 12, 16),
            Block.createCuboidShape(0, 9, 0, 16, 12, 3),
            Block.createCuboidShape(0, 9, 11, 4, 12, 14),
            Block.createCuboidShape(0, 9, 7, 4, 12, 10),
            Block.createCuboidShape(12, 9, 11, 16, 12, 14),
            Block.createCuboidShape(12, 9, 7, 16, 12, 10),
            Block.createCuboidShape(12, 9, 3, 16, 12, 6),
            Block.createCuboidShape(0, 9, 3, 4, 12, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 9, 14),
            Block.createCuboidShape(10, 9, 0, 11, 12, 16),
            Block.createCuboidShape(6, 9, 0, 7, 12, 16),
            Block.createCuboidShape(14, 9, 0, 16, 12, 16),
            Block.createCuboidShape(0, 9, 0, 3, 12, 16),
            Block.createCuboidShape(11, 9, 12, 14, 12, 16),
            Block.createCuboidShape(7, 9, 12, 10, 12, 16),
            Block.createCuboidShape(11, 9, 0, 14, 12, 4),
            Block.createCuboidShape(7, 9, 0, 10, 12, 4),
            Block.createCuboidShape(3, 9, 0, 6, 12, 4),
            Block.createCuboidShape(3, 9, 12, 6, 12, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 9, 14),
            Block.createCuboidShape(0, 9, 5, 16, 12, 6),
            Block.createCuboidShape(0, 9, 9, 16, 12, 10),
            Block.createCuboidShape(0, 9, 0, 16, 12, 2),
            Block.createCuboidShape(0, 9, 13, 16, 12, 16),
            Block.createCuboidShape(12, 9, 2, 16, 12, 5),
            Block.createCuboidShape(12, 9, 6, 16, 12, 9),
            Block.createCuboidShape(0, 9, 2, 4, 12, 5),
            Block.createCuboidShape(0, 9, 6, 4, 12, 9),
            Block.createCuboidShape(0, 9, 10, 4, 12, 13),
            Block.createCuboidShape(12, 9, 10, 16, 12, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public DiamondAlloySynthesizer(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case WEST -> SHAPE_W;
            case SOUTH -> SHAPE_S;
            case EAST -> SHAPE_E;
            default -> SHAPE_N;
        };
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DiamondAlloySynthesizerBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DiamondAlloySynthesizerBlockEntity) {
                ItemScatterer.spawn(world, pos, (DiamondAlloySynthesizerBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = (DiamondAlloySynthesizerBlockEntity) world.getBlockEntity(pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.DIAMOND_ALLOY_SYNTHESIZER,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
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
        builder.add(FACING);
    }
}
