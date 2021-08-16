package me.axilirate.friendsystem.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiftBit {

    public ItemStack getItem() {

        ItemStack itemStack = new ItemStack(Material.LODESTONE);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(Enchantment.LOYALTY, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        if (itemMeta != null){
            itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.GREEN + "Gift 1✦");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.RESET + "");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "click to gift a free bit");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "(this wont take a bit from your balance)");
            lore.add(ChatColor.RESET + "");
            lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "can be done once every 24 hours");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }


        return itemStack;
    }

    }
