package com.shuangjiangguyi.datagen.LangProvider;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModJAJPLANProvider extends FabricLanguageProvider{
    public ModJAJPLANProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "ja_jp", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "§6銅-鉄合金インゴット");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "§6銅-錫合金インゴット");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, "§6フェロタングステンインゴット");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, "§6アルミ錫インゴット");
        translationBuilder.add(ModItems.CAST_IRON_INGOT, "§6鋳鉄インゴット");

        translationBuilder.add(ModItems.TIN_INGOT, "§6錫インゴット");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6タングステンインゴット");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6アルミインゴット");
        translationBuilder.add(ModItems.CARBON_INGOT, "§6炭インゴット");
        translationBuilder.add(ModItems.SILICON_INGOT, "§6シリコンインゴット");

        translationBuilder.add(ModItems.RAW_TIN, "§7粗錫");

        translationBuilder.add(ModItems.EMPTY_ALLOY_TEMPLATE, "§6空の合金テンプレート");
        translationBuilder.add(ModItems.ALLOY_SWORD_TEMPLATE, "§6合金剑模板");
        translationBuilder.add(ModItems.ALLOY_HAMMER_TEMPLATE, "§6合金剣テンプレート");

        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5タングステン鉄合金製剣");

        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_HAMMER, "§5銅-錫合金ハンマー");
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_HAMMER, "§5銅鉄合金ハンマー");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, "§5アルミ-スズ合金ハンマー");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, "§5フェロタングステン合金ハンマー");

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§e銅-鉄合金ブロック");
        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§e銅-錫合金ブロック");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§eタングステンフェロアロイブロック");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§eアルミ-スズ合金ブロック");

        translationBuilder.add(ModBlocks.TIN_BLOCK, "§eブリキブロック");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§eタングステンブロック");

        translationBuilder.add(ModBlocks.TIN_ORE, "§7錫鉱石");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7タングステン鉱石");

        translationBuilder.add(ModBlocks.IRON_ALLOY_SYNTHESIZER, "§e鉄合金シンセサイザー");
        translationBuilder.add(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER, "§eダイヤモンド合金シンセサイザー");
        translationBuilder.add(ModBlocks.ALLOY_FORGING_TABLE, "§e合金工具鍛造テーブル");
        translationBuilder.add(ModBlocks.ALLOY_DISMANTLING_TABLE, "§e合金工具分解テーブル");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "§6合金技術");

        translationBuilder.add("connect.iron_alloy_synthesizer", "§e鉄合金シンセサイザー");
        translationBuilder.add("connect.diamond_alloy_synthesizer", "§eダイヤモンド合金シンセサイザー");
        translationBuilder.add("connect.alloy_forging_table", "§e合金工具鍛造テーブル");
        translationBuilder.add("connect.alloy_dismantling_table", "§e合金工具分解テーブル");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "§6合金商人");
    }
}
