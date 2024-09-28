package com.shuangjiangguyi.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.shuangjiangguyi.AlloyTechnology;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DiamondAlloySynthesizerScreen extends HandledScreen<DiamondAlloySynthesizerScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(AlloyTechnology.MOD_ID, "textures/gui/container/diamond_alloy_synthesizer.png");
    public DiamondAlloySynthesizerScreen(DiamondAlloySynthesizerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCraft()) {
            context.drawTexture(TEXTURE, x + 55, y + 30, 176, 12,  handler.getScaledProgress(), 60);
        }
    }
}
