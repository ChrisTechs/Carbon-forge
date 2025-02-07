package me.jellysquid.mods.sodium.client.gl.compat;

import me.jellysquid.mods.sodium.client.render.chunk.shader.ChunkFogMode;
import github.christechs.sodiumcompat.reflection.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class FogHelper {
    private static final float FAR_PLANE_THRESHOLD_EXP = (float) Math.log(1.0f / 0.0019f);
    private static final float FAR_PLANE_THRESHOLD_EXP2 = MathHelper.sqrt_double(FAR_PLANE_THRESHOLD_EXP);

    public static float getFogEnd() {
    	return ReflectionHelper.getFogState().end;
    }

    public static float getFogStart() {
    	return ReflectionHelper.getFogState().start;
    }

    public static float getFogDensity() {
    	return ReflectionHelper.getFogState().density;
    }

    /**
     * Retrieves the current fog mode from the fixed-function pipeline.
     */
    public static ChunkFogMode getFogMode() {
        int mode = ReflectionHelper.getFogState().mode;

        if(mode == 0 || !ReflectionHelper.getCurrentState(ReflectionHelper.getFogState().fog))
        	return ChunkFogMode.NONE;

        switch (mode) {
            case GL11.GL_EXP2:
            case GL11.GL_EXP:
                return ChunkFogMode.EXP2;
            case GL11.GL_LINEAR:
                return ChunkFogMode.LINEAR;
            default:
                throw new UnsupportedOperationException("Unknown fog mode: " + mode);
        }
    }

    public static float getFogCutoff() {
    	int mode = ReflectionHelper.getFogState().mode;

        switch (mode) {
            case GL11.GL_LINEAR:
                return getFogEnd();
            case GL11.GL_EXP:
                return FAR_PLANE_THRESHOLD_EXP / getFogDensity();
            case GL11.GL_EXP2:
                return FAR_PLANE_THRESHOLD_EXP2 / getFogDensity();
            default:
                return 0.0f;
        }
    }
    
    public static float[] getFogColor() {
        EntityRenderer er = Minecraft.getMinecraft().entityRenderer;
    	return new float[]{ReflectionHelper.getFogColorRed(er), ReflectionHelper.getFogColorGreen(er), ReflectionHelper.getFogColorBlue(er), 1.0F};
    }
}
