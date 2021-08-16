package me.axilirate.friendsystem.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SendTrade {


    public ItemStack getItem() {

        ItemStack itemStack = new ItemStack(Material.EMERALD);

        ItemMeta itemMeta = itemStack.getItemMeta();


        if (itemMeta != null){
            itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.YELLOW + "Send a Trade Request");
            ArrayList<String> lore = new ArrayList<>();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }


        return itemStack;
    }
}
