package com.shuangjiangguyi.world.gen;

import com.shuangjiangguyi.AlloyTechnology;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Mod World Generation");
    }
}
