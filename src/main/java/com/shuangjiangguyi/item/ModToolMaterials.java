package com.shuangjiangguyi.item;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    TUNGSTEN_IRON_ALLOY_INGOT(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 10.0f, 4.0f, 10,
            () -> Ingredient.ofItems(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)),
    ALUMINIUM_TIN_ALLOY_INGOT(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2000, 8.0f, 4.0f, 10,
            () -> Ingredient.ofItems(ModItems.ALUMINIUM_TIN_ALLOY_INGOT)),
    COPPER_IRON_ALLOY_INGOT(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 6.0f, 4.0f, 10,
            () -> Ingredient.ofItems(ModItems.COPPER_IRON_ALLOY_INGOT)),
    COPPER_TIN_ALLOY_INGOT(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1000, 4.0f, 4.0f, 10,
            () -> Ingredient.ofItems(ModItems.COPPER_TIN_ALLOY_INGOT));

    private final TagKey<Block> inverseTag;

    private final int itemDurability;

    private final float miningSpeed;

    private final float attackDamage;

    private final int enchantability;

    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
