package de.lavaclient.feature.challenges;

import com.google.gson.*;
import de.lavaclient.LavaClient;
import de.lavaclient.feature.coins.LavaCoins;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {

    public static class Challenge {
        public String id, name, description;
        public int reward;
        public int progress, goal;
        public boolean completed;
        public Challenge(String id, String name, String desc, int goal, int reward) {
            this.id = id; this.name = name; this.description = desc;
            this.goal = goal; this.reward = reward;
        }
    }

    private final List<Challenge> dailies = new ArrayList<>();
    private String lastDate = "";
    private final Path file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ChallengeManager() {
        file = FabricLoader.getInstance().getConfigDir().resolve("lavaclient/challenges.json");
    }

    public void init() {
        load();
        String today = LocalDate.now().toString();
        if (!today.equals(lastDate)) {
            resetDailies(today);
        }
    }

    private void resetDailies(String date) {
        lastDate = date;
        dailies.clear();
        dailies.add(new Challenge("mine_50",  "Miner",        "Mine 50 blocks",          50,  10));
        dailies.add(new Challenge("play_30",  "Session",      "Play for 30 minutes",     30,  15));
        dailies.add(new Challenge("walk_500", "Explorer",     "Walk 500 blocks",         500, 20));
        dailies.add(new Challenge("craft_5",  "Crafter",      "Craft 5 items",           5,   10));
        dailies.add(new Challenge("kill_10",  "Hunter",       "Kill 10 mobs",            10,  25));
        save();
    }

    public void progress(String id, int amount) {
        for (Challenge c : dailies) {
            if (c.id.equals(id) && !c.completed) {
                c.progress = Math.min(c.goal, c.progress + amount);
                if (c.progress >= c.goal) {
                    c.completed = true;
                    LavaCoins.get().add(c.reward);
                    LavaClient.LOGGER.info("[Challenges] Completed '{}' +{} coins", c.name, c.reward);
                }
                save();
                break;
            }
        }
    }

    public List<Challenge> getDailies() { return dailies; }

    private void save() {
        JsonObject root = new JsonObject();
        root.addProperty("lastDate", lastDate);
        JsonArray arr = new JsonArray();
        for (Challenge c : dailies) {
            JsonObject o = new JsonObject();
            o.addProperty("id", c.id); o.addProperty("name", c.name);
            o.addProperty("description", c.description);
            o.addProperty("progress", c.progress); o.addProperty("goal", c.goal);
            o.addProperty("reward", c.reward); o.addProperty("completed", c.completed);
            arr.add(o);
        }
        root.add("dailies", arr);
        try (Writer w = new FileWriter(file.toFile())) { gson.toJson(root, w); }
        catch (IOException e) { LavaClient.LOGGER.error("Challenges save failed", e); }
    }

    private void load() {
        if (!Files.exists(file)) return;
        try (Reader r = new FileReader(file.toFile())) {
            JsonObject root = gson.fromJson(r, JsonObject.class);
            if (root == null) return;
            lastDate = root.has("lastDate") ? root.get("lastDate").getAsString() : "";
            if (root.has("dailies")) {
                for (JsonElement el : root.getAsJsonArray("dailies")) {
                    JsonObject o = el.getAsJsonObject();
                    Challenge c = new Challenge(
                        o.get("id").getAsString(), o.get("name").getAsString(),
                        o.get("description").getAsString(),
                        o.get("goal").getAsInt(), o.get("reward").getAsInt()
                    );
                    c.progress = o.get("progress").getAsInt();
                    c.completed = o.get("completed").getAsBoolean();
                    dailies.add(c);
                }
            }
        } catch (IOException e) { LavaClient.LOGGER.error("Challenges load failed", e); }
    }
}
