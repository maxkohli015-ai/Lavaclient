package de.lavaclient.config;

import java.util.function.Consumer;

public class Setting<T> {

    private final String name;
    private final String description;
    private T value;
    private final T defaultValue;
    private T min, max;
    private Consumer<T> onChange;

    public Setting(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public Setting<T> range(T min, T max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public Setting<T> onChange(Consumer<T> onChange) {
        this.onChange = onChange;
        return this;
    }

    public void setValue(T value) {
        this.value = value;
        if (onChange != null) onChange.accept(value);
    }

    public T getValue() { return value; }
    public T getDefaultValue() { return defaultValue; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public T getMin() { return min; }
    public T getMax() { return max; }
    public boolean hasRange() { return min != null && max != null; }
}
