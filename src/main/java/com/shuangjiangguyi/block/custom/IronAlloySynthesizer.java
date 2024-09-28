package com.shuangjiangguyi.block.custom;

import com.shuangjiangguyi.block.entity.IronAlloySynthesizerBlockEntity;
import com.shuangjiangguyi.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import com.mojang.serialization.MapCodec;
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

public class IronAlloySynthesizer extends BlockWithEntity implements BlockEntityProvider {
    public static boolean[] canProduceAlloy = new boolean[4];

    public static final DirectionProperty FACING = Properties.HOPPER_FACING;
    public static final MapCodec<IronAlloySynthesizer> CODEC = createCodec(IronAlloySynthesizer::new);
    public static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 8, 14),
            Block.createCuboidShape(0, 8, 0, 16, 11, 3),
            Block.createCuboidShape(0, 8, 13, 16, 11, 16),
            Block.createCuboidShape(5, 8, 6, 11, 11, 10),
            Block.createCuboidShape(11, 8, 3, 16, 11, 13),
            Block.createCuboidShape(0, 8, 3, 5, 11, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public IronAlloySynthesizer(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronAlloySynthesizerBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof IronAlloySynthesizerBlockEntity) {
                ItemScatterer.spawn(world, pos, (IronAlloySynthesizerBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = (IronAlloySynthesizerBlockEntity) world.getBlockEntity(pos);
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
        return validateTicker(type, ModBlockEntities.IRON_ALLOY_SYNTHESIZER,
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
