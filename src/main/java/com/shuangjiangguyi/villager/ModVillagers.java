package com.shuangjiangguyi.villager;

import com.google.common.collect.ImmutableSet;
import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

public class ModVillagers {

    public static final VillagerProfession ALLOY_MASTER = register("alloy_master",
            ModPointOfInterestType.ALLOY_KEY, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);

    public static final PointOfInterestType ALLOY_POI = registerPointOfInterestType("alloy_poi", ModBlocks.IRON_ALLOY_SYNTHESIZER);
    private static VillagerProfession register(String id, RegistryKey<PointOfInterestType> heldWorkstation, @Nullable SoundEvent workSound) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(AlloyTechnology.MOD_ID, id),
                new VillagerProfession(id, entry -> entry.matchesKey(heldWorkstation), entry -> entry.matchesKey(heldWorkstation),
                        ImmutableSet.of(), ImmutableSet.of(), workSound));
    }
    private static PointOfInterestType registerPointOfInterestType(String id, Block block) {
        return PointOfInterestHelper.register(Identifier.of(AlloyTechnology.MOD_ID, id), 1, 1, block);
    }
    public static void registerModVillager() {

    }
}
