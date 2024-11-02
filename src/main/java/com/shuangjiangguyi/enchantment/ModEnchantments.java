package com.shuangjiangguyi.enchantment;

import com.mojang.serialization.MapCodec;
import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(AlloyTechnology.MOD_ID, id), codec);
    }

    private static RegistryKey<Enchantment> registerEnchantments(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(AlloyTechnology.MOD_ID, id));
    }

    public static void registerModEnchantments() {
        AlloyTechnology.LOGGER.info("合金科技附魔注册");
    }
}
