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
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "§6铜铁合金锭");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "§6铜锡合金锭");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, "§6钨铁合金锭");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, "§6铝锡合金锭");
        translationBuilder.add(ModItems.TIN_INGOT, "§6锡锭");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6钨锭");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6铝锭");
        translationBuilder.add(ModItems.RAW_TIN, "§7粗锡");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5钨铁合金剑");

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§e铜铁合金块");
        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§e铜锡合金块");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§e钨铁合金块");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§e铝锡合金块");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "§e锡块");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§e钨块");
        translationBuilder.add(ModBlocks.TIN_ORE, "§7锡矿");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7钨矿");
        translationBuilder.add(ModBlocks.ALLOY_SYNTHESIZER, "§e合金合成器");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "§6合金科技");

        translationBuilder.add("connect.alloy_synthesizer", "合金合成器");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "合金商人");
    }
}
