package net.etum.etumatrium.akia.listeners;

import net.etum.etumatrium.Main;
import net.etum.etumatrium.akia.player.Commands.CommandSystem;
import net.etum.etumatrium.akia.player.Commands.CommandGamemode;

import java.util.Objects;

public class Commands {


    public Commands(Main main) {
        commandsServer(main);
    }

    private void commandsServer(Main main){

        Objects.requireNonNull(main.getCommand("atrium")).setExecutor(new CommandSystem(main));
        Objects.requireNonNull(main.getCommand("gamemode")).setExecutor(new CommandGamemode(main));


    }
}
