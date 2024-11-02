package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.mixin.ServerPlayerEntityMixin;
import com.shuangjiangguyi.mixinInterface.ServerPlayerEntityMixinAccessor;
import com.shuangjiangguyi.particle.ModParticles;
import com.shuangjiangguyi.tags.ModBlockTags;
import com.shuangjiangguyi.tags.ModItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtInt;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.shuangjiangguyi.block.custom.AlloyAltar;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class AlloyAltarBlockEntity extends BlockEntity {

    public AlloyAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_ALTAR, pos, state);
    }

    public String recipes = null;

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
                    double radius2 = 2;
                    for (double angle = 0; angle < 360; angle += 10) {
                        double x = pos.getX() + 0.5 + radius2 * Math.cos(Math.toRadians(angle));
                        double z = pos.getZ() + 0.5 + radius2 * Math.sin(Math.toRadians(angle));

                        world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                    }
                    for (double angle = -2; angle < 0; angle += 0.1) {
                        double x = pos.getX() + 0.5;
                        double z = pos.getZ() + 0.5 + angle;

                        world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                    }

                    if (judgmentRecipes()) {
                        double X = pos.getX();
                        double Y = pos.getY() + 0.25;
                        double Z = pos.getZ();
                        for (double i = 0.1; i <= 0.9; i+= 0.1) {
                            for (double k = 0.1; k <= 0.9; k+= 0.1) {
                                world.addParticle(ModParticles.GOLD_PARTICLE, X + i, Y, Z + k, 0, 0, 0);
                            }
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5 + angle;
                            double z = pos.getZ() + 0.5;

                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5 + -angle;
                            double z = pos.getZ() + 0.5;

                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5;
                            double z = pos.getZ() + 0.5 + -angle;

                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5 + angle;
                            double z = pos.getZ() + 0.5 + angle;

                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5 + -angle;
                            double z = pos.getZ() + 0.5 + angle;
                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5 + angle;
                            double z = pos.getZ() + 0.5 + -angle;

                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                        for (double angle = -2; angle < 0; angle += 0.1) {
                            double x = pos.getX() + 0.5 + -angle;
                            double z = pos.getZ() + 0.5 + -angle;
                            world.addParticle(ModParticles.GOLD_PARTICLE, x, pos.getY(), z, 0, 0, 0);
                        }
                    }
                }

                return;
            }
        }
        world.setBlockState(pos, AlloyAltar.getRelatedBlockState(state, world, pos, state.get(AlloyAltar.FACING)));
        markDirty(world, pos, state);
    }

    public void output(PlayerEntity player) {
        jianqu(pos, world);
        if (!world.isClient()) {
            switch (recipes) {
                case "SEA_ALLOY_HORN":
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.675, pos.getZ() + 0.5, new ItemStack(ModItems.SEA_ALLOY_HORN)));
                    break;
            }
            ServerPlayerEntityMixinAccessor playerEntityMixinAccessor = (ServerPlayerEntityMixinAccessor) player;
            NbtDouble alloyAltarProficiency = playerEntityMixinAccessor.getAlloyAltarProficiency();
            boolean noDamagedYesOrNo = false;
            int random = new Random().nextInt(100) + 1;
            int gaiLv = 20 + (((int) (alloyAltarProficiency.doubleValue())) * 5);
            for (int i = 0; i < gaiLv; i++) {
                int randomI = new Random().nextInt(100) + 1;
                if (randomI == random) {
                    noDamagedYesOrNo = true;
                    AlloyTechnology.LOGGER.info(randomI + String.valueOf(random));
                    break;
                }
            }
            if (!noDamagedYesOrNo) {
                world.setBlockState(pos, ModBlocks.DAMAGED_ALLOY_ALTAR.getDefaultState());
            }
            playerEntityMixinAccessor.setAlloyAltarProficiency(NbtDouble.of(playerEntityMixinAccessor.getAlloyAltarProficiency().doubleValue() + 0.01));
        }
    }
    public Item getOutputItem() {
        Item item = null;
        switch (recipes) {
            case "SEA_ALLOY_HORN":
                item = ModItems.SEA_ALLOY_HORN;
        }
        return item;
    }

    private void jianqu(BlockPos pos, WorldAccess world) {
        BlockPos blockPos = pos.east(2).north(2);
        AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.north(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.west(2).north(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.west(2).south(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.south(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.east(2).south(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.east(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
        blockPos = pos.west(2);
        blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
        blockEntity.storedItems = null;
    }

    public boolean judgmentRecipes() {
        final boolean SEA_ALLOY_HORN = judgmentItemTableItem(Items.HEART_OF_THE_SEA, ModItems.COPPER_IRON_ALLOY_INGOT, ModItems.CAST_IRON_INGOT, ModItems.TUNGSTEN_IRON_ALLOY_INGOT, ModItems.CAST_IRON_INGOT, ModItems.ALUMINIUM_TIN_ALLOY_INGOT, ModItems.CAST_IRON_INGOT, ModItems.COPPER_TIN_ALLOY_INGOT, pos, world);
        if (SEA_ALLOY_HORN) {
            recipes = "SEA_ALLOY_HORN";
            return true;
        } else {
            recipes = null;
            return false;
        }
    }

    public Item judgmentHammerLevel() {
        if (new ItemStack(getOutputItem()).isIn(ModItemTags.ALLOY_ALTAR_NEED_COPPER_IRON_ALLOY_HAMMER)) {
            return ModItems.COPPER_IRON_ALLOY_HAMMER;
        } else return null;
    }

    private boolean judgmentItemTableItem(Item north, Item eastNorth, Item east, Item eastSouth, Item south, Item westSouth, Item west, Item westNorth, BlockPos pos, WorldAccess world) {
        boolean East = false;
        boolean EastSouth = false;
        boolean South = false;
        boolean WestSouth = false;
        boolean West = false;
        boolean WestNorth = false;
        boolean North = false;
        boolean EastNorth = false;
        if (AlloyAltar.determineIfItHasBlock(pos.east(2).north(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.east(2).north(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (eastNorth != null) {
                if (Objects.equals(blockEntity.storedItems, eastNorth.toString())) {
                    EastNorth = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.north(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.north(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (north != null) {
                if (Objects.equals(blockEntity.storedItems, north.toString())) {
                    North = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.west(2).north(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.west(2).north(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (westNorth != null) {
                if (Objects.equals(blockEntity.storedItems, westNorth.toString())) {
                    WestNorth = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.west(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.west(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (west != null) {
                if (Objects.equals(blockEntity.storedItems, west.toString())) {
                    West = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.west(2).south(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.west(2).south(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (westSouth != null) {
                if (Objects.equals(blockEntity.storedItems, westSouth.toString())) {
                    WestSouth = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.south(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.south(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (south != null) {
                if (Objects.equals(blockEntity.storedItems, south.toString())) {
                    South = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.east(2).south(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.east(2).south(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (eastSouth != null) {
                if (Objects.equals(blockEntity.storedItems, eastSouth.toString())) {
                    EastSouth = true;
                }
            }
        }
        if (AlloyAltar.determineIfItHasBlock(pos.east(2), ModBlocks.ALLOY_ALTAR_ITEM_TABLE, world)) {
            BlockPos blockPos = pos.east(2);
            AlloyAltarItemTableBlockEntity blockEntity = (AlloyAltarItemTableBlockEntity) world.getBlockEntity(blockPos);
            if (east != null) {
                if (Objects.equals(blockEntity.storedItems, east.toString())) {
                    East = true;
                }
            }
        }
        return (East && EastSouth && South && WestSouth && West && WestNorth && North && EastNorth);
    }
}
