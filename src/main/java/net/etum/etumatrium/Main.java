package net.etum.etumatrium;

import net.etum.etumatrium.akia.listeners.Commands;
import net.etum.etumatrium.akia.listeners.Events;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the plugin.
 */
public final class Main extends JavaPlugin {

    private static Economy econ = null;

    // Added instance reference to make singleton structure.
    private static Main instance;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        // Instance reference assignment
        instance = this;

        // Plugin startup logic
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", this.getName()));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        new Commands(this);
        new Events(this);


        getLogger().info(String.format("[%s] Plugin successfully started!", this.getName()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(String.format("[%s] Plugin successfully stopped!", this.getName()));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public static Economy getEconomy() {
        return econ;
    }

    // Getter for instance
    public static Main getInstance() {
        return instance;
    }
}