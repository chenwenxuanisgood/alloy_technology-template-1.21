package com.shuangjiangguyi.datagen.LangProvider;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModZHHKLANProvider extends FabricLanguageProvider{
    public ModZHHKLANProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_hk", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "§6銅鐵合金錠");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "§6銅錫合金錠");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, "§6鎢鐵合金錠");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, "§6鋁錫合金錠");
        translationBuilder.add(ModItems.CAST_IRON_INGOT, "§6鑄鐵錠");

        translationBuilder.add(ModItems.TIN_INGOT, "§6錫錠");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6鎢錠");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6鋁錠");
        translationBuilder.add(ModItems.CARBON_INGOT, "§6碳錠");
        translationBuilder.add(ModItems.SILICON_INGOT, "§6矽錠");

        translationBuilder.add(ModItems.RAW_TIN, "§7粗錫");

        translationBuilder.add(ModItems.EMPTY_ALLOY_TEMPLATE, "§6空合金範本");
        translationBuilder.add(ModItems.ALLOY_SWORD_TEMPLATE, "§6合金劍範本");
        translationBuilder.add(ModItems.ALLOY_HAMMER_TEMPLATE, "§6合金錘範本");

        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5鎢鐵合金劍");

        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_HAMMER, "§5銅錫合金錘");
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_HAMMER, "§5銅鐵合金錘");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, "§5鋁錫合金錘");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, "§5鎢鐵合金錘");

        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§e铜铁合金塊");
        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§e銅錫合金塊");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§e鎢鐵合金塊");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§e鋁錫合金塊");

        translationBuilder.add(ModBlocks.TIN_BLOCK, "§e錫塊");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§e鎢塊");

        translationBuilder.add(ModBlocks.TIN_ORE, "§7錫礦");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7鎢礦");

        translationBuilder.add(ModBlocks.IRON_ALLOY_SYNTHESIZER, "§e鐵合金合成器");
        translationBuilder.add(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER, "鑽石合金合成器");
        translationBuilder.add(ModBlocks.ALLOY_FORGING_TABLE, "§e合金鍛造臺");
        translationBuilder.add(ModBlocks.ALLOY_DISMANTLING_TABLE, "§e合金工具拆解臺");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "§6合金科技");

        translationBuilder.add("connect.iron_alloy_synthesizer", "§e合金合成器");
        translationBuilder.add("connect.diamond_alloy_synthesizer", "§e鑽石合金合成器");
        translationBuilder.add("connect.alloy_forging_table", "§e合金鍛造臺");
        translationBuilder.add("connect.alloy_dismantling_table", "§e合金工具拆解臺");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "§6合金商人");
    }
}
