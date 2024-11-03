package com.shuangjiangguyi;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.block.entity.ModBlockEntities;
import com.shuangjiangguyi.enchantment.ModEnchantments;
import com.shuangjiangguyi.groups.ModItemGroups;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.particle.ModParticles;
import com.shuangjiangguyi.screen.ModScreenHandlers;
import com.shuangjiangguyi.sound.ModSoundEvent;
import com.shuangjiangguyi.tags.ModBlockTags;
import com.shuangjiangguyi.tags.ModItemTags;
import com.shuangjiangguyi.util.ModCustomTrades;
import com.shuangjiangguyi.villager.ModVillagers;
import com.shuangjiangguyi.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlloyTechnology implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "alloy_technology";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModItemGroups.registerModItemGroups();
		ModScreenHandlers.registerScreenHandlers();
		ModWorldGeneration.generateModWorldGen();
		ModVillagers.registerModVillager();
		ModCustomTrades.registerModCustomTrades();
		ModItemTags.registerModItemTags();
		ModBlockTags.registerModBlockTags();
		ModSoundEvent.registerModSoundEvents();
		ModParticles.registerModParticles();
		ModEnchantments.registerModEnchantments();
		LOGGER.info("合金科技");
	}
}