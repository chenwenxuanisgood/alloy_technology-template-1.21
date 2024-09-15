package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ALLOY_SYNTHESIZER)
                .add(ModBlocks.ALLOY_FORGING_TABLE)
                .add(ModBlocks.COPPER_IRON_ALLOY_BLOCK)
                .add(ModBlocks.COPPER_TIN_ALLOY_BLOCK)
                .add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK)
                .add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK)
                .add(ModBlocks.TIN_ORE)
                .add(ModBlocks.TUNGSTEN_ORE)
                .add(ModBlocks.TIN_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ALLOY_SYNTHESIZER)
                .add(ModBlocks.ALLOY_FORGING_TABLE)
                .add(ModBlocks.COPPER_IRON_ALLOY_BLOCK)
                .add(ModBlocks.COPPER_TIN_ALLOY_BLOCK)
                .add(ModBlocks.TIN_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK)
                .add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK)
                .add(ModBlocks.TUNGSTEN_BLOCK)
                .add(ModBlocks.TUNGSTEN_ORE);
    }
}
