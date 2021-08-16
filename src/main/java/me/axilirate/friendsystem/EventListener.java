package me.axilirate.friendsystem;

import me.axilirate.friendsystem.items.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class EventListener implements Listener {

    public FriendSystem friendSystem;

    public EventListener(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }

    HashMap<Player, Inventory> lastOpenedFriendsMenu = new HashMap<>();
    HashMap<Player, OfflinePlayer> lastOpenedFriend = new HashMap<>();

    HashMap<OfflinePlayer, ArrayList<String>> inboxMessages = new HashMap<>();


    ItemStack giftBitItem = new GiftBit().getItem();
    ItemStack giftBitCooldownItem = new GiftBitCooldown().getItem();
    ItemStack sendTradeItem = new SendTrade().getItem();
    ItemStack sendTpaItem = new SendTpa().getItem();
    ItemStack unfriendItem = new Unfriend().getItem();



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String playerUID = player.getUniqueId().toString();

        Inventory openedInventory = player.getOpenInventory().getTopInventory();

        if (event.getClickedInventory() != null) {
            Inventory playerFriendsInventory = friendSystem.friendsInventory.get(player);
            Inventory friendsMenu = lastOpenedFriendsMenu.get(player);

            ItemStack clickedItem = event.getCurrentItem();

            if (playerFriendsInventory != null) {
                if (openedInventory.equals(playerFriendsInventory)) {

                    if (clickedItem.getType().equals(Material.PLAYER_HEAD)){
                        SkullMeta skullMeta = (SkullMeta) clickedItem.getItemMeta();
                        OfflinePlayer friend = skullMeta.getOwningPlayer();
                        String friendUID = friend.getUniqueId().toString();
                        Inventory friendInventory = Bukkit.createInventory(player, 9, friend.getName());

                        long giftTime = friendSystem.dataManager.getYamlFriendGiftTime(playerUID, friendUID);


                        if (giftTime == 0 || System.currentTimeMillis() - giftTime > 86400000){
                            friendInventory.setItem(0 , giftBitItem);
                        }else{
                            friendInventory.setItem(0 , giftBitCooldownItem);
                        }

                        friendInventory.setItem(1 , sendTradeItem);

                        friendInventory.setItem(2 , sendTpaItem);

                        friendInventory.setItem(8 , unfriendItem);
                        lastOpenedFriendsMenu.put(player ,friendInventory);
                        lastOpenedFriend.put(player, friend);
                        player.openInventory(friendInventory);

                    }

                    event.setCancelled(true);

                }

                if (openedInventory.equals(lastOpenedFriendsMenu.get(player))) {
                    OfflinePlayer friend = lastOpenedFriend.get(player);
                    String friendUID = friend.getUniqueId().toString();

                    event.setCancelled(true);

                    if (clickedItem == null){
                        return;
                    }

                    if (clickedItem.equals(unfriendItem)){

                        friendSystem.dataManager.setYamlFriend(playerUID, friendUID, false);
                        friendSystem.dataManager.setYamlFriend(friendUID, playerUID, false);

                        Inventory updatedFriendsInventory = friendSystem.getUpdatedFriendsInventory(player);

                        friendSystem.friendsInventory.put(player, updatedFriendsInventory);
                        player.openInventory(updatedFriendsInventory);
                    }

                    else if (clickedItem.equals(giftBitItem)){
                        friendSystem.dataManager.setYamlFriendGiftTime(playerUID, friendUID, System.currentTimeMillis());
                        friendSystem.economy.depositPlayer(friend, 1.0);

                        if (Bukkit.getOnlinePlayers().contains(friend)){
                            Bukkit.getPlayer(UUID.fromString(friendUID)).sendMessage(player.getDisplayName() + ChatColor.GREEN + " has gifted you 1✦");
                        }else{
                            ArrayList<String> inboxMessagesCache = inboxMessages.get(friend);
                            if (inboxMessagesCache == null){
                                inboxMessagesCache = new ArrayList<>();
                            }
                            inboxMessagesCache.add(player.getDisplayName() + ChatColor.GREEN + " has gifted you 1✦");

                        }

                        openedInventory.setItem(0 , giftBitCooldownItem);

                    }

                    else if (clickedItem.equals(sendTradeItem)){
                        player.performCommand("trade " + friend.getName());
                        player.closeInventory();
                    }

                    else if (clickedItem.equals(sendTpaItem)){
                        player.performCommand("tpa " + friend.getName());
                        player.closeInventory();
                    }




                }


            }

        }
    }



    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = (Player) event.getPlayer();
        ArrayList<String> inboxMessagesCache = inboxMessages.get(player);
        if (inboxMessagesCache != null && inboxMessagesCache.size() != 0){
            for(String message: inboxMessagesCache){
                player.sendMessage(message);
            }
            inboxMessagesCache.clear();
            inboxMessages.put(player, inboxMessagesCache);
        }
    }



    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onServiceRegister(ServiceRegisterEvent event) {

        RegisteredServiceProvider<Economy> eco_rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (eco_rsp != null) {
            friendSystem.
                    economy= eco_rsp.getProvider();
        }
    }


}
