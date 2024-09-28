package com.shuangjiangguyi;

import com.shuangjiangguyi.screen.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AlloyTechnologyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALLOY_SYNTHESIZER, IronAlloySynthesizerScreen::new);
        HandledScreens.register(ModScreenHandlers.ALLOY_FORGING_TABLE, AlloyForgingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.ALLOY_DISMANTLING_TABLE, AlloyDismantlingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.DIAMOND_ALLOY_SYNTHESIZER, DiamondAlloySynthesizerScreen::new);
    }
}
