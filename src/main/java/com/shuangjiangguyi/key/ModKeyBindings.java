package com.shuangjiangguyi.key;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ModKeyBindings {

    public static void registerModKeyBinding() {
    }
    private static KeyBinding registerKeyBinding(String name, int Key) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.alloy_technology." + name, InputUtil.Type.KEYSYM, Key,
                "key.alloy_technology.alloy_technology"));
    }
    public static void ModKeyBindingEvent() {
    }
}
