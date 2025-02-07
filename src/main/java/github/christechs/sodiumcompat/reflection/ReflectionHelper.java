package github.christechs.sodiumcompat.reflection;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.biome.BiomeGenBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class ReflectionHelper {

    private static Field fogStateField = null;

    private static Field currentStateField = null;

    private static Field fogColorRed = null, fogColorGreen = null, fogColorBlue = null;

    private static Field entityRenderMap = null;

    private static Field theWorld = null;

    private static Method getColorAtPosMethod = null;

    static {
        try {
            fogStateField = GlStateManager.class.getDeclaredField("field_179155_g");
            currentStateField = GlStateManager.BooleanState.class.getDeclaredField("field_179201_b");
            fogColorRed = EntityRenderer.class.getDeclaredField("field_175080_Q");
            fogColorGreen = EntityRenderer.class.getDeclaredField("field_175082_R");
            fogColorBlue = EntityRenderer.class.getDeclaredField("field_175081_S");
            entityRenderMap = RenderManager.class.getDeclaredField("field_78729_o");
            theWorld = RenderGlobal.class.getDeclaredField("field_72769_h");

            getColorAtPosMethod = BiomeColorHelper.ColorResolver.class.getDeclaredMethod("func_180283_a", BiomeGenBase.class, BlockPos.class);
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        fogStateField.setAccessible(true);
        currentStateField.setAccessible(true);
        fogColorRed.setAccessible(true);
        fogColorGreen.setAccessible(true);
        fogColorBlue.setAccessible(true);
        entityRenderMap.setAccessible(true);
        theWorld.setAccessible(true);

        getColorAtPosMethod.setAccessible(true);
    }

    public static GlStateManager.FogState getFogState() {
        try {
            return (GlStateManager.FogState) fogStateField.get(null);
        }catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean getCurrentState(GlStateManager.BooleanState state) {
        try {
            return currentStateField.getBoolean(state);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static float getFogColorRed(EntityRenderer er) {
        try {
            return fogColorRed.getFloat(er);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0f;
    }

    public static float getFogColorGreen(EntityRenderer er) {
        try {
            return fogColorGreen.getFloat(er);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0f;
    }

    public static float getFogColorBlue(EntityRenderer er) {
        try {
            return fogColorBlue.getFloat(er);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0f;
    }

    public static Map<Class<? extends Entity>, Render<? extends Entity>> getEntityRenderMap(RenderManager rm) {
        try {
            return (Map<Class<? extends Entity>, Render<? extends Entity>>) entityRenderMap.get(rm);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static WorldClient getTheWorld(RenderGlobal rg) {
        try {
            return (WorldClient) theWorld.get(rg);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getColorAtPosMethod(BiomeColorHelper.ColorResolver res, BiomeGenBase biome, BlockPos blockPos) {
        try {
            return (int) getColorAtPosMethod.invoke(res, biome, blockPos);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
