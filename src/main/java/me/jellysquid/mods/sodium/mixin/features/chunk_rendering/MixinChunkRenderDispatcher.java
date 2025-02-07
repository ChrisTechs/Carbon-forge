package me.jellysquid.mods.sodium.mixin.features.chunk_rendering;

import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChunkRenderDispatcher.class)
public class MixinChunkRenderDispatcher {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 2))
    public int modifyThreadPoolSize(int constant) {
        return 1;
    }
}
