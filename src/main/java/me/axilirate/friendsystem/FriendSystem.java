package me.axilirate.friendsystem;

import me.axilirate.friendsystem.commands.Friends;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class FriendSystem extends JavaPlugin {

    public Economy eco;
    public DataManager dataManager;


    public Inventory friendsInventory = Bukkit.createInventory(null, 54, "Friends");


    @Override
    public void onEnable() {

        if (!setupEconomy()) {
            System.out.println("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        if (!getDataFolder().exists()) {
            if (getDataFolder().mkdirs()) {
                getLogger().info("Data dir was created.");
            }
        }

        dataManager = new DataManager(this);
        getServer().getPluginManager().registerEvents(new EventListener(this), this);

        RegisteredServiceProvider<Economy> eco_rsp = getServer().getServicesManager().getRegistration(Economy.class);
        eco = eco_rsp.getProvider();

        this.getCommand("friends").setExecutor(new Friends(this));


    }


    private boolean setupEconomy() {
        return getServer().getPluginManager().getPlugin("Vault") != null;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
