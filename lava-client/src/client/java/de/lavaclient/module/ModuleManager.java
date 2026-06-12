package de.lavaclient.module;

import de.lavaclient.module.combat.*;
import de.lavaclient.module.movement.*;
import de.lavaclient.module.render.*;
import de.lavaclient.module.player.*;
import de.lavaclient.module.world.*;
import de.lavaclient.module.misc.*;

import java.util.*;
import java.util.stream.Collectors;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    public void init() {
        // Combat
        register(new HitColor());
        register(new Reach());
        register(new AutoClicker());

        // Movement
        register(new Sprint());
        register(new Sneak());
        register(new Fly());
        register(new Speed());
        register(new Zoom());
        register(new NoFall());

        // Render
        register(new Fullbright());
        register(new MotionBlur());
        register(new CustomCrosshair());
        register(new WeatherChanger());
        register(new TimeChanger());
        register(new ESP());

        // Player
        register(new AutoGG());
        register(new AutoText());
        register(new NoHurtCam());
        register(new FreeCam());

        // World
        register(new Waypoints());
        register(new ScreenshotManager());
        register(new XRay());
        register(new BuildIdeas());

        // Misc
        register(new FPSBooster());
        register(new DynamicFPS());
        register(new EntityCulling());
        register(new ParticleOptimizer());
        register(new ServerInfo());
    }

    private void register(Module module) { modules.add(module); }

    public List<Module> getModules() { return modules; }

    public List<Module> getByCategory(Module.Category category) {
        return modules.stream().filter(m -> m.getCategory() == category).collect(Collectors.toList());
    }

    public Optional<Module> getByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Module> search(String query) {
        if (query == null || query.isEmpty()) return modules;
        String lower = query.toLowerCase();
        return modules.stream()
                .filter(m -> m.getName().toLowerCase().contains(lower)
                        || m.getDescription().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public void onTick()             { modules.stream().filter(Module::isEnabled).forEach(Module::onTick); }
    public void onRender(float delta){ modules.stream().filter(Module::isEnabled).forEach(m -> m.onRender(delta)); }
}
