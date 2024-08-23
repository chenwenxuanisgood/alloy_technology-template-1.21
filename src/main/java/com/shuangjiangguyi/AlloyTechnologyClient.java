package com.shuangjiangguyi;

import com.shuangjiangguyi.screen.AlloySynthesizerScreen;
import com.shuangjiangguyi.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AlloyTechnologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALLOY_SYNTHESIZER, AlloySynthesizerScreen::new);
    }
}
