package com.example;

import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.CommentedConfigurationNode;
import space.vectrix.ignite.api.Platform;
import space.vectrix.ignite.api.config.Configuration;
import space.vectrix.ignite.api.config.Configurations;
import space.vectrix.ignite.api.event.Subscribe;
import space.vectrix.ignite.api.event.platform.PlatformInitializeEvent;

public final class ExampleMod {
    private final Logger logger;
    private final Platform platform;

    @Inject
    public ExampleMod(final Logger logger,
                      final Platform platform) {
        this.logger = logger;
        this.platform = platform;
    }

    @Subscribe
    public void onInitialize(final @NonNull PlatformInitializeEvent event) {
        this.logger.info("Hello Example!");

        final Configuration<ExampleConfig, CommentedConfigurationNode> configWrapper = Configurations.getOrCreate(Configurations.HOCON_LOADER, ExampleInfo.getExampleConfig());
        final ExampleConfig config = configWrapper.instance();
        if (config != null) {
            this.logger.info("Foo is set to: " + (config.container().foo() ? "Enabled" : "Disabled"));
        }
    }
}
