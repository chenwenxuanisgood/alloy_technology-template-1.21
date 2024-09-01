package com.shuangjiangguyi.mixinInterface;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;

public interface ServerPlayerEntityMixinAccessor {
    public NbtCompound getAlloyCraftProficiency();
    public void setAlloyCraftProficiency(NbtCompound nbtCompound);
    public NbtInt getAlloyCraftLevel();
    public void setAlloyCraftLevel(NbtInt nbtInt);
}
