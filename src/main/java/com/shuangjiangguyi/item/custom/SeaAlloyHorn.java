package com.shuangjiangguyi.item.custom;

import com.shuangjiangguyi.sound.ModSoundEvent;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerConfigurationTask;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SeaAlloyHorn extends Item {
    public SeaAlloyHorn(Settings settings) {
        super(settings);
    }

    private int setTime(int hour, int minute, int second) {
        return ((hour * 60 * 60) + (minute * 60) + second) * 20;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (hand == Hand.MAIN_HAND) {
                if (!Screen.hasShiftDown()) {
                    world.playSound(null, user.getBlockPos(), ModSoundEvent.SEA_ALLOY_HORN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, setTime(0, 5, 0), 2, true, true, true));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, setTime(0, 5, 0), 2, true, true, true));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, setTime(0, 0, 30), 2, true, true, true));
                    user.getStackInHand(Hand.MAIN_HAND).damage(1, user, EquipmentSlot.MAINHAND);
                    //user.getItemCooldownManager().set(user.getStackInHand(Hand.MAIN_HAND).getItem(), setTime(0, 6, 0));
                }
                else if (Screen.hasShiftDown()) {
                    world.playSound(null, user.getBlockPos(), ModSoundEvent.SEA_ALLOY_HORN, SoundCategory.BLOCKS, 1.0f, 5.0f);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, setTime(0, 10, 0), 5, true, true, true));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, setTime(0, 10, 0), 5, true, true, true));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, setTime(0, 2, 0), 5, true, true, true));
                    user.getStackInHand(Hand.MAIN_HAND).damage(5, user, EquipmentSlot.MAINHAND);
                    //user.getItemCooldownManager().set(user.getStackInHand(Hand.MAIN_HAND).getItem(), setTime(0, 14, 0));
                }
            }
        }
        return super.use(world, user, hand);
    }
}
