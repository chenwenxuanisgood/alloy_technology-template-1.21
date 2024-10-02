package com.shuangjiangguyi.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.shuangjiangguyi.block.custom.AlloyAltar;

public class AlloyAltarBlockEntity extends BlockEntity {

    public AlloyAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_ALTAR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient)
            return;

        world.setBlockState(pos, AlloyAltar.getRelatedBlockState(state, world, pos, state.get(AlloyAltar.FACING)));
        markDirty(world, pos, state);
    }
}
