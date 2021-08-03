package me.axilirate.friendsystem.commands;

import me.axilirate.friendsystem.FriendSystem;
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


        if (sender instanceof Player){
            Player player = (Player) sender;
            String playerUID = player.getUniqueId().toString();



        }



        return true;
    }



}
