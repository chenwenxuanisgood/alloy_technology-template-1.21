package com.shuangjiangguyi.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class IronAlloySynthesizerRecipe implements Recipe<SingleStackRecipeInput> {

    private final ItemStack output;

    private final List<Ingredient> recipeItem;

    public IronAlloySynthesizerRecipe(List<Ingredient> recipeItem, ItemStack output) {
        this.output = output;
        this.recipeItem = recipeItem;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list =DefaultedList.ofSize(this.recipeItem.size());
        list.addAll(recipeItem);
        return list;
    }

    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        return(recipeItem.get(0).test(input.item()) && recipeItem.get(1).test(input.item()) && recipeItem.get(2).test(input.item()) && recipeItem.get(3).test(input.item()) && recipeItem.get(4).test(input.item()));
    }

    @Override
    public ItemStack craft(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<IronAlloySynthesizerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "iron_alloy_synthesizer";
    }

    public static class Serializer implements RecipeSerializer<IronAlloySynthesizerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "iron_alloy_synthesizer";

        public static final MapCodec<IronAlloySynthesizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        (Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredient")).flatXmap(ingredients -> {
                            Ingredient[] ingredient1 = (Ingredient[]) ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                            if (ingredient1.length == 0) {
                                return DataResult.error(() -> ("No ingredients"));
                            }
                            return DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredient1));
                        }, DataResult::success).forGetter(recipe -> recipe.getIngredients()),
                (ItemStack.VALIDATED_CODEC.fieldOf("output")).forGetter(recipe -> recipe.output)).apply(instance, IronAlloySynthesizerRecipe::new));

        public static final PacketCodec<RegistryByteBuf, IronAlloySynthesizerRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                Serializer::write, Serializer::read
        );

        private static IronAlloySynthesizerRecipe read(RegistryByteBuf registryByteBuf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(registryByteBuf.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.PACKET_CODEC.decode(registryByteBuf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(registryByteBuf);
            return new IronAlloySynthesizerRecipe(inputs, output);
        }

        private static void write(RegistryByteBuf registryByteBuf, IronAlloySynthesizerRecipe ironAlloySynthesizerRecipe) {
            registryByteBuf.writeInt(ironAlloySynthesizerRecipe.getIngredients().size());
            for (Ingredient ingredient : ironAlloySynthesizerRecipe.getIngredients()) {
                Ingredient.PACKET_CODEC.encode(registryByteBuf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(registryByteBuf, ironAlloySynthesizerRecipe.getResult(null));
        }

        @Override
        public MapCodec<IronAlloySynthesizerRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, IronAlloySynthesizerRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
