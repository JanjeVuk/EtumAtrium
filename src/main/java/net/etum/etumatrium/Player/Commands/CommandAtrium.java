package net.etum.etumatrium.Player.Commands;

import net.etum.etumatrium.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandAtrium implements CommandExecutor, TabCompleter {

    private final Main plugin;

    public CommandAtrium(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length == 0) return false;
        switch (args[0]) {
            case "reload":
                plugin.reloadConfig();
                sender.sendMessage("Configuration Reloaded!");
                return true;
            case "motd":
                return handleMotdCommand(args, sender);
            default:
                sender.sendMessage("Invalid command!");
                return false;
        }
    }

    private boolean handleMotdCommand(String[] args, CommandSender sender) {
        if (args.length >= 3) {
            FileConfiguration config = plugin.getConfig();
            List<String> motd = config.getStringList("MODULE.MOTD.message");
            // Récupérer la ligne à modifier
            int lineNumber = getLineNumberAndValidate(args[1], motd.size(), sender);
            if (lineNumber == -1)
                return false;
            // Créer le nouveau MOTD en utilisant le reste des arguments
            String newMotd = generateNewMotd(args);
            // Appliquer le nouveau MOTD à la ligne spécifiée
            motd.set(lineNumber, newMotd);
            // Mettre à jour la configuration et sauvegarder
            config.set("MODULE.MOTD.message", motd);
            plugin.saveConfig();
            sender.sendMessage("MOTD Updated!");
            return true;
        } else {
            sender.sendMessage("Not enough arguments for motd command!");
            return false;
        }
    }

    private int getLineNumberAndValidate(String arg, int size, CommandSender sender) {
        try {
            int lineNumber = Integer.parseInt(arg) - 1;
            if (lineNumber < 0 || lineNumber >= size) {
                sender.sendMessage("Invalid line number!");
                return -1;
            }
            return lineNumber;
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid line number!");
            return -1;
        }
    }

    private String generateNewMotd(String[] args) {
        StringBuilder newMotd = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            newMotd.append(args[i]).append(" ");
        }
        return newMotd.toString().trim();
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // Tab completion for the first argument (commands)
            completions.add("reload");
            completions.add("motd");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("motd")) {
            // Tab completion for the second argument (line number)
            completions.add("1");
            completions.add("2");
            // Add more if needed
        }

        return completions;
    }
}
