package me.axilirate.friendsystem.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SendTpa {

    public ItemStack getItem() {

        ItemStack itemStack = new ItemStack(Material.ENDER_PEARL);

        ItemMeta itemMeta = itemStack.getItemMeta();


        if (itemMeta != null){
            itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.YELLOW + "Send a Teleport Request" + ChatColor.RED + " [1✦]");
            ArrayList<String> lore = new ArrayList<>();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }


        return itemStack;
    }

}
