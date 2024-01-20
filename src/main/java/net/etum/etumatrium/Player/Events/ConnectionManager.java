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

    private static final String JOIN_MSG_KEY = "Module.ConnectionManager.message.join";
    private static final String QUIT_MSG_KEY = "Module.ConnectionManager.message.quit";
    private static final String WELCOME_MSG = getWelcomeMsg();

    private static String getWelcomeMsg() {
        FileConfiguration config = Main.getInstance().getConfig();
        return String.join(System.lineSeparator(), config.getStringList("Module.ConnectionManager.message.welcome"));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        applyPlayerEventMessage(event, JOIN_MSG_KEY);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        applyPlayerEventMessage(event, QUIT_MSG_KEY);
    }

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