package net.etum.etumatrium.Server;

import net.etum.etumatrium.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;

public class ServerListPingManager implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        FileConfiguration config = Main.getInstance().getConfig();
        if (!config.getBoolean("MODULE.MOTD.enable")) return;

        List<String> messageList = config.getStringList("MODULE.MOTD.message");
        event.motd(
                Component.text(
                        messageList.get(0).replace('&', '§') + "\n" + messageList.get(1).replace('&', '§')
                )
        );
    }





}
