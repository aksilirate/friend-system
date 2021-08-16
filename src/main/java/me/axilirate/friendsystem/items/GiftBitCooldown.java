package me.axilirate.friendsystem.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiftBitCooldown {

    public ItemStack getItem() {

        ItemStack itemStack = new ItemStack(Material.LODESTONE);

        ItemMeta itemMeta = itemStack.getItemMeta();


        if (itemMeta != null){
            itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.GRAY + "You already gifted a free bit today");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.RESET + "");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "come back tomorrow");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }


        return itemStack;
    }

}
