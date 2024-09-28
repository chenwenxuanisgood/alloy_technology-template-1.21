package com.shuangjiangguyi.block.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record DiamondAlloySynthesizerData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, DiamondAlloySynthesizerData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, DiamondAlloySynthesizerData::pos, DiamondAlloySynthesizerData::new);
}
