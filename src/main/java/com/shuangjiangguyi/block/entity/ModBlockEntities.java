package com.shuangjiangguyi.block.entity;

import com.shuangjiangguyi.AlloyTechnology;
import com.shuangjiangguyi.block.ModBlocks;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModBlockEntities {
    public static final BlockEntityType<AlloySynthesizerBlockEntity> ALLOY_SYNTHESIZER = create("alloy_synthesizer_block_entity",
            BlockEntityType.Builder.create(AlloySynthesizerBlockEntity::new, ModBlocks.ALLOY_SYNTHESIZER));
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AlloyTechnology.MOD_ID,id), builder.build(type));
    }
    public static void registerBlockEntities() {

    }
}
