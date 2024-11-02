package com.shuangjiangguyi.client.render.block.entity;

import com.shuangjiangguyi.block.custom.AlloyAltarItemTable;
import com.shuangjiangguyi.block.entity.AlloyAltarBlockEntity;
import com.shuangjiangguyi.block.entity.AlloyAltarItemTableBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class AlloyAltarBlockEntityRenderer implements BlockEntityRenderer<AlloyAltarBlockEntity> {

    public AlloyAltarBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(AlloyAltarBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity.judgmentRecipes()) {
            ItemStack stack = new ItemStack(blockEntity.getOutputItem());
            matrices.push();
            matrices.translate(0.5, 0.675, 0.5);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4));
            int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, blockEntity.getWorld(), 0);
            matrices.pop();
        }
    }
}
