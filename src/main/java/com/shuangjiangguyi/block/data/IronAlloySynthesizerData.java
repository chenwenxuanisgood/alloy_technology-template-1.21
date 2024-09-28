package com.shuangjiangguyi.block.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record IronAlloySynthesizerData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, IronAlloySynthesizerData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, IronAlloySynthesizerData::pos, IronAlloySynthesizerData::new);
}
