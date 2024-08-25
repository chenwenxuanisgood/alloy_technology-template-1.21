package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModZHCNLANProvider extends FabricLanguageProvider{
    public ModZHCNLANProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "铜铁合金锭");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "铜锡合金锭");
        translationBuilder.add(ModItems.TIN_INGOT, "锡锭");
        translationBuilder.add(ModItems.RAW_TIN, "粗锡");

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "铜铁合金块");
        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "铜锡合金块");
        translationBuilder.add(ModBlocks.TIN_ORE, "锡矿");
        translationBuilder.add(ModBlocks.ALLOY_SYNTHESIZER, "合金合成器");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "合金科技");

        translationBuilder.add("connect.alloy_synthesizer", "合金合成器");
    }
}
