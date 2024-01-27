package net.etum.etumatrium.akia.player.Commands;

import net.etum.etumatrium.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandSystem implements CommandExecutor, TabCompleter {

    /**
     * Reference to the main plugin class.
     */
    private final Main plugin;

    /**
     * Represents a command system for executing and tab completing commands.
     */
    public CommandSystem(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the command when it is triggered.
     *
     * @param sender the command sender
     * @param cmd the command being executed
     * @param label the alias used for the command
     * @param args the arguments provided for the command
     * @return true if the command executed successfully, false otherwise
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        // Vérifier si l'émetteur est un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande est uniquement utilisable par les joueurs.");
            return false;
        }

        // Vérifier les permissions
        if (!sender.hasPermission("commandsystem.use")) {
            sender.sendMessage("Vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }

        // Vérifier si des arguments sont fournis
        if (args.length == 0) {
            sender.sendMessage("Utilisation: /commandsystem <reload>");
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {// Vérifier les permissions pour recharger la configuration
            plugin.reloadConfig();
            sender.sendMessage("Configuration rechargée !");
            return true;
        }
        sender.sendMessage("Commande invalide !");
        return false;

    }

    /**
     * Provides tab completion for the onCommand method in the CommandSystem class.
     *
     * @param sender the command sender
     * @param cmd the command being executed
     * @param alias the alias used for the command
     * @param args the arguments provided for the command
     * @return a list of tab completions for the command
     */
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        // Tabulation pour le premier argument (commandes)
        if (args.length == 1) {
            completions.add("reload");
        }

        // Trie les suggestions
        Collections.sort(completions);

        return completions;
    }
}
