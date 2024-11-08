package com.shuangjiangguyi.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ModStackRecipeInput(ItemStack item) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot != 0) {
            throw new IllegalArgumentException("No item for index " + slot);
        } else {
            return this.item;
        }
    }

    @Override
    public int getSize() {
        return 1;
    }
}

