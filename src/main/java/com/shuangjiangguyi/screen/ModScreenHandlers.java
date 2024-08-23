package com.shuangjiangguyi.screen;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.data.AlloySynthesizerData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AlloySynthesizerScreenHandler> ALLOY_SYNTHESIZER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AlloyTechnology.MOD_ID, "alloy_synthesizer"),
                    new ExtendedScreenHandlerType<>(AlloySynthesizerScreenHandler::new, AlloySynthesizerData.CODEC));
    public static void registerScreenHandlers() {

    }
}
