package com.shuangjiangguyi.datagen.LangProvider;

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
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "§6Copper Iron Alloy Ingot");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "§6Copper Tin Alloy Ingot");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, "§6Tungsten Iron Alloy Ingot");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, "§6Aluminium Tin Alloy Ingot");

        translationBuilder.add(ModItems.TIN_INGOT, "§6Tin Ingot");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6Tungsten Ingot");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6Aluminium Ingot");

        translationBuilder.add(ModItems.RAW_TIN, "§7Raw Tin");

        translationBuilder.add(ModItems.EMPTY_ALLOY_TEMPLATE, "§6Empty Alloy Template");
        translationBuilder.add(ModItems.ALLOY_SWORD_TEMPLATE, "§6Alloy Sword Template");
        translationBuilder.add(ModItems.ALLOY_HAMMER_TEMPLATE, "§6Alloy Hammer Template");

        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5Tungsten Iron Alloy Sword");

        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_HAMMER, "§5Copper Tin Alloy Hammer");
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_HAMMER, "§5Copper Iron Alloy Hammer");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, "§5Aluminium Tin Alloy Hammer");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, "§5Tungsten Iron Alloy Hammer");

        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§eCopper TIN Alloy Block");
        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§eCopper Iron Alloy Block");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§eTungsten Iron Alloy Block");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§eAluminium Tin Alloy Block");

        translationBuilder.add(ModBlocks.TIN_BLOCK, "§eTin Block");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§eTungsten Block");

        translationBuilder.add(ModBlocks.TIN_ORE, "§7Tin Ore");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7Tungsten Ore");

        translationBuilder.add(ModBlocks.ALLOY_SYNTHESIZER, "§eAlloy Synthesizer");
        translationBuilder.add(ModBlocks.ALLOY_FORGING_TABLE, "§eAlloy Forging Table");
        translationBuilder.add(ModBlocks.ALLOY_DISMANTLING_TABLE, "§eAlloy Dismantling Table");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "§6Alloy Technology");

        translationBuilder.add("connect.alloy_synthesizer", "§eAlloy Synthesizer");
        translationBuilder.add("connect.alloy_forging_table", "§eAlloy Forging Table");
        translationBuilder.add("connect.alloy_dismantling_table", "§eAlloy Dismantling Table");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "§6Alloy Merchant");
    }
}
