package com.shuangjiangguyi.datagen.LangProvider;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModKOKRLANProvider extends FabricLanguageProvider{
    public ModKOKRLANProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "ko_kr", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "§6구리-철 합금 잉곳");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "§6구리-주석 합금 잉곳");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, "§6페로-텅스텐 잉곳");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, "§6알루미늄-주석 잉곳");

        translationBuilder.add(ModItems.TIN_INGOT, "§6주석 잉곳");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6텅스텐 잉곳");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6알루미늄 잉곳");

        translationBuilder.add(ModItems.RAW_TIN, "§7조잡한 주석");

        translationBuilder.add(ModItems.EMPTY_ALLOY_TEMPLATE, "§6빈 합금 템플릿");
        translationBuilder.add(ModItems.ALLOY_SWORD_TEMPLATE, "§6합금 검 템플릿");
        translationBuilder.add(ModItems.ALLOY_HAMMER_TEMPLATE, "§6합금 검 템플릿");

        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5텅스텐 철 합금 검");

        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_HAMMER, "§5구리-주석 합금 해머");
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_HAMMER, "§5구리-철 합금 해머");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, "§5알루미늄-주석 합금 해머");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, "§5페로 - 텅스텐 합금 해머");

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§e구리-철 합금 블록");
        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§e구리-주석 합금 블록");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§e텅스텐 합금철 블록");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§e알루미늄-주석 합금 블록");

        translationBuilder.add(ModBlocks.TIN_BLOCK, "§e주석 블록");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§e텅스텐 블록");

        translationBuilder.add(ModBlocks.TIN_ORE, "§7주석 광석");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7텅스텐 광석");

        translationBuilder.add(ModBlocks.ALLOY_SYNTHESIZER, "§e합금 합성기");
        translationBuilder.add(ModBlocks.ALLOY_FORGING_TABLE, "§e합금 단조 테이블");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "§6합금 기술");

        translationBuilder.add("connect.alloy_synthesizer", "§e합금 합성기");
        translationBuilder.add("connect.alloy_forging_table", "§e합금 단조 테이블");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "§6합금 상인");
    }
}
