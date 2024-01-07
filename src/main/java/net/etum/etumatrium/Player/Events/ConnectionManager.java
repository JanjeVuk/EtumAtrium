package net.etum.etumatrium.Player.Events;

import net.etum.etumatrium.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * The ConnectionManager class is responsible for managing player connection events
 * and displaying join and quit messages.
 * Implements the Listener interface to handle events.
 */
public class ConnectionManager implements Listener {


    /**
     * The JOIN_MSG_KEY represents the key used to retrieve the join message from the configuration file.
     * This key is used in the ConnectionManager class to display a customized join message to players.
     */
    private static final String JOIN_MSG_KEY = "Module.ConnectionManager.message.join";
    /**
     * The key used to retrieve the quit message from the configuration file.
     * This key is used in the ConnectionManager class to display a customized quit message to players.
     */
    private static final String QUIT_MSG_KEY = "Module.ConnectionManager.message.quit";
    /**
     * Welcome message displayed to players when they join the server.
     * This message is used in the ConnectionManager class to display a customized welcome message to players.
     */
    private static final String WELCOME_MSG = "Bienvenue sur le serveur!";


    /**
     * The ConnectionManager class is responsible for managing player connection events
     * and displaying join and quit messages.
     * Implements the Listener interface to handle events.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        applyPlayerEventMessage(event, JOIN_MSG_KEY);
    }


    /**
     * This method is called when a player quits the server.
     * It applies a quit message to the player using the message key defined in the ConnectionManager class.
     *
     * @param event        The PlayerQuitEvent associated with the player quitting.
     * @see ConnectionManager#applyPlayerEventMessage(PlayerEvent, String)
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        applyPlayerEventMessage(event, QUIT_MSG_KEY);
    }

    /**
     * Applies a player event message to the specified event.
     *
     * @param event        The player event to apply the message to.
     * @param messageKey   The key used to retrieve the message from the configuration file.
     */
    private void applyPlayerEventMessage(PlayerEvent event, String messageKey) {
        FileConfiguration config = Main.getInstance().getConfig();
        if (!config.getBoolean("Module.ConnectionManager.enable")) {
            return;
        }

        String message = config.getString(messageKey, WELCOME_MSG)
                .replace('&', '§')
                .replace("%player%", event.getPlayer().getName());
        Component textComponent = Component.text(message);

        if (event instanceof PlayerJoinEvent) {
            ((PlayerJoinEvent) event).joinMessage(textComponent);
        } else if (event instanceof PlayerQuitEvent) {
            ((PlayerQuitEvent) event).quitMessage(textComponent);
        }
    }
}