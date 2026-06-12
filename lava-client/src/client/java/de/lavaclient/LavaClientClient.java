package de.lavaclient;

import de.lavaclient.config.ConfigManager;
import de.lavaclient.event.EventBus;
import de.lavaclient.feature.challenges.ChallengeManager;
import de.lavaclient.feature.coins.LavaCoins;
import de.lavaclient.feature.waypoints.WaypointManager;
import de.lavaclient.hud.HudManager;
import de.lavaclient.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;

public class LavaClientClient implements ClientModInitializer {

    private static LavaClientClient instance;
    private EventBus eventBus;
    private ModuleManager moduleManager;
    private HudManager hudManager;
    private ConfigManager configManager;
    private WaypointManager waypointManager;
    private ChallengeManager challengeManager;
    private LavaCoins lavaCoins;

    @Override
    public void onInitializeClient() {
        instance = this;
        LavaClient.LOGGER.info("[LavaClient] Starting client-side initialization...");
        this.eventBus         = new EventBus();
        this.lavaCoins        = new LavaCoins();
        this.configManager    = new ConfigManager();
        this.moduleManager    = new ModuleManager();
        this.hudManager       = new HudManager();
        this.waypointManager  = new WaypointManager();
        this.challengeManager = new ChallengeManager();
        this.moduleManager.init();
        this.hudManager.init();
        this.challengeManager.init();
        this.configManager.loadAll();
        LavaClient.LOGGER.info("[LavaClient] Ready! {} modules | {} coins | Keybind: RIGHT SHIFT",
                moduleManager.getModules().size(), lavaCoins.getCoins());
    }

    public static LavaClientClient getInstance() { return instance; }
    public EventBus getEventBus()                { return eventBus; }
    public ModuleManager getModuleManager()      { return moduleManager; }
    public HudManager getHudManager()            { return hudManager; }
    public ConfigManager getConfigManager()      { return configManager; }
    public WaypointManager getWaypointManager()  { return waypointManager; }
    public ChallengeManager getChallengeManager(){ return challengeManager; }
    public LavaCoins getLavaCoins()              { return lavaCoins; }
}
