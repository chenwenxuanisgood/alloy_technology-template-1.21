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
import net.minecraft.registry.tag.ItemTags;

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
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModBlocks.IRON_ALLOY_SYNTHESIZER).pattern("bib").pattern("iii").pattern("iii")
                .input('i', Items.IRON_INGOT)
                .input('b', Items.BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.BUCKET),
                        FabricRecipeProvider.conditionsFromItem(Items.BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModBlocks.DIAMOND_ALLOY_SYNTHESIZER).pattern("dtd").pattern("aic").pattern("dnd")
                .input('t', ModItems.TUNGSTEN_IRON_ALLOY_INGOT)
                .input('a', ModItems.ALUMINIUM_TIN_ALLOY_INGOT)
                .input('c', ModItems.COPPER_IRON_ALLOY_INGOT)
                .input('n', ModItems.COPPER_TIN_ALLOY_INGOT)
                .input('d', Items.DIAMOND)
                .input('i', ModBlocks.IRON_ALLOY_SYNTHESIZER)
                .criterion(FabricRecipeProvider.hasItem(Items.DIAMOND),
                        FabricRecipeProvider.conditionsFromItem(Items.DIAMOND))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.IRON_ALLOY_SYNTHESIZER),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.IRON_ALLOY_SYNTHESIZER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPTY_ALLOY_TEMPLATE).pattern("tt").pattern("tt")
                .input('t', ModItems.TUNGSTEN_IRON_ALLOY_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TUNGSTEN_IRON_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.TUNGSTEN_IRON_ALLOY_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ALLOY_HAMMER_TEMPLATE).pattern("ppp").pattern("ptp").pattern("et ")
                .input('p', ItemTags.PLANKS)
                .input('t', Items.STICK)
                .input('e', ModItems.EMPTY_ALLOY_TEMPLATE)
                .criterion(FabricRecipeProvider.hasItem(Items.STONE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.TUNGSTEN_IRON_ALLOY_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(ModItems.TUNGSTEN_IRON_ALLOY_INGOT))
                .criterion(FabricRecipeProvider.hasItem(ModItems.ALLOY_HAMMER_TEMPLATE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.ALLOY_HAMMER_TEMPLATE))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ALLOY_SWORD_TEMPLATE)
                .input(ModItems.EMPTY_ALLOY_TEMPLATE, 1)
                .input(Items.WOODEN_SWORD, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.WOODEN_SWORD),
                        FabricRecipeProvider.conditionsFromItem(Items.WOODEN_SWORD))
                .criterion(FabricRecipeProvider.hasItem(ModItems.EMPTY_ALLOY_TEMPLATE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.EMPTY_ALLOY_TEMPLATE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_TIN_ALLOY_HAMMER).pattern("ccc").pattern("csc").pattern(" s ")
                .input('c', ModItems.COPPER_TIN_ALLOY_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_TIN_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_TIN_ALLOY_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModBlocks.ALLOY_FORGING_TABLE).pattern("iii").pattern(" b ")
                .input('i', ModItems.COPPER_IRON_ALLOY_INGOT)
                .input('b', ModBlocks.COPPER_IRON_ALLOY_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.COPPER_IRON_ALLOY_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.COPPER_IRON_ALLOY_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_TIN_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_TIN_ALLOY_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModBlocks.ALLOY_DISMANTLING_TABLE).pattern("iii").pattern(" b ")
                .input('i', ModItems.ALUMINIUM_TIN_ALLOY_INGOT)
                .input('b', ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(ModItems.ALUMINIUM_TIN_ALLOY_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.ALUMINIUM_TIN_ALLOY_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModBlocks.ALLOY_ALTAR).pattern("c c").pattern("ccc")
                .input('c', ModItems.CAST_IRON_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.CAST_IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.CAST_IRON_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModBlocks.ALLOY_ALTAR_ITEM_TABLE).pattern("ccc").pattern(" c ").pattern("ccc")
                .input('c', ModItems.CAST_IRON_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.CAST_IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.CAST_IRON_INGOT))
                .offerTo(exporter);
        offerSmelting(exporter, TIN_INGOT, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.7f, 600, "tin_ingot");
        offerBlasting(exporter, TIN_INGOT, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.7f, 300, "tin_ingot");
    }
}
