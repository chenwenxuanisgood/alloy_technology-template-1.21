package com.shuangjiangguyi.mixin;

import com.mojang.authlib.GameProfile;
import com.shuangjiangguyi.mixinInterface.ServerPlayerEntityMixinAccessor;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
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
    private NbtCompound alloyTechnology;

    @Unique
    private NbtDouble alloyAltarProficiency;
    // 构造函数mixin
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(MinecraftServer server, ServerWorld world, GameProfile profile, SyncedClientOptions clientOptions, CallbackInfo ci){
        this.alloyTechnology = new NbtCompound(); // alloyTechnology:{}
        this.alloyAltarProficiency = NbtDouble.of(1);
        this.alloyTechnology.put("alloyAltarProficiency", NbtDouble.of(1)); // alloyTechnology:{alloyAltarProficiency:0}
    }
    // 当玩家进入游戏后将数据写入到玩家的Nbt数据中
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void onWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        if (this.alloyTechnology != null){
            nbt.put("alloyTechnology", this.alloyTechnology);
        }
    }
    // 当玩家进入游戏后将数据写入到玩家实体
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void onReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        if(nbt.contains("alloyTechnology", 10)){
            this.alloyTechnology = nbt.getCompound("alloyTechnology");
        }else{
            this.alloyTechnology = new NbtCompound();
            this.alloyAltarProficiency = NbtDouble.of(1);
            this.alloyTechnology.put("alloyAltarProficiency", this.alloyAltarProficiency);
        }
    }


    // 当玩家发生维度切换的时候，会调用CopyFrom方法来进行数据转移，还有当玩家重新登录服务器的时候也会调用该方法
    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void onCopyForm(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci){
        if(oldPlayer instanceof ServerPlayerEntityMixinAccessor playerEntityMixinAccessor){
            if (playerEntityMixinAccessor.getAlloyTechnology() != null){
                this.alloyTechnology = playerEntityMixinAccessor.getAlloyTechnology();
            }
        }
    }

    @Override
    public NbtCompound getAlloyTechnology() {
        return alloyTechnology;
    }

    @Override
    public NbtDouble getAlloyAltarProficiency() {
        return alloyAltarProficiency;
    }

    @Override
    public void setAlloyAltarProficiency(NbtDouble alloy_craft_level) {
        this.alloyAltarProficiency = alloy_craft_level; // 更新玩家实例中的变量值
        this.alloyTechnology.put("alloyAltarProficiency", this.alloyAltarProficiency); // 更新了nbt对象中的值
    }

}
