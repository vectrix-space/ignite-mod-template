package com.example.mixin.plugins;

import com.example.ExampleConfig;
import com.example.ExampleInfo;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.configurate.CommentedConfigurationNode;
import space.vectrix.ignite.api.config.Configuration;
import space.vectrix.ignite.api.config.Configurations;

import java.util.List;
import java.util.Set;

public final class CorePlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(final @NonNull String mixinPackage) {
    }

    @Override
    public @Nullable String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(final @NonNull String targetClassName, final @NonNull String mixinClassName) {
        final Configuration<ExampleConfig, CommentedConfigurationNode> configWrapper = Configurations.getOrCreate(Configurations.HOCON_LOADER, ExampleInfo.getExampleConfig());
        final ExampleConfig config = configWrapper.instance();
        if (config != null) {
            return config.test();
        }

        return false;
    }

    @Override
    public void acceptTargets(final @NonNull Set<String> myTargets, final @NonNull Set<String> otherTargets) {
    }

    @Override
    public @Nullable List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(final @NonNull String targetClassName, final @NonNull ClassNode targetClass, final @NonNull String mixinClassName, final @NonNull IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(final @NonNull String targetClassName, final @NonNull ClassNode targetClass, final @NonNull String mixinClassName, final @NonNull IMixinInfo mixinInfo) {
    }
}
