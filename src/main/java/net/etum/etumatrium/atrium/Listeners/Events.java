package net.etum.etumatrium.atrium.Listeners;

import net.etum.etumatrium.Main;
import net.etum.etumatrium.atrium.Player.Events.ConnectionManager;
import net.etum.etumatrium.Server.ServerListPingManager;
import org.bukkit.plugin.PluginManager;

public class Events {



    public Events(Main main) {

        PluginManager pm = main.getServer().getPluginManager();


        eventsPlayer(main, pm);
        eventServer(main, pm);

    }

    private void eventsPlayer(Main main, PluginManager pm){

        pm.registerEvents(new ConnectionManager(), main);

    }

    private void eventServer(Main main, PluginManager pm){

        pm.registerEvents(new ServerListPingManager(), main);

    }
}
