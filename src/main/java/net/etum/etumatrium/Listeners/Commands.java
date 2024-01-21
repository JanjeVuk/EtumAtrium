package net.etum.etumatrium.Listeners;

import net.etum.etumatrium.Main;
import net.etum.etumatrium.Player.Commands.CommandAtrium;

import java.util.Objects;

public class Commands {


    public Commands(Main main) {
        commandsServer(main);
    }

    private void commandsServer(Main main){

        Objects.requireNonNull(main.getCommand("atrium")).setExecutor(new CommandAtrium(main));


    }
}
