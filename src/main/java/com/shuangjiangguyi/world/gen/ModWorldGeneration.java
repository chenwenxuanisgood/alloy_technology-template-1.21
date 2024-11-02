package com.shuangjiangguyi.world.gen;

import com.shuangjiangguyi.AlloyTechnology;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
        AlloyTechnology.LOGGER.info("合金科技模组世界生成生成");
    }
}
