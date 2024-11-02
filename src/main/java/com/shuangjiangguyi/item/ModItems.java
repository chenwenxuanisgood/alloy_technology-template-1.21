package com.shuangjiangguyi.item;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.item.custom.Detector;
import com.shuangjiangguyi.item.custom.SeaAlloyHorn;
import com.shuangjiangguyi.item.tools.Hammer;
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
    private static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(AlloyTechnology.MOD_ID, id)),item);
    }
    private static Item registerSword(String id, ModToolMaterials ingot, int baseAttackDamage, float attackSpeed) {
        return registerItems(id, new SwordItem(ingot, new Item.Settings().
                attributeModifiers(SwordItem.createAttributeModifiers(ingot, baseAttackDamage, attackSpeed))));
    }
    private static Item registerPickaxe(String id, ModToolMaterials ingot, float baseAttackDamage, float attackSpeed) {
        return registerItems(id, new Hammer(ingot, new Item.Settings().
                attributeModifiers(PickaxeItem.createAttributeModifiers(ingot, baseAttackDamage, attackSpeed))));
    }

    public static final Item COPPER_IRON_ALLOY_INGOT = ordinaryItemRegister("copper_iron_alloy_ingot");
    public static final Item COPPER_TIN_ALLOY_INGOT = ordinaryItemRegister("copper_tin_alloy_ingot");
    public static final Item TUNGSTEN_IRON_ALLOY_INGOT = ordinaryItemRegister("tungsten_iron_alloy_ingot");
    public static final Item ALUMINIUM_TIN_ALLOY_INGOT = ordinaryItemRegister("aluminium_tin_alloy_ingot");
    public static final Item CAST_IRON_INGOT = ordinaryItemRegister("cast_iron_ingot");

    public static final Item TIN_INGOT = ordinaryItemRegister("tin_ingot");
    public static final Item TUNGSTEN_INGOT = ordinaryItemRegister("tungsten_ingot");
    public static final Item ALUMINIUM_INGOT = ordinaryItemRegister("aluminium_ingot");
    public static final Item CARBON_INGOT = ordinaryItemRegister("carbon_ingot");
    public static final Item SILICON_INGOT = ordinaryItemRegister("silicon_ingot");

    public static final Item RAW_TIN = ordinaryItemRegister("raw_tin");

    public static final Item EMPTY_ALLOY_TEMPLATE = ordinaryItemRegister("empty_alloy_template");
    public static final Item ALLOY_SWORD_TEMPLATE = ordinaryItemRegister("alloy_sword_template");
    public static final Item ALLOY_HAMMER_TEMPLATE = ordinaryItemRegister("alloy_hammer_template");

    public static final Item TUNGSTEN_IRON_ALLOY_SWORD = registerSword("tungsten_iron_alloy_sword", ModToolMaterials.TUNGSTEN_IRON_ALLOY_INGOT, 15, -3.3f);

    public static final Item COPPER_TIN_ALLOY_HAMMER = registerPickaxe("copper_tin_alloy_hammer", ModToolMaterials.COPPER_TIN_ALLOY_INGOT, 2.5f, -2f);
    public static final Item COPPER_IRON_ALLOY_HAMMER = registerPickaxe("copper_iron_alloy_hammer", ModToolMaterials.COPPER_IRON_ALLOY_INGOT, 4f, -2.2f);
    public static final Item ALUMINIUM_TIN_ALLOY_HAMMER = registerPickaxe("aluminium_tin_alloy_hammer", ModToolMaterials.ALUMINIUM_TIN_ALLOY_INGOT, 5.5f, -2.4f);
    public static final Item TUNGSTEN_IRON_ALLOY_HAMMER = registerPickaxe("tungsten_iron_alloy_hammer", ModToolMaterials.TUNGSTEN_IRON_ALLOY_INGOT, 7f, -2.6f);

    public static final Item SEA_ALLOY_HORN = registerItems("sea_alloy_horn", new SeaAlloyHorn(new Item.Settings().maxDamage(249)));
    public static final Item DETECTOR = registerItems("detector", new Detector(new Item.Settings()));

    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(COPPER_IRON_ALLOY_INGOT);
        fabricItemGroupEntries.add(COPPER_TIN_ALLOY_INGOT);
        fabricItemGroupEntries.add(TUNGSTEN_IRON_ALLOY_INGOT);
        fabricItemGroupEntries.add(ALUMINIUM_TIN_ALLOY_INGOT);
        fabricItemGroupEntries.add(TIN_INGOT);
        fabricItemGroupEntries.add(TUNGSTEN_INGOT);
        fabricItemGroupEntries.add(ALUMINIUM_INGOT);
        fabricItemGroupEntries.add(CARBON_INGOT);
        fabricItemGroupEntries.add(SILICON_INGOT);
        fabricItemGroupEntries.add(EMPTY_ALLOY_TEMPLATE);
        fabricItemGroupEntries.add(ALLOY_SWORD_TEMPLATE);
        fabricItemGroupEntries.add(ALLOY_HAMMER_TEMPLATE);
        fabricItemGroupEntries.add(RAW_TIN);
    }
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG);
        AlloyTechnology.LOGGER.info("合金科技物品注册");
    }
}