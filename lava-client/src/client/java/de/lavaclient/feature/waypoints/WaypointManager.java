package de.lavaclient.feature.waypoints;

import com.google.gson.*;
import de.lavaclient.LavaClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class WaypointManager {

    private final List<Waypoint> waypoints = new ArrayList<>();
    private final Path file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public WaypointManager() {
        file = FabricLoader.getInstance().getConfigDir().resolve("lavaclient/waypoints.json");
    }

    public void addWaypoint(String name, int x, int y, int z, int color) {
        waypoints.add(new Waypoint(name, x, y, z, color));
        save();
    }

    public void removeWaypoint(Waypoint wp) { waypoints.remove(wp); save(); }
    public List<Waypoint> getWaypoints()    { return waypoints; }

    public void save() {
        try (Writer w = new FileWriter(file.toFile())) {
            gson.toJson(waypoints, w);
        } catch (IOException e) { LavaClient.LOGGER.error("Waypoints save failed", e); }
    }

    public void load() {
        if (!Files.exists(file)) return;
        try (Reader r = new FileReader(file.toFile())) {
            JsonArray arr = gson.fromJson(r, JsonArray.class);
            if (arr == null) return;
            for (JsonElement el : arr) {
                JsonObject o = el.getAsJsonObject();
                waypoints.add(new Waypoint(
                    o.get("name").getAsString(),
                    o.get("x").getAsInt(), o.get("y").getAsInt(), o.get("z").getAsInt(),
                    o.get("color").getAsInt()
                ));
            }
        } catch (IOException e) { LavaClient.LOGGER.error("Waypoints load failed", e); }
    }
}
