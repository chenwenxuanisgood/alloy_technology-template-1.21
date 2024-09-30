package com.shuangjiangguyi.datagen.LangProvider;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.sound.ModSoundEvent;
import com.shuangjiangguyi.tags.ModItemTags;
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
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_INGOT, "§6Copper-Iron Alloy Ingot");
        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_INGOT, "§6Copper-Tin Alloy Ingot");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, "§6Tungsten-Iron Alloy Ingot");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, "§6Aluminium-Tin Alloy Ingot");
        translationBuilder.add(ModItems.CAST_IRON_INGOT, "§6Cast Iron Ingot");

        translationBuilder.add(ModItems.TIN_INGOT, "§6Tin Ingot");
        translationBuilder.add(ModItems.TUNGSTEN_INGOT, "§6Tungsten Ingot");
        translationBuilder.add(ModItems.ALUMINIUM_INGOT, "§6Aluminium Ingot");
        translationBuilder.add(ModItems.CARBON_INGOT, "§6Carbon Ingot");
        translationBuilder.add(ModItems.SILICON_INGOT, "§6Silicon Ingot");

        translationBuilder.add(ModItems.RAW_TIN, "§7Raw Tin");

        translationBuilder.add(ModItems.EMPTY_ALLOY_TEMPLATE, "§6Empty Alloy Template");
        translationBuilder.add(ModItems.ALLOY_SWORD_TEMPLATE, "§6Alloy Sword Template");
        translationBuilder.add(ModItems.ALLOY_HAMMER_TEMPLATE, "§6Alloy Hammer Template");

        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD, "§5Tungsten-Iron Alloy Sword");

        translationBuilder.add(ModItems.COPPER_TIN_ALLOY_HAMMER, "§5Copper-Tin Alloy Hammer");
        translationBuilder.add(ModItems.COPPER_IRON_ALLOY_HAMMER, "§5Copper-Iron Alloy Hammer");
        translationBuilder.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER, "§5Aluminium-Tin Alloy Hammer");
        translationBuilder.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER, "§5Tungsten-Iron Alloy Hammer");

        translationBuilder.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK, "§eCopper-TIN Alloy Block");
        translationBuilder.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK, "§eCopper-Iron Alloy Block");
        translationBuilder.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK, "§eTungsten-Iron Alloy Block");
        translationBuilder.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK, "§eAluminium-Tin Alloy Block");

        translationBuilder.add(ModBlocks.TIN_BLOCK, "§eTin Block");
        translationBuilder.add(ModBlocks.TUNGSTEN_BLOCK, "§eTungsten Block");

        translationBuilder.add(ModBlocks.TIN_ORE, "§7Tin Ore");
        translationBuilder.add(ModBlocks.TUNGSTEN_ORE, "§7Tungsten Ore");

        translationBuilder.add(ModBlocks.IRON_ALLOY_SYNTHESIZER, "§eIron Alloy Synthesizer");
        translationBuilder.add(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER, "§eDiamond Alloy Synthesizer");
        translationBuilder.add(ModBlocks.ALLOY_FORGING_TABLE, "§eAlloy Tool Forging Table");
        translationBuilder.add(ModBlocks.ALLOY_DISMANTLING_TABLE, "§eAlloy Tool Dismantling Table");

        translationBuilder.add(ModGroups.ALLOY_TECHNOLOGY, "§6Alloy Technology");

        translationBuilder.add("connect.iron_alloy_synthesizer", "§eIron Alloy Synthesizer");
        translationBuilder.add("connect.diamond_alloy_synthesizer", "§eDiamond Alloy Synthesizer");
        translationBuilder.add("connect.alloy_forging_table", "§eAlloy Tool Forging Table");
        translationBuilder.add("connect.alloy_dismantling_table", "§eAlloy Tool Dismantling Table");

        translationBuilder.add(ModItemTags.ALLOY_INGOT, "§eAlloy Ingot");
        translationBuilder.add(ModItemTags.COPPER_TIN_ALLOY_ITEM, "§eCopper-Tin Alloy Item");
        translationBuilder.add(ModItemTags.COPPER_IRON_ALLOY_ITEM, "§eCopper-Iron Alloy Item");
        translationBuilder.add(ModItemTags.ALUMINIUM_TIN_ALLOY_ITEM, "§eAluminium-Tin Alloy Item");
        translationBuilder.add(ModItemTags.TUNGSTEN_IRON_ALLOY_ITEM, "§eTungsten-Iron Alloy Item");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER, "§eGreater Than And About Copper Tin Alloy Hammer");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER, "§eGreater Than And About Copper Iron Alloy Hammer");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER, "§eGreater Than And About Aluminium Tin Alloy Hammer");
        translationBuilder.add(ModItemTags.GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER, "§eGreater Than And About Tungsten Iron Alloy Hammer");
        translationBuilder.add(ModItemTags.INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY, "§eIngot That Can Be Synthesized Into Alloy");
        translationBuilder.add(ModItemTags.SWORD, "§eSword");
        translationBuilder.add(ModItemTags.HAMMER, "§eHammer");

        translationBuilder.add(ModItems.SEA_ALLOY_HORN, "§5Sea Alloy Horn");

        translationBuilder.add("sound.alloy_technology.sea_alloy_horn", "Sea Alloy Horn");

        translationBuilder.add("entity.minecraft.villager.alloy_master", "§6Alloy Merchant");
    }
}
