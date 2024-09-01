package com.shuangjiangguyi.mixin;

import com.mojang.authlib.GameProfile;
import com.shuangjiangguyi.mixinInterface.ServerPlayerEntityMixinAccessor;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements ServerPlayerEntityMixinAccessor {

    @Unique // 不是原版中的变量，不会覆盖源代码
    private NbtCompound alloyCraftProficiency; // alloyCraf

    @Unique
    private NbtInt alloyCraftLevel;
    // 构造函数mixin
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(MinecraftServer server, ServerWorld world, GameProfile profile, SyncedClientOptions clientOptions, CallbackInfo ci){
        this.alloyCraftProficiency = new NbtCompound(); // alloyCraftProficiency:{}
        this.alloyCraftProficiency.put("alloyCraftLevel", NbtInt.of(1)); // alloyCraftProficiency:{alloyCraftLevel:0}
    }
    // 当玩家进入游戏后将数据写入到玩家的Nbt数据中
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void onWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        if (this.alloyCraftProficiency != null){
            nbt.put("alloyCraftProficiency", this.alloyCraftProficiency);
        }
    }
    // 当玩家进入游戏后将数据写入到玩家实体
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void onReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        if(nbt.contains("alloyCraftProficiency", 10)){
            this.alloyCraftProficiency = nbt.getCompound("alloyCraftProficiency");
        }else{
            this.alloyCraftProficiency = new NbtCompound();
            this.alloyCraftLevel = NbtInt.of(1);
            this.alloyCraftProficiency.put("alloyCraftLevel", this.alloyCraftLevel);
        }
    }


    // 当玩家发生维度切换的时候，会调用CopyFrom方法来进行数据转移，还有当玩家重新登录服务器的时候也会调用该方法
    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void onCopyForm(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci){
        if(oldPlayer instanceof ServerPlayerEntityMixinAccessor playerEntityMixinAccessor){
            if (playerEntityMixinAccessor.getAlloyCraftProficiency() != null){
                this.alloyCraftProficiency = playerEntityMixinAccessor.getAlloyCraftProficiency();
            }
        }
    }

    @Override
    public NbtCompound getAlloyCraftProficiency() {
        return alloyCraftProficiency;
    }

    @Override
    public void setAlloyCraftProficiency(NbtCompound alloy_craft_proficiency) {
        this.alloyCraftProficiency = alloy_craft_proficiency;
    }

    @Override
    public NbtInt getAlloyCraftLevel() {
        return alloyCraftLevel;
    }

    @Override
    public void setAlloyCraftLevel(NbtInt alloy_craft_level) {
        this.alloyCraftLevel = alloy_craft_level; // 更新玩家实例中的变量值
        this.alloyCraftProficiency.put("alloyCraftLevel", this.alloyCraftLevel); // 更新了nbt对象中的值
    }

}
