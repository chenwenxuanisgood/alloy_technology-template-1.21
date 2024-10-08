package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_IRON_ALLOY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_TIN_ALLOY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TUNGSTEN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.IRON_ALLOY_SYNTHESIZER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ALLOY_FORGING_TABLE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ALLOY_DISMANTLING_TABLE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TUNGSTEN_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COPPER_IRON_ALLOY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_TIN_ALLOY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALUMINIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARBON_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILICON_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_ALLOY_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALLOY_SWORD_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALLOY_HAMMER_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_TIN_ALLOY_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_IRON_ALLOY_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAST_IRON_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_ALLOY_HORN, Models.GENERATED);
    }
}
