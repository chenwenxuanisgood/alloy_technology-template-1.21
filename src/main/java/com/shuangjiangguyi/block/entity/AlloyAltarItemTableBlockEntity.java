package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.custom.AlloyAltarItemTable;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.particle.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AlloyAltarItemTableBlockEntity extends BlockEntity {
    public String storedItems = null;

    public AlloyAltarItemTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_ALTAR_ITEM_TABLE, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper) {
        super.writeNbt(nbt, wrapper);
        if (storedItems != null) {
            nbt.putString("Item", storedItems);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper) {
        super.readNbt(nbt, wrapper);
        storedItems = nbt.getString("Item");
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient){
            {
                int X = pos.getX();
                int Y = pos.getY();
                int Z = pos.getZ();
                double radius = 0.45;

                if (state == state.with(AlloyAltarItemTable.TYPE, AlloyAltarItemTable.Type.ACTIVATION)) {
                    for (double angle = 0; angle < 360; angle += 10) {
                        double x = X + 0.5 + radius * Math.cos(Math.toRadians(angle));
                        double y = Y + 0.75;
                        double z = Z + 0.5 + radius * Math.sin(Math.toRadians(angle));

                        world.addParticle(ModParticles.GOLD_PARTICLE, x, y, z, 0, 0, 0);
                    }
                    world.addParticle(ModParticles.GOLD_PARTICLE, X + 0.5, Y + 1.25, Z + 0.5, 0, 0, 0);
                }
                return;
            }
        }
        world.setBlockState(pos, AlloyAltarItemTable.getRelatedBlockState(state, world, pos, state.get(AlloyAltarItemTable.FACING)));
        markDirty(world, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
