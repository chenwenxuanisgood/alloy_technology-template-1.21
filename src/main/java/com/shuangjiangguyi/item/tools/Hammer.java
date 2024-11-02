package com.shuangjiangguyi.item.tools;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;

public class Hammer extends PickaxeItem {

    public Hammer(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }
}
