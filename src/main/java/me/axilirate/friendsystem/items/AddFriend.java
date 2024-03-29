package me.axilirate.friendsystem.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AddFriend {

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            ArrayList<String> lore = new ArrayList<>();
            itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.YELLOW + "Info");
            lore.add(ChatColor.RESET + "");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "To add a friend use the command /friends add <name>");

            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

}
