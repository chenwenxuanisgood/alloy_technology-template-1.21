package com.shuangjiangguyi.villager;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPointOfInterestType {

    public static final RegistryKey<PointOfInterestType> ALLOY_KEY = of("alloy_poi");

    private static RegistryKey<PointOfInterestType> of(String id) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(AlloyTechnology.MOD_ID, id));
    }
}
