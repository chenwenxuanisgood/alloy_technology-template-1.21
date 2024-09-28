package com.shuangjiangguyi.datagen;

import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModItemTags.ALLOY_INGOT)
                .add(ModItems.COPPER_TIN_ALLOY_INGOT)
                .add(ModItems.COPPER_IRON_ALLOY_INGOT)
                .add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)
                .add(ModItems.CAST_IRON_INGOT);
        getOrCreateTagBuilder(ModItemTags.INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY)
                .add(Items.COPPER_INGOT)
                .add(Items.IRON_INGOT)
                .add(ModItems.TIN_INGOT)
                .add(ModItems.ALUMINIUM_INGOT)
                .add(ModItems.TUNGSTEN_INGOT)
                .add(ModItems.CARBON_INGOT)
                .add(ModItems.SILICON_INGOT);
        getOrCreateTagBuilder(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER)
                .add(ModItems.COPPER_TIN_ALLOY_HAMMER)
                .add(ModItems.COPPER_IRON_ALLOY_HAMMER)
                .add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER)
                .add(ModItems.COPPER_IRON_ALLOY_HAMMER)
                .add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER)
                .add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.COPPER_TIN_ALLOY_ITEM)
                .add(ModItems.COPPER_TIN_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.COPPER_IRON_ALLOY_ITEM)
                .add(ModItems.COPPER_IRON_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.ALUMINIUM_TIN_ALLOY_ITEM)
                .add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER);
        getOrCreateTagBuilder(ModItemTags.TUNGSTEN_IRON_ALLOY_ITEM)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD);
        getOrCreateTagBuilder(ModItemTags.SWORD)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD);
        getOrCreateTagBuilder(ModItemTags.HAMMER)
                .add(ModItems.COPPER_TIN_ALLOY_HAMMER)
                .add(ModItems.COPPER_IRON_ALLOY_HAMMER)
                .add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER)
                .add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER);
    }
}
