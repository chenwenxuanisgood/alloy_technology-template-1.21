package com.shuangjiangguyi.datagen.LangProvider;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModItemGroups;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.tags.ModItemTags;
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
        translationBuilder.add(ModItems.CAST_IRON_INGOT, "§6铸铁锭");

        translationBuilder.add(ModItems.TIN_INGOT, "§6锡锭");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6钨锭");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6铝锭");
        translationBuilder.add(ModItems.CARBON_INGOT, "§6碳锭");
        translationBuilder.add(ModItems.SILICON_INGOT, "§6硅锭");

        translationBuilder.add(ModItems.RAW_TIN, "§7粗锡");

        translationBuilder.add(ModItems.EMPTY_ALLOY_TEMPLATE, "§6空合金模板");
        translationBuilder.add(ModItems.ALLOY_SWORD_TEMPLATE, "§6合金剑模板");
        translationBuilder.add(ModItems.ALLOY_HAMMER_TEMPLATE, "§6合金锤模板");

        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5钨铁合金剑");

        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_HAMMER, "§5铜锡合金锤");
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_HAMMER, "§5铜铁合金锤");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, "§5铝锡合金锤");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, "§5钨铁合金锤");

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§e铜铁合金块");
        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§e铜锡合金块");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§e钨铁合金块");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§e铝锡合金块");

        translationBuilder.add(ModBlocks.TIN_BLOCK, "§e锡块");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§e钨块");

        translationBuilder.add(ModBlocks.TIN_ORE, "§7锡矿");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7钨矿");

        translationBuilder.add(ModBlocks.IRON_ALLOY_SYNTHESIZER, "§e铁合金合成器");
        translationBuilder.add(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER, "钻石合金合成器");
        translationBuilder.add(ModBlocks.ALLOY_FORGING_TABLE, "§e合金物品锻造台");
        translationBuilder.add(ModBlocks.ALLOY_DISMANTLING_TABLE, "§e合金物品拆解台");

        translationBuilder.add(ModBlocks.ALLOY_ALTAR, "§6合金祭坛");
        translationBuilder.add(ModBlocks.DAMAGED_ALLOY_ALTAR, "§6损坏的合金祭坛");
        translationBuilder.add(ModBlocks.ALLOY_ALTAR_ITEM_TABLE, "§6合金祭坛物品台");

        translationBuilder.add(ModItemGroups.ALLOY_TECHNOLOGY, "§6合金科技");

        translationBuilder.add("connect.iron_alloy_synthesizer", "§e铁合金合成器");
        translationBuilder.add("connect.diamond_alloy_synthesizer", "§e钻石合金合成器");
        translationBuilder.add("connect.alloy_forging_table", "§e合金锻造台");
        translationBuilder.add("connect.alloy_dismantling_table", "§e合金工具拆解台");

        translationBuilder.add("text.detector.need_copper_iron_alloy_hammer", "需要铜铁合金锤");
        translationBuilder.add("text.detector.not_player", "请不要对着非玩家的实体右键");
        translationBuilder.add("text.detector.alloy_altar_proficiency", "合金祭坛熟练度是");

        translationBuilder.add(ModItemTags.ALLOY_INGOT, "§e合金锭");
        translationBuilder.add(ModItemTags.COPPER_TIN_ALLOY_ITEM, "§e铜锡合金物品");
        translationBuilder.add(ModItemTags.COPPER_IRON_ALLOY_ITEM, "§e铜铁合金物品");
        translationBuilder.add(ModItemTags.ALUMINIUM_TIN_ALLOY_ITEM, "§e铝锡合金物品");
        translationBuilder.add(ModItemTags.TUNGSTEN_IRON_ALLOY_ITEM, "§e钨铁合金物品");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER, "§e大于铜锡合金锤");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER, "§e大于铜铁合金锤");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER, "§e大于铝锡合金锤");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER, "§e大于钨铁合金锤");
        translationBuilder.add(ModItemTags.INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY, "§e可以合成合金的锭");
        translationBuilder.add(ModItemTags.SWORD, "§e剑");
        translationBuilder.add(ModItemTags.HAMMER, "§e锤");
        translationBuilder.add(ModItemTags.ALLOY_ALTAR_NEED_COPPER_TIN_ALLOY_HAMMER, "§e需要铜锡合金锤的合金祭坛");
        translationBuilder.add(ModItemTags.ALLOY_ALTAR_NEED_COPPER_IRON_ALLOY_HAMMER, "§e需要铜铁合金锤的合金祭坛");
        translationBuilder.add(ModItemTags.ALLOY_ALTAR_NEED_ALUMINIUM_TIN_ALLOY_HAMMER, "§e需要铝锡合金锤的合金祭坛");
        translationBuilder.add(ModItemTags.ALLOY_ALTAR_NEED_TUNGSTEN_IRON_ALLOY_HAMMER, "§e需要钨铁合金锤的合金祭坛");

        translationBuilder.add(ModItems.SEA_ALLOY_HORN, "§5海洋合金号角");
        translationBuilder.add(ModItems.DETECTOR, "§6检测器");

        translationBuilder.add("sound.alloy_technology.sea_alloy_horn", "海洋合金号角");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "§6合金商人");

        translationBuilder.add("tutorial_book_landing_text", "这是合金科技教程书");
        translationBuilder.add("tutorial_book_name", "合金科技教程书");
    }
}