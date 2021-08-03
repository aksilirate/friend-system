package me.axilirate.friendsystem.commands;

import me.axilirate.friendsystem.FriendSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Friends implements CommandExecutor {


    public FriendSystem friendSystem;

    public Friends(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerUID = player.getUniqueId().toString();

            if (args.length == 0){
                player.openInventory(friendSystem.friendsInventory);
            }

            if (args.length == 2){

                if (args[0].equals("add")){
                    String argsPlayerName = args[1];
                    Player argsPlayer =  Bukkit.getPlayer(argsPlayerName);
                    if (argsPlayer != null){

                    }else{
                        player.sendMessage(ChatColor.GRAY + "The player is not online");
                    }

                }

            }


        }


        return true;
    }


}
