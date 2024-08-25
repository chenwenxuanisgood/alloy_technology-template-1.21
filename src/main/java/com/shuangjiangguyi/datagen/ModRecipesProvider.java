package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.COPPER_IRON_ALLOY_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_IRON_ALLOY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.COPPER_TIN_ALLOY_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_TIN_ALLOY_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ALLOY_SYNTHESIZER).pattern("bib").pattern("iii").pattern("iii")
                .input('i', Items.IRON_INGOT)
                .input('b', Items.BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.BUCKET),
                        FabricRecipeProvider.conditionsFromItem(Items.BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
    }
}
