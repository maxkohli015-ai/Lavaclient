package de.lavaclient.config;

import com.google.gson.*;
import de.lavaclient.LavaClient;
import de.lavaclient.LavaClientClient;
import de.lavaclient.module.Module;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    private final Path configDir;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {
        configDir = FabricLoader.getInstance().getConfigDir().resolve("lavaclient");
        try { Files.createDirectories(configDir); } catch (IOException ignored) {}
    }

    public void saveAll() {
        JsonObject root = new JsonObject();
        JsonArray modulesArr = new JsonArray();
        for (Module mod : LavaClientClient.getInstance().getModuleManager().getModules()) {
            JsonObject mObj = new JsonObject();
            mObj.addProperty("name",    mod.getName());
            mObj.addProperty("enabled", mod.isEnabled());
            mObj.addProperty("keybind", mod.getKeybind());
            JsonArray settingsArr = new JsonArray();
            for (Setting<?> s : mod.getSettings()) {
                JsonObject sObj = new JsonObject();
                sObj.addProperty("name",  s.getName());
                sObj.addProperty("value", String.valueOf(s.getValue()));
                settingsArr.add(sObj);
            }
            mObj.add("settings", settingsArr);
            modulesArr.add(mObj);
        }
        root.add("modules", modulesArr);
        try (Writer w = new FileWriter(configDir.resolve("config.json").toFile())) {
            gson.toJson(root, w);
        } catch (IOException e) {
            LavaClient.LOGGER.error("[LavaClient] Failed to save config: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadAll() {
        Path file = configDir.resolve("config.json");
        if (!Files.exists(file)) return;
        try (Reader r = new FileReader(file.toFile())) {
            JsonObject root = gson.fromJson(r, JsonObject.class);
            if (root == null || !root.has("modules")) return;
            for (JsonElement el : root.getAsJsonArray("modules")) {
                JsonObject mObj = el.getAsJsonObject();
                String name = mObj.get("name").getAsString();
                LavaClientClient.getInstance().getModuleManager().getByName(name).ifPresent(mod -> {
                    mod.setEnabled(mObj.get("enabled").getAsBoolean());
                    if (mObj.has("keybind")) mod.setKeybind(mObj.get("keybind").getAsInt());
                    if (mObj.has("settings")) {
                        for (JsonElement se : mObj.getAsJsonArray("settings")) {
                            JsonObject sObj = se.getAsJsonObject();
                            String sName = sObj.get("name").getAsString();
                            String sVal  = sObj.get("value").getAsString();
                            mod.getSettings().stream()
                               .filter(s -> s.getName().equals(sName))
                               .findFirst().ifPresent(s -> applySetting(s, sVal));
                        }
                    }
                });
            }
        } catch (IOException e) {
            LavaClient.LOGGER.error("[LavaClient] Failed to load config: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void applySetting(Setting<?> setting, String value) {
        try {
            Object def = setting.getDefaultValue();
            if (def instanceof Boolean)  ((Setting<Boolean>) setting).setValue(Boolean.parseBoolean(value));
            else if (def instanceof Integer) ((Setting<Integer>) setting).setValue(Integer.parseInt(value));
            else if (def instanceof Float)   ((Setting<Float>)   setting).setValue(Float.parseFloat(value));
            else if (def instanceof String)  ((Setting<String>)  setting).setValue(value);
        } catch (Exception ignored) {}
    }
}
