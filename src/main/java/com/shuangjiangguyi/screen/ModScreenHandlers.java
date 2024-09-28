package com.shuangjiangguyi.screen;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.data.AlloyDismantlingTableData;
import com.shuangjiangguyi.block.data.AlloyForgingTableData;
import com.shuangjiangguyi.block.data.DiamondAlloySynthesizerData;
import com.shuangjiangguyi.block.data.IronAlloySynthesizerData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<IronAlloySynthesizerScreenHandler> ALLOY_SYNTHESIZER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AlloyTechnology.MOD_ID, "alloy_synthesizer"),
                    new ExtendedScreenHandlerType<>(IronAlloySynthesizerScreenHandler::new, IronAlloySynthesizerData.CODEC));
    public static final ScreenHandlerType<AlloyForgingTableScreenHandler> ALLOY_FORGING_TABLE =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AlloyTechnology.MOD_ID, "alloy_forging_table"),
                    new ExtendedScreenHandlerType<>(AlloyForgingTableScreenHandler::new, AlloyForgingTableData.CODEC));
    public static final ScreenHandlerType<AlloyDismantlingTableScreenHandler> ALLOY_DISMANTLING_TABLE =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AlloyTechnology.MOD_ID, "alloy_dismantling_table"),
                    new ExtendedScreenHandlerType<>(AlloyDismantlingTableScreenHandler::new, AlloyDismantlingTableData.CODEC));
    public static final ScreenHandlerType<DiamondAlloySynthesizerScreenHandler> DIAMOND_ALLOY_SYNTHESIZER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AlloyTechnology.MOD_ID, "diamond_alloy_synthesizer"),
                    new ExtendedScreenHandlerType<>(DiamondAlloySynthesizerScreenHandler::new, DiamondAlloySynthesizerData.CODEC));
    public static void registerScreenHandlers() {
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Screen Handlers");
    }
}
