package com.shuangjiangguyi.block.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record AlloyForgingTableData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, AlloyForgingTableData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, AlloyForgingTableData::pos, AlloyForgingTableData::new);
}
