package com.shuangjiangguyi.recipe;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static void registerRecipeTypes() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(AlloyTechnology.MOD_ID, IronAlloySynthesizerRecipe.Serializer.ID),
                IronAlloySynthesizerRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(AlloyTechnology.MOD_ID, IronAlloySynthesizerRecipe.Type.ID),
                IronAlloySynthesizerRecipe.Type.INSTANCE);
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Recipe Types");
    }
}
