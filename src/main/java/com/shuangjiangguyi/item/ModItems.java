package com.shuangjiangguyi.item;

import com.shuangjiangguyi.AlloyTechnology;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item ordinaryItemRegister(String id) {
        return registerItems(id, new Item(new Item.Settings()));
    }

    public static final Item COPPER_IRON_ALLOY_INGOT = ordinaryItemRegister("copper_iron_alloy_ingot");
    public static final Item COPPER_TIN_ALLOY_INGOT = ordinaryItemRegister("copper_tin_alloy_ingot");
    public static final Item TUNGSTEN_IRON_ALLOY_INGOT = ordinaryItemRegister("tungsten_iron_alloy_ingot");
    public static final Item ALUMINIUM_TIN_ALLOY_INGOT = ordinaryItemRegister("aluminium_tin_alloy_ingot");

    public static final Item TIN_INGOT = ordinaryItemRegister("tin_ingot");
    public static final Item TUNGSTEN_INGOT = ordinaryItemRegister("tungsten_ingot");
    public static final Item ALUMINIUM_INGOT = ordinaryItemRegister("aluminium_ingot");

    public static final Item RAW_TIN = ordinaryItemRegister("raw_tin");

    public static final Item EMPTY_ALLOY_TEMPLATE = ordinaryItemRegister("empty_alloy_template");
    public static final Item ALLOY_SWORD_TEMPLATE = ordinaryItemRegister("alloy_sword_template");
    public static final Item ALLOY_HAMMER_TEMPLATE = ordinaryItemRegister("alloy_hammer_template");

    public static final Item TUNGSTEN_IRON_ALLOY_SWORD = registerItems("tungsten_iron_alloy_sword", new SwordItem(ModToolMaterials.TUNGSTEN_IRON_ALLOY_INGOT, new Item.Settings().
            attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN_IRON_ALLOY_INGOT, 15, -2.9f))));

    public static final Item COPPER_TIN_ALLOY_HAMMER = registerItems("copper_tin_alloy_hammer", new PickaxeItem(ModToolMaterials.COPPER_TIN_ALLOY_INGOT, new Item.Settings().
            attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.COPPER_TIN_ALLOY_INGOT, 2.5f, -2f))));
    public static final Item COPPER_IRON_ALLOY_HAMMER = registerItems("copper_iron_alloy_hammer", new PickaxeItem(ModToolMaterials.COPPER_TIN_ALLOY_INGOT, new Item.Settings().
            attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.COPPER_IRON_ALLOY_INGOT, 2.5f, -2f))));
    public static final Item ALUMINIUM_TIN_ALLOY_HAMMER = registerItems("aluminium_tin_alloy_hammer", new PickaxeItem(ModToolMaterials.COPPER_TIN_ALLOY_INGOT, new Item.Settings().
            attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ALUMINIUM_TIN_ALLOY_INGOT, 2.5f, -2f))));
    public static final Item TUNGSTEN_IRON_ALLOY_HAMMER = registerItems("tungsten_iron_alloy_hammer", new PickaxeItem(ModToolMaterials.COPPER_TIN_ALLOY_INGOT, new Item.Settings().
            attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN_IRON_ALLOY_INGOT, 2.5f, -2f))));
    private static Item registerItems(String id, Item item){
        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(AlloyTechnology.MOD_ID, id)),item);
    }
    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(COPPER_IRON_ALLOY_INGOT);
        fabricItemGroupEntries.add(COPPER_TIN_ALLOY_INGOT);
        fabricItemGroupEntries.add(TUNGSTEN_IRON_ALLOY_INGOT);
        fabricItemGroupEntries.add(ALUMINIUM_TIN_ALLOY_INGOT);
        fabricItemGroupEntries.add(TIN_INGOT);
        fabricItemGroupEntries.add(TUNGSTEN_INGOT);
        fabricItemGroupEntries.add(ALUMINIUM_INGOT);
        fabricItemGroupEntries.add(EMPTY_ALLOY_TEMPLATE);
        fabricItemGroupEntries.add(ALLOY_SWORD_TEMPLATE);
        fabricItemGroupEntries.add(ALLOY_HAMMER_TEMPLATE);
        fabricItemGroupEntries.add(RAW_TIN);
    }
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG);
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Items");
    }
}