package com.shuangjiangguyi;

import com.shuangjiangguyi.block.ModBlocks;
import com.shuangjiangguyi.block.entity.ModBlockEntities;
import com.shuangjiangguyi.groups.ModGroups;
import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.recipe.ModRecipeTypes;
import com.shuangjiangguyi.screen.ModScreenHandlers;
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
		ModGroups.registerModGroups();
		ModRecipeTypes.registerRecipeType();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModWorldGeneration.generateModWorldGen();
		ModVillagers.registerModVillager();
		ModCustomTrades.registerModCustomTrades();
		LOGGER.info("Mod Name:Alloy Technology");
	}
}