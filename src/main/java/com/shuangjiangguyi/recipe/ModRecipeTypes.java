package com.shuangjiangguyi.recipe;

import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static void registerRecipeType() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(AlloyTechnology.MOD_ID, AlloySynthesizerRecipe.Serializer.ID),
                AlloySynthesizerRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(AlloyTechnology.MOD_ID, AlloySynthesizerRecipe.Type.ID),
                AlloySynthesizerRecipe.Type.INSTANCE);
    }
}
