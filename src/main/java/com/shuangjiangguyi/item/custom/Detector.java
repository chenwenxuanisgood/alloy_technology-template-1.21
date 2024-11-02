package com.shuangjiangguyi.item.custom;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.block.entity.AlloyAltarBlockEntity;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.mixinInterface.ServerPlayerEntityMixinAccessor;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Detector extends Item {
    public Detector(Settings settings) {
        super(settings);
    }

    private int setTime(int hour, int minute, int second) {
        return ((hour * 60 * 60) + (minute * 60) + second) * 20;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient()) {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                ServerPlayerEntityMixinAccessor accessor = (ServerPlayerEntityMixinAccessor) player;
                user.sendMessage(player.getName());
                user.sendMessage(Text.translatable("text.detector.alloy_altar_proficiency").append(String.valueOf(((int) accessor.getAlloyAltarProficiency().doubleValue()))));
                user.getItemCooldownManager().set(user.getStackInHand(Hand.MAIN_HAND).getItem(), setTime(0, 0, 5));
            } else {
                user.sendMessage(Text.translatable("text.detector.not_player"));
                return ActionResult.PASS;
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
            if (block == ModBlocks.ALLOY_ALTAR) {
                AlloyAltarBlockEntity blockEntity = (AlloyAltarBlockEntity) context.getWorld().getBlockEntity(context.getBlockPos());
                if (blockEntity.judgmentRecipes()) {
                    if (blockEntity.judgmentHammerLevel() == ModItems.COPPER_IRON_ALLOY_HAMMER) {
                        context.getPlayer().sendMessage(Text.translatable("text.detector.need_copper_iron_alloy_hammer"));
                        context.getPlayer().getItemCooldownManager().set(context.getPlayer().getStackInHand(Hand.MAIN_HAND).getItem(), setTime(0, 0, 5));
                    }
                }
            }
        } else {
            Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
            if (block == ModBlocks.ALLOY_ALTAR) {
                AlloyAltarBlockEntity blockEntity = (AlloyAltarBlockEntity) context.getWorld().getBlockEntity(context.getBlockPos());
                if (!blockEntity.judgmentRecipes()) {
                    ServerPlayerEntityMixinAccessor accessor = (ServerPlayerEntityMixinAccessor) context.getPlayer();
                    context.getPlayer().sendMessage(context.getPlayer().getName());
                    context.getPlayer().sendMessage(Text.translatable("text.detector.alloy_altar_proficiency").append(String.valueOf(((int)accessor.getAlloyAltarProficiency().doubleValue()))));
                    context.getPlayer().getItemCooldownManager().set(context.getPlayer().getStackInHand(Hand.MAIN_HAND).getItem(), setTime(0, 0, 5));
                }
            } else {
                ServerPlayerEntityMixinAccessor accessor = (ServerPlayerEntityMixinAccessor) context.getPlayer();
                context.getPlayer().sendMessage(context.getPlayer().getName());
                context.getPlayer().sendMessage(Text.translatable("text.detector.alloy_altar_proficiency").append(String.valueOf(((int)accessor.getAlloyAltarProficiency().doubleValue()))));
                context.getPlayer().getItemCooldownManager().set(context.getPlayer().getStackInHand(Hand.MAIN_HAND).getItem(), setTime(0, 0, 5));
            }
        }
        return super.useOnBlock(context);
    }
}
