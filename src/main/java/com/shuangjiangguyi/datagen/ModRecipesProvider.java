package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> TIN_INGOT = List.of(ModItems.RAW_TIN);
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.COPPER_IRON_ALLOY_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_IRON_ALLOY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.COPPER_TIN_ALLOY_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_TIN_ALLOY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.TUNGSTEN_IRON_ALLOY_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.TIN_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ALUMINIUM_TIN_ALLOY_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.TUNGSTEN_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ALLOY_SYNTHESIZER).pattern("bib").pattern("iii").pattern("iii")
                .input('i', Items.IRON_INGOT)
                .input('b', Items.BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.BUCKET),
                        FabricRecipeProvider.conditionsFromItem(Items.BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPTY_ALLOY_TEMPLATE).pattern("tt").pattern("tt")
                .input('t', ModItems.TUNGSTEN_IRON_ALLOY_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TUNGSTEN_IRON_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.TUNGSTEN_IRON_ALLOY_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_TIN_ALLOY_HAMMER).pattern("ccc").pattern("csc").pattern(" s ")
                .input('c', ModItems.COPPER_TIN_ALLOY_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_TIN_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_TIN_ALLOY_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModBlocks.ALLOY_FORGING_TABLE).pattern("iii").pattern(" b ")
                .input('i', ModItems.COPPER_IRON_ALLOY_INGOT)
                .input('b', ModBlocks.COPPER_IRON_ALLOY_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_TIN_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_TIN_ALLOY_INGOT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ALLOY_SWORD_TEMPLATE)
                .input(ModItems.EMPTY_ALLOY_TEMPLATE, 1)
                .input(Items.WOODEN_SWORD, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.WOODEN_SWORD),
                        FabricRecipeProvider.conditionsFromItem(Items.WOODEN_SWORD))
                .criterion(FabricRecipeProvider.hasItem(ModItems.EMPTY_ALLOY_TEMPLATE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.EMPTY_ALLOY_TEMPLATE))
                .offerTo(exporter);;
        offerSmelting(exporter, TIN_INGOT, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.7f, 600, "tin_ingot");
        offerBlasting(exporter, TIN_INGOT, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.7f, 300, "tin_ingot");
    }
}
