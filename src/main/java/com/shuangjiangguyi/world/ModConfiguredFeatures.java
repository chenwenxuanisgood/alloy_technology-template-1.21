package com.shuangjiangguyi.world;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_PLACED_KEY = registerKey("tin_ore");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stonePlace = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overWorld =
                List.of(OreFeatureConfig.createTarget(stonePlace, ModBlocks.TIN_ORE.getDefaultState())
                );

        register(context, TIN_ORE_PLACED_KEY, Feature.ORE, new OreFeatureConfig(overWorld, 20));
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(AlloyTechnology.MOD_ID, name));
    }

    private static <FD extends FeatureConfig, F extends Feature<FD>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FD configuration) { //, PlacementType<ConfiguredFeature<?, ?>> placement, PlacementArgs args) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
