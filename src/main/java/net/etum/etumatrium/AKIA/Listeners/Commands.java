package net.etum.etumatrium.AKIA.Listeners;

import net.etum.etumatrium.Main;
import net.etum.etumatrium.AKIA.Player.Commands.CommandSystem;
import net.etum.etumatrium.AKIA.Player.Commands.CommandGamemode;

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
