package com.shuangjiangguyi.world;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> TIN_ORE_PLACED_KEY = registryKey("tin_ore_placed");
    public static final RegistryKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY = registryKey("tungsten_ore_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(context, TIN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_PLACED_KEY),
                ModOrePlacement.modifiersWithCount(10,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(70))));
        register(context, TUNGSTEN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TUNGSTEN_ORE_PLACED_KEY),
                ModOrePlacement.modifiersWithCount(10,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(0))));
    }
    public static RegistryKey<PlacedFeature> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(AlloyTechnology.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
