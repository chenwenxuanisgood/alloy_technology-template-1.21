package com.shuangjiangguyi.block;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.custom.AlloyDismantlingTable;
import com.shuangjiangguyi.block.custom.AlloyForgingTable;
import com.shuangjiangguyi.block.custom.DiamondAlloySynthesizer;
import com.shuangjiangguyi.block.custom.IronAlloySynthesizer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBlocks {
    private static Block ordinaryBlockRegister(String id, float hardness, float resistance) {
        return register(id, new Block(AbstractBlock.Settings.create().requiresTool().strength(hardness,resistance)));
    }

    public static final Block COPPER_TIN_ALLOY_BLOCK = ordinaryBlockRegister("copper_tin_alloy_block", 5.0f, 6.0f);
    public static final Block COPPER_IRON_ALLOY_BLOCK = ordinaryBlockRegister("copper_iron_alloy_block", 5.0f, 6.0f);
    public static final Block TUNGSTEN_IRON_ALLOY_BLOCK = ordinaryBlockRegister("tungsten_iron_alloy_block", 7.0f,8.0f);
    public static final Block ALUMINIUM_TIN_ALLOY_BLOCK = ordinaryBlockRegister("aluminium_tin_alloy_block", 6.0f,7.0f);
    public static final Block TIN_BLOCK = ordinaryBlockRegister("tin_block", 4.0f,5.0f);
    public static final Block TUNGSTEN_BLOCK = ordinaryBlockRegister("tungsten_block", 5.0f,6.0f);
    public static final Block TIN_ORE = ordinaryBlockRegister("tin_ore", 2.0f,3.0f);
    public static final Block TUNGSTEN_ORE = ordinaryBlockRegister("tungsten_ore", 5.0f,6.0f);
    public static final Block IRON_ALLOY_SYNTHESIZER = register("iron_alloy_synthesizer",
            new IronAlloySynthesizer(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block ALLOY_FORGING_TABLE = register("alloy_forging_table",
            new AlloyForgingTable(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block ALLOY_DISMANTLING_TABLE = register("alloy_dismantling_table",
            new AlloyDismantlingTable(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block DIAMOND_ALLOY_SYNTHESIZER = register("diamond_alloy_synthesizer",
            new DiamondAlloySynthesizer(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)));
    public static void registerBlockItem(String id, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(AlloyTechnology.MOD_ID, id), new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }
    public static Block register(String id, Block block) {
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, RegistryKey.of(Registries.BLOCK.getKey(), Identifier.of(AlloyTechnology.MOD_ID, id)),block);
    }
    public static void registerModBlocks() {
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Block");
    }
}
