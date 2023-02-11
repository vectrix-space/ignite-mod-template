package com.example.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class HelloCommand extends BukkitCommand {
    public HelloCommand(final @NonNull String name) {
        super(name);

        this.setPermission("example.hello");
    }

    @Override
    public boolean execute(final @NonNull CommandSender commandSender, final @NonNull String currentAlias, final @NonNull String[] args) {
        if (!this.testPermission(commandSender)) {
            return true;
        } else {
            commandSender.sendMessage("Hello " + commandSender.getName());
        }

        return false;
    }
}
