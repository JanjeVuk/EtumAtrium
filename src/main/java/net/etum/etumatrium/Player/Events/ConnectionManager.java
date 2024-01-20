package net.etum.etumatrium.Player.Events;

import net.etum.etumatrium.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class ConnectionManager implements Listener {

    /**
     * Represents the join message key for the ConnectionManager module.
     * This key is used to fetch the join message from the configuration file.
     */
    private static final String JOIN_MSG_KEY = "Module.ConnectionManager.message.join";
    /**
     * Represents the key for the quit message in the ConnectionManager module.
     */
    private static final String QUIT_MSG_KEY = "Module.ConnectionManager.message.quit";
    /**
     * Represents a welcome message string.
     *
     * The value of this variable is obtained from the configuration file and is used to display a welcome
     * message to players when they join the server for the first time. The message is customizable and
     * can contain replacement placeholders such as '%player%' to dynamically insert the player's name.
     *
     * The value of this variable is obtained by calling the 'getWelcomeMsg()' method, which retrieves the
     * welcome message from the configuration file and returns it as a string. The message is stored in
     * 'WELCOME_MSG' as a private static final variable.
     *
     * @see ConnectionManager
     * @see ConnectionManager#getWelcomeMsg()
     * @see ConnectionManager#applyPlayerEventMessage(PlayerEvent, String)
     */
    private static final String WELCOME_MSG = getWelcomeMsg();

    /**
     * Retrieves the welcome message from the plugin configuration.
     *
     * @return the welcome message
     */
    private static String getWelcomeMsg() {
        FileConfiguration config = Main.getInstance().getConfig();
        return String.join(System.lineSeparator(), config.getStringList("Module.ConnectionManager.message.welcome"));
    }

    /**
     * Handles the player join event.
     *
     * @param event The PlayerJoinEvent object representing the event.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        applyPlayerEventMessage(event, JOIN_MSG_KEY);
    }

    /**
     * Handles the PlayerQuitEvent.
     *
     * @param event The PlayerQuitEvent object.
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        applyPlayerEventMessage(event, QUIT_MSG_KEY);
    }

    /**
     * Applies a player event message.
     *
     * @param event       The player event.
     * @param messageKey  The key of the message to retrieve from the configuration.
     */
    private void applyPlayerEventMessage(PlayerEvent event, String messageKey) {
        FileConfiguration config = Main.getInstance().getConfig();
        if (!config.getBoolean("Module.ConnectionManager.enable")) {
            return;
        }

        String message = config.getString(messageKey, "no message found")
                .replace('&', '§')
                .replace("%player%", event.getPlayer().getName());
        Component textComponent = Component.text(message);

        if (event instanceof PlayerJoinEvent) {
            if (!event.getPlayer().hasPlayedBefore()) {
                event.getPlayer().sendMessage(WELCOME_MSG
                        .replace('&', '§')
                        .replace("%player%", event.getPlayer().getName()));
            }
            ((PlayerJoinEvent) event).joinMessage(textComponent);
        } else if (event instanceof PlayerQuitEvent) {
            ((PlayerQuitEvent) event).quitMessage(textComponent);
        }
    }
}