package de.lavaclient;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LavaClient implements ModInitializer {

    public static final String MOD_ID = "lavaclient";
    public static final String MOD_NAME = "Lava Client";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[LavaClient] Initializing Lava Client {}...", VERSION);
    }
}
