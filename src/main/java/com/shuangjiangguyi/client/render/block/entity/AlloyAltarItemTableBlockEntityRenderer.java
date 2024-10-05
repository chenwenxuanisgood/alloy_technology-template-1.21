package com.shuangjiangguyi.client.render.block.entity;

import com.shuangjiangguyi.block.entity.AlloyAltarItemTableBlockEntity;
import com.shuangjiangguyi.item.ModItems;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class AlloyAltarItemTableBlockEntityRenderer implements BlockEntityRenderer<AlloyAltarItemTableBlockEntity> {

    public AlloyAltarItemTableBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    private String itemToString(Item item) {
        return item.toString();
    }

    @Override
    public void render(AlloyAltarItemTableBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!Objects.equals(blockEntity.storedItems, null)) {
            String[] itemString = {
                    itemToString(ModItems.COPPER_TIN_ALLOY_INGOT),
                    itemToString(ModItems.COPPER_IRON_ALLOY_INGOT),
                    itemToString(ModItems.ALUMINIUM_TIN_ALLOY_INGOT),
                    itemToString(ModItems.TUNGSTEN_IRON_ALLOY_INGOT)
            };
            Item[] Items = {
                    ModItems.COPPER_TIN_ALLOY_INGOT,
                    ModItems.COPPER_IRON_ALLOY_INGOT,
                    ModItems.ALUMINIUM_TIN_ALLOY_INGOT,
                    ModItems.TUNGSTEN_IRON_ALLOY_INGOT
            };
            for (int i = 0; i < itemString.length; i++) {
                if (Objects.equals(blockEntity.storedItems, itemString[i])) {
                    ItemStack stack = new ItemStack(Items[i]);
                    matrices.push();
                    matrices.translate(0.5, 0.675, 0.5);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4));
                    int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
                    MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, blockEntity.getWorld(), 0);
                    matrices.pop();
                }
            }
        }
    }
}
