package me.axilirate.friendsystem.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Unfriend {

    public ItemStack getItem() {

        ItemStack itemStack = new ItemStack(Material.MUSIC_DISC_11);

        ItemMeta itemMeta = itemStack.getItemMeta();


        if (itemMeta != null){
            itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.RED + "Unfriend");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.RESET + "");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "click to unfriend");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }



        return itemStack;
    }


}
