package com.example;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import space.vectrix.ignite.api.Blackboard;
import space.vectrix.ignite.api.config.Configuration;

import java.nio.file.Path;

public final class ExampleInfo {
    private static final @MonotonicNonNull Path CONFIGS_PATH = Blackboard.getProperty(Blackboard.CONFIG_DIRECTORY_PATH);

    private static @MonotonicNonNull Path EXAMPLE_PATH;
    private static Configuration.@MonotonicNonNull Key<ExampleConfig> EXAMPLE_CONFIG;

    public static @MonotonicNonNull Path getExamplePath() {
        if (ExampleInfo.EXAMPLE_PATH != null) return ExampleInfo.EXAMPLE_PATH;
        if (ExampleInfo.CONFIGS_PATH == null) return null;

        return ExampleInfo.EXAMPLE_PATH = ExampleInfo.CONFIGS_PATH.resolve("example");
    }

    public static Configuration.@NonNull Key<ExampleConfig> getExampleConfig() {
        if (ExampleInfo.EXAMPLE_CONFIG != null) return ExampleInfo.EXAMPLE_CONFIG;

        final Path examplePath = ExampleInfo.getExamplePath();
        if (examplePath == null) throw new IllegalStateException("Unable to locate example path.");

        return ExampleInfo.EXAMPLE_CONFIG = Configuration.key(ExampleConfig.class, examplePath.resolve("example.conf"));
    }
}
