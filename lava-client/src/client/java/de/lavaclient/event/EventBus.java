package de.lavaclient.event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class EventBus {

    private final Map<Class<?>, List<Consumer<Object>>> listeners = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void subscribe(Class<T> eventClass, Consumer<T> listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>())
                 .add((Consumer<Object>) listener);
    }

    public <T> void post(T event) {
        List<Consumer<Object>> list = listeners.get(event.getClass());
        if (list != null) {
            for (Consumer<Object> listener : list) {
                listener.accept(event);
            }
        }
    }
}
