package me.jellysquid.mods.sodium.client.world.biome;

import net.chlorinemc.carbon.compat.mc.IBlockColor;
import net.minecraft.block.state.IBlockState;

public interface BlockColorsExtended {
    IBlockColor getColorProvider(IBlockState state);
}
