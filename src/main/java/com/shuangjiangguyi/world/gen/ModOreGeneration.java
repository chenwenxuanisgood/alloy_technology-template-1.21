package com.shuangjiangguyi.world.gen;

import com.shuangjiangguyi.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld()
                ,GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.TIN_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld()
                ,GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.TUNGSTEN_ORE_PLACED_KEY);
    }
}
