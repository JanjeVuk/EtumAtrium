package net.etum.etumatrium.AKIA.Player.Commands;

import net.etum.etumatrium.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a command executor and tab completer for the '/gamemode' command.
 * This command allows players to change their game mode.
 *
 * @see CommandExecutor
 * @see TabCompleter
 */
public class CommandGamemode implements CommandExecutor, TabCompleter {

    /**
     * The plugin instance.
     */
    private final Main plugin;
    /**
     * Usage message for the /gamemode command.
     * It provides information on how to use the command to change game modes.
     * The message is formatted using ChatColor to enhance visibility.
     */
    private static final String USAGE_MESSAGE = ChatColor.YELLOW + "Usage: " + ChatColor.GRAY + "/gamemode <mode> [joueur]"
            + ChatColor.WHITE + " - Changez votre mode de jeu (survival, creative, adventure, spectator) ou celui d'un joueur.";
    /**
     * This variable represents the message displayed when a user does not have permission to use a command.
     * It is a constant string and its value is "Vous n'avez pas la permission d'utiliser cette commande.".
     */
    private static final String NO_PERMISSION_MESSAGE = ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.";
    /**
     *
     */
    private static final String INVALID_PLAYER_MESSAGE = ChatColor.RED + "Le joueur spécifié n'est pas en ligne ou n'existe pas.";
    /**
     * Error message displayed when an invalid game mode is provided.
     */
    private static final String GAME_MODE_ERROR_MESSAGE = ChatColor.RED + "Mode de jeu invalide. Utilisez le nom du mode (survival, creative, adventure, spectator) ou le chiffre associé.";
    /**
     * The permission required to use the gamemode command.
     * Can be used to check if a command sender has the necessary permission.
     */
    private static final String GAME_MODE_USE_PERMISSION = "gamemode.use";

    /**
     * Represents a command executor for the 'gamemode' command.
     * This command allows players to change their game mode.
     *
     * @param main The instance of the main class.
     */
    public CommandGamemode(Main main) {
        this.plugin = main;
    }

    /**
     * Executes the onCommand method for the provided command.
     * This method allows players to change their game mode.
     *
     * @param sender The CommandSender that executed the command.
     * @param command The Command that was executed.
     * @param s The alias of the command.
     * @param args The arguments provided with the command.
     * @return {@code true} if the command executed successfully, {@code false} otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!sender.hasPermission(GAME_MODE_USE_PERMISSION)){
            sender.sendMessage(NO_PERMISSION_MESSAGE);
            return false;
        }

        if (!isValidArguments(args)) {
            sender.sendMessage(USAGE_MESSAGE);
            return false;
        }

        Player targetPlayer = (args.length == 2) ? Bukkit.getPlayer(args[1]) : (sender instanceof Player) ? (Player) sender : null;

        if (targetPlayer == null && args.length != 1) {
            sender.sendMessage(INVALID_PLAYER_MESSAGE);
            return false;
        }

        GameMode gameMode = getGameModeFromInput(args[0]);

        if (gameMode != null) {
            if (targetPlayer == null) {
                handleConsoleUser(sender, gameMode);
            } else {
                setPlayerGameMode(targetPlayer, gameMode);
                if (sender != targetPlayer) {
                    sender.sendMessage(ChatColor.GREEN + "Le mode de jeu de " + ChatColor.YELLOW + targetPlayer.getName() +
                            ChatColor.GREEN + " a été changé en " + ChatColor.YELLOW + gameMode.name().toLowerCase());
                }
            }
            return true;
        }

        sender.sendMessage(GAME_MODE_ERROR_MESSAGE);
        return false;
    }

    /**
     * Checks if the given arguments are valid.
     *
     * @param args the array of arguments to check.
     * @return true if the arguments are valid, otherwise false.
     */
    private boolean isValidArguments(String[] args) {
        return args.length >= 1 && args.length <= 2;
    }

    /**
     * Handles the command input from the console user.
     *
     * @param sender the command sender
     * @param gameMode the game mode to be set
     */
    private void handleConsoleUser(CommandSender sender, GameMode gameMode) {
        if (sender instanceof Player) {
            setPlayerGameMode((Player) sender, gameMode);
        } else {
            sender.sendMessage(ChatColor.RED + "Vous devez spécifier un joueur lorsque vous utilisez cette commande depuis la console.");
        }
    }

    /**
     * Retrieves the corresponding {@link GameMode} based on the provided input.
     *
     * @param input The input to match against a {@link GameMode}.
     * @return The corresponding {@link GameMode} if found, otherwise {@code null}.
     */
    private GameMode getGameModeFromInput(String input) {
        return getGameModeByName(input).or(() -> getGameModeByValue(input)).orElse(null);
    }

    /**
     * Sets the game mode of a player and sends a message indicating the mode change.
     *
     * @param player    The player whose game mode will be changed.
     * @param gameMode  The new game mode to set for the player.
     */
    private void setPlayerGameMode(Player player, GameMode gameMode) {
        player.setGameMode(gameMode);
        player.sendMessage(ChatColor.GREEN + "Votre mode de jeu a été changé en " + ChatColor.YELLOW + gameMode.name().toLowerCase());
    }

    /**
     * Tab complete method for the onTabComplete command.
     *
     * @param sender the command sender
     * @param command the command
     * @param alias the command alias
     * @param args the command arguments
     * @return a list of tab completions for the command
     */
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.stream(GameMode.values())
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } else if (args.length == 2 && sender.hasPermission("gamemode.others")) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Retrieves the {@link GameMode} based on the given name.
     *
     * @param name the name of the game mode
     * @return an {@link Optional} containing the requested game mode if found, otherwise an empty {@link Optional}
     */
    private Optional<GameMode> getGameModeByName(String name) {
        try {
            return Optional.of(GameMode.valueOf(name.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * Retrieves the GameMode enum value by its corresponding integer value.
     *
     * @param value the integer value representing the desired GameMode enum
     * @return an Optional containing the GameMode enum value if found, or empty if not found
     */
    private Optional<GameMode> getGameModeByValue(String value) {
        try {
            int modeValue = Integer.parseInt(value);
            return Arrays.stream(GameMode.values()).filter(mode -> mode.getValue() == modeValue).findFirst();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
