package com.shuangjiangguyi.item.tools;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
public class Hammer extends PickaxeItem  {

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    public Hammer(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
}
