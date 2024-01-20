package net.etum.etumatrium.Server;

import net.etum.etumatrium.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingManager implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {

        FileConfiguration config = Main.getInstance().getConfig();


    }



}
