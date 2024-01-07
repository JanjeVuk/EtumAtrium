package net.etum.etumatrium.Listeners;

import net.etum.etumatrium.Main;
import net.etum.etumatrium.Player.Events.ConnectionManager;
import org.bukkit.plugin.PluginManager;

public class Events {



    public Events(Main main) {

        PluginManager pm = main.getServer().getPluginManager();


        eventsPlayer(main, pm);

    }

    private void eventsPlayer(Main main, PluginManager pm){

        pm.registerEvents(new ConnectionManager(), main);

    }
}
