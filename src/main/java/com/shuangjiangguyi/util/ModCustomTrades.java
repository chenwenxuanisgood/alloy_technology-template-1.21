package com.shuangjiangguyi.util;

import com.shuangjiangguyi.item.ModItems;
import com.shuangjiangguyi.villager.ModVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.TradeOffers;

public class ModCustomTrades {
    public static void registerModCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ALLOY_MASTER, 1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.COPPER_IRON_ALLOY_INGOT, 2, 1,20,1, 1f));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ALLOY_MASTER, 2, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.COPPER_TIN_ALLOY_INGOT, 4, 1,20,1, 1f));
            factories.add(new TradeOffers.SellItemFactory(ModItems.ALUMINIUM_TIN_ALLOY_INGOT, 8, 1,20,1, 1f));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ALLOY_MASTER, 3, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.TUNGSTEN_IRON_ALLOY_INGOT, 16, 1, 20, 1, 1f));
        });
    }
}
