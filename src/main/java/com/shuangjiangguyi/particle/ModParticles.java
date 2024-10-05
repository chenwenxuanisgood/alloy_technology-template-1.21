package com.shuangjiangguyi.particle;

import com.shuangjiangguyi.AlloyTechnology;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType GOLD_PARTICLE = register("gold_particle", FabricParticleTypes.simple());

    public static SimpleParticleType register(String name, SimpleParticleType type) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(AlloyTechnology.MOD_ID, name), type);
    }

    public static void registerModParticles() {

    }
}
