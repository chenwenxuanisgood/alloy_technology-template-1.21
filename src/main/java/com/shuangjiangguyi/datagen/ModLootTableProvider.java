package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.COPPER_IRON_ALLOY_BLOCK);
        addDrop(ModBlocks.COPPER_TIN_ALLOY_BLOCK);
        addDrop(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK);
        addDrop(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK);
        addDrop(ModBlocks.TIN_BLOCK);
        addDrop(ModBlocks.TUNGSTEN_BLOCK);

        addDrop(ModBlocks.IRON_ALLOY_SYNTHESIZER);
        addDrop(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER);
        addDrop(ModBlocks.ALLOY_FORGING_TABLE);
        addDrop(ModBlocks.ALLOY_DISMANTLING_TABLE);

        addDrop(ModBlocks.ALLOY_ALTAR);
        addDrop(ModBlocks.DAMAGED_ALLOY_ALTAR);
        addDrop(ModBlocks.ALLOY_ALTAR_ITEM_TABLE);

        addDrop(ModBlocks.TIN_ORE, oreDrops(ModBlocks.TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.TUNGSTEN_ORE);
    }
}
