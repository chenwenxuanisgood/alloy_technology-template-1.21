package com.shuangjiangguyi.tags;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> ALLOY_INGOT = of("alloy_ingot");
    public static final TagKey<Item> INGOT_THAT_CAN_BE_SYNTHESIZED_INTO_ALLOY = of("ingot_that_can_be_synthesized_into_alloy");
    public static final TagKey<Item> GREATER_THAN_AND_ABOUT_COPPER_TIN_ALLOY_HAMMER = of("greater_than_and_about_copper_tin_alloy_hammer");
    public static final TagKey<Item> GREATER_THAN_AND_ABOUT_COPPER_IRON_ALLOY_HAMMER = of("greater_than_and_about_copper_iron_alloy_hammer");
    public static final TagKey<Item> GREATER_THAN_AND_ABOUT_ALUMINIUM_TIN_ALLOY_HAMMER = of("greater_than_and_about_aluminium_tin_alloy_hammer");
    public static final TagKey<Item> GREATER_THAN_AND_ABOUT_TUNGSTEN_IRON_ALLOY_HAMMER = of("greater_than_and_about_tungsten_iron_alloy_hammer");
    public static final TagKey<Item> COPPER_TIN_ALLOY_ITEM = of("copper_tin_alloy_item");
    public static final TagKey<Item> COPPER_IRON_ALLOY_ITEM = of("copper_iron_alloy_item");
    public static final TagKey<Item> ALUMINIUM_TIN_ALLOY_ITEM = of("aluminium_tin_alloy_item");
    public static final TagKey<Item> TUNGSTEN_IRON_ALLOY_ITEM = of("tungsten_iron_alloy_item");
    public static final TagKey<Item> SWORD = of("sword");
    public static final TagKey<Item> HAMMER = of("hammer");
    public static final TagKey<Item> ALLOY_ALTAR_NEED_COPPER_TIN_ALLOY_HAMMER = of("alloy_altar_need_copper_tin_alloy_hammer");
    public static final TagKey<Item> ALLOY_ALTAR_NEED_COPPER_IRON_ALLOY_HAMMER = of("alloy_altar_need_copper_iron_alloy_hammer");
    public static final TagKey<Item> ALLOY_ALTAR_NEED_ALUMINIUM_TIN_ALLOY_HAMMER = of("alloy_altar_need_aluminium_tin_alloy_hammer");
    public static final TagKey<Item> ALLOY_ALTAR_NEED_TUNGSTEN_IRON_ALLOY_HAMMER = of("alloy_altar_need_tungsten_iron_alloy_hammer");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(AlloyTechnology.MOD_ID, id));
    }
    public static void registerModItemTags() {
        AlloyTechnology.LOGGER.info("合金科技物品标签注册");
    }
}
