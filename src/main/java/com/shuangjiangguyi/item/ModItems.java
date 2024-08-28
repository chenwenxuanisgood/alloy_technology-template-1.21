package com.shuangjiangguyi.item;

import com.shuangjiangguyi.AlloyTechnology;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item COPPER_IRON_ALLOY_INGOT = registerItems("copper_iron_alloy_ingot", new Item(new Item.Settings()));
    public static final Item COPPER_TIN_ALLOY_INGOT = registerItems("copper_tin_alloy_ingot", new Item(new Item.Settings()));
    public static final Item TUNGSTEN_IRON_ALLOY_INGOT = registerItems("tungsten_iron_alloy_ingot", new Item(new Item.Settings()));
    public static final Item ALUMINIUM_TIN_ALLOY_INGOT = registerItems("aluminium_tin_alloy_ingot", new Item(new Item.Settings()));

    public static final Item TIN_INGOT = registerItems("tin_ingot", new Item(new Item.Settings()));
    public static final Item TUNGSTEN_INGOT = registerItems("tungsten_ingot", new Item(new Item.Settings()));
    public static final Item ALUMINIUM_INGOT = registerItems("aluminium_ingot", new Item(new Item.Settings()));

    public static final Item RAW_TIN = registerItems("raw_tin", new Item(new Item.Settings()));

    public static final Item TUNGSTEN_IRON_ALLOY_SWORD = registerItems("tungsten_iron_alloy_sword", new SwordItem(ModToolMaterials.TUNGSTEN_IRON_ALLOY_SWORD, new Item.Settings().
            attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN_IRON_ALLOY_SWORD, 15, -3.5f))));
    private static Item registerItems(String id, Item item){
        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(AlloyTechnology.MOD_ID, id)),item);
    }
    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(COPPER_IRON_ALLOY_INGOT);
        fabricItemGroupEntries.add(COPPER_TIN_ALLOY_INGOT);
        fabricItemGroupEntries.add(TUNGSTEN_IRON_ALLOY_INGOT);
        fabricItemGroupEntries.add(TIN_INGOT);
        fabricItemGroupEntries.add(TUNGSTEN_INGOT);
        fabricItemGroupEntries.add(RAW_TIN);
    }
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG);
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Items");
    }
}