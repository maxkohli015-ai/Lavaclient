@echo off
echo [Lava Client Setup]
echo.
if not exist "gradle\wrapper\gradle-wrapper.jar" (
    echo Downloading Gradle Wrapper...
    gradle wrapper --gradle-version 8.8
)
echo Building Lava Client...
gradlew.bat build
echo.
echo Done! JAR is in: build\libs\lava-client-1.0.0.jar
