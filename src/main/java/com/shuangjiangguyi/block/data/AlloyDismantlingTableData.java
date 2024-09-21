package com.shuangjiangguyi.block.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record AlloyDismantlingTableData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, AlloyDismantlingTableData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, AlloyDismantlingTableData::pos, AlloyDismantlingTableData::new);
}
