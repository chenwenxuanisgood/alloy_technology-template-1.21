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

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "Copper Iron Alloy Block");
        translationBuilder.add(ModBlocks.ALLOY_SYNTHESIZER, "Alloy Synthesizer");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "Alloy Technology");

        translationBuilder.add("connect.alloy_synthesizer", "Connect Alloy Synthesizer");
    }
}
