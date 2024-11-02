package com.shuangjiangguyi;

import com.shuangjiangguyi.block.entity.ModBlockEntities;
import com.shuangjiangguyi.client.render.block.entity.AlloyAltarBlockEntityRenderer;
import com.shuangjiangguyi.client.render.block.entity.AlloyAltarItemTableBlockEntityRenderer;
import com.shuangjiangguyi.key.ModKeyBindings;
import com.shuangjiangguyi.particle.ModParticles;
import com.shuangjiangguyi.screen.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class AlloyTechnologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALLOY_SYNTHESIZER, IronAlloySynthesizerScreen::new);
        HandledScreens.register(ModScreenHandlers.ALLOY_FORGING_TABLE, AlloyForgingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.ALLOY_DISMANTLING_TABLE, AlloyDismantlingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.DIAMOND_ALLOY_SYNTHESIZER, DiamondAlloySynthesizerScreen::new);
        ModKeyBindings.registerModKeyBinding();
        ModKeyBindings.ModKeyBindingEvent();
        ParticleFactoryRegistry.getInstance().register(ModParticles.GOLD_PARTICLE, FlameParticle.Factory::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ALLOY_ALTAR_ITEM_TABLE, AlloyAltarItemTableBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ALLOY_ALTAR, AlloyAltarBlockEntityRenderer::new);
    }
}