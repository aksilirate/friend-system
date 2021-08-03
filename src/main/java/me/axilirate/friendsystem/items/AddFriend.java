package me.axilirate.friendsystem.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AddFriend {

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta ItemMeta = itemStack.getItemMeta();
        if (ItemMeta != null) {
            ItemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.YELLOW + "Add a Friend");
            itemStack.setItemMeta(ItemMeta);
        }
        return itemStack;
    }

}
