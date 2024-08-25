package com.shuangjiangguyi.block;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.custom.AlloySynthesizer;
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
    public static final Block COPPER_TIN_ALLOY_BLOCK = register("copper_tin_alloy_block", new Block(AbstractBlock.Settings.create().requiresTool().strength(5.0f,6.0f)));
    public static final Block COPPER_IRON_ALLOY_BLOCK = register("copper_iron_alloy_block", new Block(AbstractBlock.Settings.create().requiresTool().strength(5.0f,6.0f)));
    public static final Block ALLOY_SYNTHESIZER = register("alloy_synthesizer",
            new AlloySynthesizer(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static void registerBlockItem(String id, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(AlloyTechnology.MOD_ID, id), new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }
    public static Block register(String id, Block block) {
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, RegistryKey.of(Registries.BLOCK.getKey(), Identifier.of(AlloyTechnology.MOD_ID, id)),block);
    }
    public static void registerModBlocks() {
        AlloyTechnology.LOGGER.info("Registering Block");
    }
}
