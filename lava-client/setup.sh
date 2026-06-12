#!/bin/sh
echo "[Lava Client Setup]"
if [ ! -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "Downloading Gradle Wrapper..."
    gradle wrapper --gradle-version 8.8
fi
chmod +x gradlew
./gradlew build
echo "Done! JAR: build/libs/lava-client-1.0.0.jar"
