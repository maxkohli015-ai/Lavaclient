package de.lavaclient.feature.coins;

import com.google.gson.*;
import de.lavaclient.LavaClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.*;

public class LavaCoins {

    private static LavaCoins instance;
    private int coins = 0;
    private int totalEarned = 0;
    private final Path file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LavaCoins() {
        instance = this;
        file = FabricLoader.getInstance().getConfigDir().resolve("lavaclient/coins.json");
        load();
    }

    public static LavaCoins get() { return instance; }

    public void add(int amount) {
        coins += amount;
        totalEarned += amount;
        save();
        LavaClient.LOGGER.info("[LavaCoins] +{} coins | Total: {}", amount, coins);
    }

    public boolean spend(int amount) {
        if (coins < amount) return false;
        coins -= amount;
        save();
        return true;
    }

    public int getCoins()       { return coins; }
    public int getTotalEarned() { return totalEarned; }

    private void save() {
        JsonObject o = new JsonObject();
        o.addProperty("coins", coins);
        o.addProperty("totalEarned", totalEarned);
        try (Writer w = new FileWriter(file.toFile())) { gson.toJson(o, w); }
        catch (IOException e) { LavaClient.LOGGER.error("Coins save failed", e); }
    }

    private void load() {
        if (!Files.exists(file)) return;
        try (Reader r = new FileReader(file.toFile())) {
            JsonObject o = gson.fromJson(r, JsonObject.class);
            if (o == null) return;
            coins = o.has("coins") ? o.get("coins").getAsInt() : 0;
            totalEarned = o.has("totalEarned") ? o.get("totalEarned").getAsInt() : 0;
        } catch (IOException e) { LavaClient.LOGGER.error("Coins load failed", e); }
    }
}
