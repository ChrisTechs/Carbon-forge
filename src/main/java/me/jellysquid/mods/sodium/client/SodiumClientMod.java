package me.jellysquid.mods.sodium.client;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SodiumClientMod.MODID, version = "0.1",  useMetadata = true)
public class SodiumClientMod {

    public static final String MODID = "vintagium";
    public static final String MODNAME = "Vintagium";
    public static final String MOD_VERSION = "1.0.0";

    private static SodiumGameOptions CONFIG;
    public static Logger LOGGER = LogManager.getLogger(MODNAME);

    public static SodiumGameOptions options() {
        if (CONFIG == null) {
            CONFIG = loadConfig();
        }

        return CONFIG;
    }

    public static Logger logger() {
        if (LOGGER == null) {
            LOGGER = LogManager.getLogger(MODNAME);
        }

        return LOGGER;
    }

    private static SodiumGameOptions loadConfig() {
        return SodiumGameOptions.load(Minecraft.getMinecraft().mcDataDir.toPath().resolve("config").resolve(MODID + "-options.json"));
    }

    public static String getVersion() {
        return MOD_VERSION;
    }
    
    public static boolean isDirectMemoryAccessEnabled() {
        return options().advanced.allowDirectMemoryAccess;
    }

}
