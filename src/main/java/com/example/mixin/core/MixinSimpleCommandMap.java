package com.example.mixin.core;

import com.example.command.HelloCommand;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SimpleCommandMap.class)
public abstract class MixinSimpleCommandMap {
    @Shadow public abstract boolean register(String fallbackPrefix, Command command);

    @Inject(method = "setDefaultCommands()V", at = @At("TAIL"), remap = false)
    public void registerOwnCommands(CallbackInfo callback) {
        this.register("example", new HelloCommand("hello"));
    }
}
