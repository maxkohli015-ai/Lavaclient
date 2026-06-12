# 🔥 Lava Client – .JAR BAUEN (Einfache Anleitung)

## Was du brauchst: NUR Java 21

Download: https://adoptium.net/de/temurin/releases/?version=21
→ Windows → .msi Installer → installieren → fertig

---

## Schritt 1 – Java prüfen

Öffne CMD (Windows-Taste → "cmd" tippen → Enter):
```
java -version
```
Muss zeigen: `openjdk version "21..."`

---

## Schritt 2 – ZIP entpacken

Das ZIP irgendwo entpacken, z.B.:
```
C:\lava-client\
```

---

## Schritt 3 – .jar bauen

In CMD:
```
cd C:\lava-client
gradlew.bat build
```

**Oder** im Explorer: Rechtsklick auf den Ordner → "In Terminal öffnen" → `gradlew.bat build` eingeben.

⏳ Beim ersten Mal lädt es ~200 MB herunter (Minecraft Mappings etc.) – normal, einmalig!

Wenn es fertig ist steht da:
```
BUILD SUCCESSFUL in Xs
```

---

## Schritt 4 – JAR finden

Die fertige Mod liegt jetzt hier:
```
C:\lava-client\build\libs\lava-client-1.0.0.jar
```

---

## Schritt 5 – Mod installieren

### Fabric installieren (einmalig)
1. https://fabricmc.net/use/installer/ → Download
2. Installer öffnen → Version `1.21.5` wählen → Install
3. Im Minecraft Launcher: Profil "fabric-loader-..." auswählen

### Fabric API (Pflicht!)
1. https://modrinth.com/mod/fabric-api → Download für 1.21.5
2. In `%appdata%\.minecraft\mods\` legen

### Lava Client
1. `lava-client-1.0.0.jar` auch in `%appdata%\.minecraft\mods\` legen
2. Minecraft starten mit Fabric-Profil
3. **RIGHT SHIFT** drücken → Mod-Menü öffnet sich! 🔥

---

## ❌ Fehler beim Bauen?

**"Java not found"** → Java 21 nicht installiert oder PATH nicht gesetzt
→ Nach der Installation neu starten

**"Could not resolve..."** → Kein Internet oder Firewall blockiert
→ Internetverbindung prüfen, ggf. VPN aus

**"Build failed"** → Fehlermeldung kopieren und nachschauen
→ Meistens ein Mixin-Fehler, nichts Schlimmes

---

## 💡 Tipp: IntelliJ IDEA

Für Entwickler: Öffne den Ordner in IntelliJ IDEA → es erkennt automatisch Gradle → einfach "Build" klicken.
