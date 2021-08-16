package me.axilirate.friendsystem.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class FriendHead {

    public ItemStack getItem(OfflinePlayer player) {

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();


        if (skullMeta != null){
            skullMeta.setOwningPlayer(player);
            skullMeta.setDisplayName(ChatColor.RESET + player.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.RESET + "");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "click to open menu");
            skullMeta.setLore(lore);
            itemStack.setItemMeta(skullMeta);
        }



        return itemStack;
    }

}
