package com.shuangjiangguyi.tags;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AlloyTechnology.MOD_ID, id));
    }
    public static void registerModBlockTags() {
        AlloyTechnology.LOGGER.info("合金科技方块标签注册");
    }
}
