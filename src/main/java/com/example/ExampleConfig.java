package com.example;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class ExampleConfig {
    @Setting(value = "test")
    @Comment(value = "Test configuration property.")
    private boolean test;

    @Setting(value = "container")
    @Comment(value = "A test container.")
    private TestContainer container;

    public boolean test() {
        return this.test;
    }

    public TestContainer container() {
        return this.container;
    }

    @ConfigSerializable
    public static class TestContainer {
        @Setting(value = "foo")
        @Comment(value = "A test boolean in a container.")
        private boolean foo;

        public boolean foo() {
            return this.foo;
        }
    }
}
