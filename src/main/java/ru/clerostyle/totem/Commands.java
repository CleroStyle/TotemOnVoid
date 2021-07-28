package ru.clerostyle.totem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private Main plugin;

    public Commands(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg1, String[] args) {
        if (sender.hasPermission("totemonvoid") || sender.isOp()) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                return true;
            }
        }
        return false;
    }
    
}
