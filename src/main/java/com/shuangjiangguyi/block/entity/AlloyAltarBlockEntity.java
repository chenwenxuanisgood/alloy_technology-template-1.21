package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.particle.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.shuangjiangguyi.block.custom.AlloyAltar;

public class AlloyAltarBlockEntity extends BlockEntity {

    public AlloyAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_ALTAR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient){
            {
                if (state == state.with(AlloyAltar.TYPE, AlloyAltar.Type.ACTIVATION)) {
                    double radius = AlloyAltar.radius; // 设置园半径
                    char ys = AlloyAltar.ys;

                    for (double angle = 0; angle < 360; angle += 10) {
                        double x = pos.getX() + 0.5 + radius * Math.cos(Math.toRadians(angle));
                        double y = pos.getY() + 0.5;
                        double z = pos.getZ() + 0.5 + radius * Math.sin(Math.toRadians(angle));

                        world.addParticle(ModParticles.GOLD_PARTICLE, x, y, z, 0, 0, 0);
                    }
                    if (radius <= 0.5){
                        ys = '+';
                    }
                    if (radius >= 0.75){
                        ys = '-';
                    }
                    if (ys == '-') {
                        radius -= 0.0025;
                    }
                    if (ys == '+') {
                        radius += 0.0025;
                    }
                    AlloyAltar.radius = radius;
                    AlloyAltar.ys = ys;
                }
                return;
            }
        }
        world.setBlockState(pos, AlloyAltar.getRelatedBlockState(state, world, pos, state.get(AlloyAltar.FACING)));
        markDirty(world, pos, state);
    }
}
