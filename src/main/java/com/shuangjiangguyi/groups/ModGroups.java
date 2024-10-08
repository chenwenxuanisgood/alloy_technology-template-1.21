package com.shuangjiangguyi.groups;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModGroups {
    public static final RegistryKey<ItemGroup> ALLOY_TECHNOLOGY = register("alloy_technology");
    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(AlloyTechnology.MOD_ID, id));
    }
    public static void registerModGroups() {
        Registry.register(Registries.ITEM_GROUP,ALLOY_TECHNOLOGY,
                ItemGroup.create(ItemGroup.Row.TOP,7)
                                .displayName(Text.translatable("alloy_technology"))
                                        .icon(() -> new ItemStack(ModItems.COPPER_IRON_ALLOY_INGOT))
                        .entries((displayContext, entries) -> {
                            entries.add(ModItems.COPPER_IRON_ALLOY_INGOT);
                            entries.add(ModItems.COPPER_TIN_ALLOY_INGOT);
                            entries.add(ModItems.TUNGSTEN_IRON_ALLOY_INGOT);
                            entries.add(ModItems.ALUMINIUM_TIN_ALLOY_INGOT);
                            entries.add(ModItems.CAST_IRON_INGOT);
                            entries.add(ModItems.TIN_INGOT);
                            entries.add(ModItems.TUNGSTEN_INGOT);
                            entries.add(ModItems.ALUMINIUM_INGOT);
                            entries.add(ModItems.CARBON_INGOT);
                            entries.add(ModItems.SILICON_INGOT);
                            entries.add(ModItems.RAW_TIN);
                            entries.add(ModBlocks.COPPER_IRON_ALLOY_BLOCK);
                            entries.add(ModBlocks.COPPER_TIN_ALLOY_BLOCK);
                            entries.add(ModBlocks.TUNGSTEN_IRON_ALLOY_BLOCK);
                            entries.add(ModBlocks.ALUMINIUM_TIN_ALLOY_BLOCK);
                            entries.add(ModBlocks.TIN_BLOCK);
                            entries.add(ModBlocks.TUNGSTEN_BLOCK);
                            entries.add(ModBlocks.TIN_ORE);
                            entries.add(ModBlocks.TUNGSTEN_ORE);
                            entries.add(ModBlocks.IRON_ALLOY_SYNTHESIZER);
                            entries.add(ModBlocks.DIAMOND_ALLOY_SYNTHESIZER);
                            entries.add(ModBlocks.ALLOY_FORGING_TABLE);
                            entries.add(ModBlocks.ALLOY_DISMANTLING_TABLE);
                            entries.add(ModItems.EMPTY_ALLOY_TEMPLATE);
                            entries.add(ModItems.ALLOY_SWORD_TEMPLATE);
                            entries.add(ModItems.ALLOY_HAMMER_TEMPLATE);
                            entries.add(ModItems.TUNGSTEN_IRON_ALLOY_SWORD);
                            entries.add(ModItems.COPPER_TIN_ALLOY_HAMMER);
                            entries.add(ModItems.COPPER_IRON_ALLOY_HAMMER);
                            entries.add(ModItems.ALUMINIUM_TIN_ALLOY_HAMMER);
                            entries.add(ModItems.TUNGSTEN_IRON_ALLOY_HAMMER);
                            entries.add(ModItems.SEA_ALLOY_HORN);
                        }).build());
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Item Groups");
    }
}
