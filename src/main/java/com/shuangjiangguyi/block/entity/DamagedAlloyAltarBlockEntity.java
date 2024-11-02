package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DamagedAlloyAltarBlockEntity extends BlockEntity {

    public DamagedAlloyAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DAMAGE_ALLOY_ALTAR, pos, state);
    }

    public int frequency = 0;

    public void tick(World world, BlockPos pos, BlockState state) {
        if (!world.isClient()) {
            if (frequency == 5) {
                world.setBlockState(pos, ModBlocks.ALLOY_ALTAR.getDefaultState());
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("frequency", frequency);
        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        frequency = nbt.getInt("frequency");
        super.readNbt(nbt, registryLookup);
    }
}
