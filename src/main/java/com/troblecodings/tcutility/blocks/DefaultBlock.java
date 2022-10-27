package com.troblecodings.tcutility.blocks;

import net.minecraft.block.Block;

public class DefaultBlock extends Block {

    public DefaultBlock(final BlockCreateInfo blockInfo) {
        super(blockInfo.material);
        this.setHardness(blockInfo.hardness);
        this.setSoundType(blockInfo.soundtype);
        this.setLightOpacity(blockInfo.opacity);
    }
}