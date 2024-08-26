package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.TIN_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ALLOY_SYNTHESIZER).pattern("bib").pattern("iii").pattern("iii")
                .input('i', Items.IRON_INGOT)
                .input('b', Items.BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.BUCKET),
                        FabricRecipeProvider.conditionsFromItem(Items.BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
        offerSmelting(exporter, TIN_INGOT, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.7f, 600, "tin_ingot");
        offerBlasting(exporter, TIN_INGOT, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.7f, 300, "tin_ingot");
    }
}
