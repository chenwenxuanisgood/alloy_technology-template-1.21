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
    public static final BlockEntityType<IronAlloySynthesizerBlockEntity> IRON_ALLOY_SYNTHESIZER = create("iron_alloy_synthesizer_block_entity",
            BlockEntityType.Builder.create(IronAlloySynthesizerBlockEntity::new, ModBlocks.IRON_ALLOY_SYNTHESIZER));
    public static final BlockEntityType<AlloyForgingTableBlockEntity> ALLOY_FORGING_TABLE = create("alloy_forging_table_block_entity",
            BlockEntityType.Builder.create(AlloyForgingTableBlockEntity::new, ModBlocks.ALLOY_FORGING_TABLE));
    public static final BlockEntityType<AlloyDismantlingTableBlockEntity> ALLOY_DISMANTLING_TABLE = create("iron_alloy_dismantling_table_block_entity",
            BlockEntityType.Builder.create(AlloyDismantlingTableBlockEntity::new, ModBlocks.ALLOY_DISMANTLING_TABLE));
    public static final BlockEntityType<DiamondAlloySynthesizerBlockEntity> DIAMOND_ALLOY_SYNTHESIZER = create("diamond_alloy_synthesizer_block_entity",
            BlockEntityType.Builder.create(DiamondAlloySynthesizerBlockEntity::new, ModBlocks.DIAMOND_ALLOY_SYNTHESIZER));
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AlloyTechnology.MOD_ID,id), builder.build(type));
    }
    public static void registerBlockEntities() {
        AlloyTechnology.LOGGER.info("Alloy Technology Registering Block Entities");
    }
}
