package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSLANProvider extends FabricLanguageProvider{
    public ModENUSLANProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "Copper Iron Alloy Ingot");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "Copper Tin Alloy Ingot");
        translationBuilder.add(ModItems.TIN_INGOT, "Tin Ingot");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "Tungsten Ingot");
        translationBuilder.add(ModItems.RAW_TIN, "Raw Tin");

        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "Copper TIN Alloy Block");
        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "Copper Iron Alloy Block");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "Tin Block");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "Tungsten Block");
        translationBuilder.add(ModBlocks.TIN_ORE, "Tin Ore");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "Tungsten Ore");
        translationBuilder.add(ModBlocks.ALLOY_SYNTHESIZER, "Alloy Synthesizer");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "Alloy Technology");

        translationBuilder.add("connect.alloy_synthesizer", "Connect Alloy Synthesizer");
    }
}
