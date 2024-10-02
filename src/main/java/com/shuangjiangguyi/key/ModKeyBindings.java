package com.shuangjiangguyi.key;

import com.shuangjiangguyi.sound.ModSoundEvent;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    private static KeyBinding MAN;

    public static void registerModKeyBinding() {
        MAN = registerKeyBinding("man", GLFW.GLFW_KEY_M);
    }
    private static KeyBinding registerKeyBinding(String name, int Key) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.alloy_technology." + name, InputUtil.Type.KEYSYM, Key,
                "key.alloy_technology.alloy_technology"));
    }
    public static void ModKeyBindingEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (MAN.wasPressed()) {
                client.world.playSound(client.player, client.player.getBlockPos(), ModSoundEvent.SEA_ALLOY_HORN, client.player.getSoundCategory(), 1.0F, 1.0F);
            }
        });
    }
}
