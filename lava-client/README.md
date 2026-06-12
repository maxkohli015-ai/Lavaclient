# 🔥 Lava Client – Fabric Mod for Minecraft 1.21.5

Ein vollständiger Minecraft Client-Mod mit Lava-Ästhetik, Mod-Menü, HUD und vielen Features.

---

## 📦 Schritt-für-Schritt: .jar bauen

### Voraussetzungen
- **Java 21** installiert → https://adoptium.net (Temurin 21)
- **Git** (optional, zum Klonen)

### 1. Java prüfen
```
java -version
```
→ Muss `21.x.x` ausgeben.

### 2. Projekt öffnen
Entpacke das ZIP in einen Ordner, z.B. `C:\lava-client\`

### 3. Bauen (Windows)
```
cd C:\lava-client
gradlew.bat build
```

### 3. Bauen (Linux / macOS)
```
cd ~/lava-client
chmod +x gradlew
./gradlew build
```

**Beim ersten Mal lädt Gradle automatisch:**
- Sich selbst (~100 MB)
- Fabric Loom
- Minecraft Mappings
- Fabric API

Das dauert 5–15 Minuten je nach Internetgeschwindigkeit.

### 4. JAR finden
Nach erfolgreichem Build liegt die fertige Mod-Datei hier:
```
build/libs/lava-client-1.0.0.jar
```

Die `-sources.jar` ist nur für Entwickler, **nicht** die Mod selbst.

---

## 🚀 Mod installieren

1. **Fabric Installer** downloaden → https://fabricmc.net/use/installer/
2. Fabric für Minecraft **1.21.5** installieren
3. **Fabric API** in den `mods`-Ordner legen → https://modrinth.com/mod/fabric-api
4. `lava-client-1.0.0.jar` in den `mods`-Ordner legen
   - Windows: `%appdata%\.minecraft\mods\`
   - Linux: `~/.minecraft/mods/`
   - macOS: `~/Library/Application Support/minecraft/mods/`
5. Minecraft starten mit dem Fabric-Profil

---

## 🎮 Bedienung

| Taste | Aktion |
|-------|--------|
| **Right Shift** | Mod-Menü öffnen/schließen |
| Klick auf Modul | Aktivieren/Deaktivieren |
| Klick auf ▶ | Modul-Einstellungen öffnen |
| Suchbox | Module suchen |
| Drag Header | Fenster verschieben |
| Scroll | Modulliste scrollen |

---

## 📁 Projektstruktur

```
lava-client/
├── build.gradle              ← Build-Konfiguration
├── gradle.properties         ← Versionen (Minecraft, Fabric, etc.)
├── settings.gradle
├── gradlew / gradlew.bat     ← Gradle Wrapper
├── src/
│   ├── main/java/de/lavaclient/
│   │   └── LavaClient.java   ← Mod-Einstiegspunkt
│   ├── main/resources/
│   │   ├── fabric.mod.json   ← Mod-Metadaten
│   │   └── lavaclient.mixins.json
│   └── client/java/de/lavaclient/
│       ├── LavaClientClient.java   ← Client-Einstiegspunkt
│       ├── module/                 ← Alle Module
│       │   ├── Module.java         ← Basis-Klasse
│       │   ├── ModuleManager.java
│       │   ├── combat/
│       │   ├── movement/
│       │   ├── render/
│       │   ├── player/
│       │   ├── world/
│       │   └── misc/
│       ├── hud/                    ← HUD System
│       │   ├── HudElement.java
│       │   ├── HudManager.java
│       │   └── elements/           ← FPS, CPS, Ping, Coords, Armor, Keystrokes, Stats
│       ├── gui/
│       │   └── ModMenuScreen.java  ← Vollständiges Mod-Menü
│       ├── config/
│       │   ├── Setting.java
│       │   └── ConfigManager.java  ← JSON Config-System
│       ├── feature/
│       │   ├── waypoints/          ← Waypoint-System
│       │   ├── coins/              ← Lava Coins (lokal)
│       │   └── challenges/         ← Daily Challenges
│       ├── util/
│       │   ├── LavaTheme.java      ← Farb-System
│       │   └── RenderUtil.java
│       ├── event/
│       │   ├── EventBus.java
│       │   └── Events.java
│       └── mixin/                  ← Minecraft-Hooks
```

---

## ⚙️ Module-Übersicht

### Combat
- **HitColor** – Ändert die Farbe des Trefferblitzes
- **ReachDisplay** – Zeigt die Angriffsreichweite im HUD
- **AutoClicker** – Automatisches Klicken zum Abbauen

### Movement
- **ToggleSprint** – Dauerrennen ohne Taste halten
- **ToggleSneak** – Dauerschleichen ohne Taste halten
- **Zoom** – Einzoomen wie Fernglas (C-Taste)
- **Fly** – Kreativ-Fliegen (nur Singleplayer)
- **Speed** – Erhöhte Bewegungsgeschwindigkeit
- **NoFall** – Kein Fallschaden (Singleplayer)

### Render
- **Fullbright** – Alles vollständig beleuchtet
- **MotionBlur** – Bewegungsunschärfe-Effekt
- **CustomCrosshair** – Eigenes Fadenkreuz mit Farb-Einstellungen
- **WeatherChanger** – Wetter client-seitig ändern
- **TimeChanger** – Tageszeit client-seitig ändern
- **ESP** – Farbige Umrisse um Entities

### Player
- **AutoGG** – Automatisch "gg" nach Spielende
- **AutoText** – Automatische Antworten auf Keywords
- **NoHurtCam** – Kein Kamera-Wackeln bei Schaden
- **FreeCam** – Kamera frei bewegen

### World
- **Waypoints** – Wegpunkte speichern und anzeigen
- **ScreenshotManager** – Erweiterter Screenshot-Manager
- **XRay** – Erze durch Wände sehen (Singleplayer)
- **BuildIdeas** – Build-Ideen Browser

### Misc
- **FPSBooster** – FPS-Optimierungen
- **DynamicFPS** – FPS-Limit im Hintergrund
- **EntityCulling** – Nicht-sichtbare Entities überspringen
- **ParticleOptimizer** – Partikel-Limit
- **ServerInfo** – Server-Informationen anzeigen

---

## 🔥 Lava Client Features

### HUD-Elemente
- FPS-Anzeige (grün/orange/rot je nach FPS)
- CPS-Anzeige
- Ping-Anzeige
- Koordinaten (X/Y/Z)
- Armor HUD
- Keystrokes (WASD + Leertaste)
- Session Stats (Kills/Tode/Zeit)

### Lava Coins System
- Lokales Währungssystem
- Verdiene Coins durch Daily Challenges
- Gespeichert in `config/lavaclient/coins.json`

### Daily Challenges
- Täglich neue Aufgaben
- Automatisches Reset um Mitternacht
- Coins als Belohnung
- Fortschritts-Tracking

---

## 🛠️ Für Modrinth/CurseForge

Nach dem Build einfach `build/libs/lava-client-1.0.0.jar` hochladen.

Die `fabric.mod.json` enthält bereits alle nötigen Metadaten für beide Plattformen.

---

## 📄 Lizenz
MIT License – Frei verwendbar und erweiterbar.
