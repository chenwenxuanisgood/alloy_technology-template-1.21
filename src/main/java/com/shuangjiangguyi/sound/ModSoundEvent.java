package com.shuangjiangguyi.sound;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import net.minecraft.registry.Registry;

public class ModSoundEvent {
    public static final SoundEvent SEA_ALLOY_HORN = register("sea_alloy_horn");

    private static SoundEvent register(String name) {
        Identifier id = Identifier.of(AlloyTechnology.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSoundEvents() {

    }
}
