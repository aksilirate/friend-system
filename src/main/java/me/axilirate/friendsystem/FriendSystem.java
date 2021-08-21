package me.axilirate.friendsystem;

import me.axilirate.friendsystem.commands.Friends;
import me.axilirate.friendsystem.items.AddFriend;
import me.axilirate.friendsystem.items.FriendHead;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public final class FriendSystem extends JavaPlugin {

    public Economy economy;
    public DataManager dataManager;

    public HashMap<Player, Inventory> friendsInventory = new HashMap<>();

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


        RegisteredServiceProvider<Economy> eco_rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (eco_rsp != null){
            economy = eco_rsp.getProvider();
        }



        dataManager = new DataManager(this);
        getServer().getPluginManager().registerEvents(new EventListener(this), this);


        Friends friends = new Friends(this);
        this.getCommand("friends").setExecutor(friends);
        this.getCommand("friends").setTabCompleter(friends);








    }


    private boolean setupEconomy() {
        return getServer().getPluginManager().getPlugin("Vault") != null;
    }



    public Inventory getUpdatedFriendsInventory(Player player){

        String playerUID = player.getUniqueId().toString();

        int invIndex = 0;
        Set<String> friendsStrings = dataManager.getYamlFriends(playerUID);
        Inventory playerFriendsInventory = Bukkit.createInventory(null, 54, "Friends");
        playerFriendsInventory.clear();

        playerFriendsInventory.setItem(45, new AddFriend().getItem());


        for (String friendUID: friendsStrings){
            OfflinePlayer friendPlayer = Bukkit.getOfflinePlayer(UUID.fromString(friendUID));

            if (friendPlayer != null){
                ItemStack friendsHead = new FriendHead().getItem(friendPlayer);
                playerFriendsInventory.setItem(invIndex, friendsHead);
                invIndex += 1;
            }

        }

        return playerFriendsInventory;

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
