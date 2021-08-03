package me.axilirate.friendsystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventListener implements Listener {

    public FriendSystem friendSystem;

    public EventListener(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String playerUID = player.getUniqueId().toString();

        if (event.getClickedInventory() != null) {
            if (player.getOpenInventory().getTopInventory().equals(friendSystem.friendsInventory)) {
                event.setCancelled(true);
            }
        }


    }


}
