package com.shuangjiangguyi.mixinInterface;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

public interface ServerPlayerEntityMixinAccessor {
    public NbtCompound getAlloyTechnology();
    public NbtDouble getAlloyAltarProficiency();
    public void setAlloyAltarProficiency(NbtDouble nbtDouble);
}
