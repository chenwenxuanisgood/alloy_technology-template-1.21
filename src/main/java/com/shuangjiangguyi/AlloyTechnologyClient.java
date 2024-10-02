package com.shuangjiangguyi;

import com.shuangjiangguyi.key.ModKeyBindings;
import com.shuangjiangguyi.screen.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;

public class AlloyTechnologyClient implements ClientModInitializer {
    private static KeyBinding MAN;
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALLOY_SYNTHESIZER, IronAlloySynthesizerScreen::new);
        HandledScreens.register(ModScreenHandlers.ALLOY_FORGING_TABLE, AlloyForgingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.ALLOY_DISMANTLING_TABLE, AlloyDismantlingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.DIAMOND_ALLOY_SYNTHESIZER, DiamondAlloySynthesizerScreen::new);
        ModKeyBindings.registerModKeyBinding();
        ModKeyBindings.ModKeyBindingEvent();
    }
}