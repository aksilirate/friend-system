package me.axilirate.friendsystem.commands;

import me.axilirate.friendsystem.FriendSystem;
import me.axilirate.friendsystem.items.AddFriend;
import me.axilirate.friendsystem.items.FriendHead;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Friends implements CommandExecutor {


    public FriendSystem friendSystem;

    public Friends(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }

// 1. user      2.friend request
    HashMap<Player, ArrayList<Player>> friendRequests =  new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerUID = player.getUniqueId().toString();

            if (args.length == 0){

                Inventory updatedFriendsInventory = friendSystem.getUpdatedFriendsInventory(player);

                friendSystem.friendsInventory.put(player, updatedFriendsInventory);
                player.openInventory(updatedFriendsInventory);

            }


            if (args.length == 2){
                String argsPlayerName = args[1];
                Player argsPlayer =  Bukkit.getPlayer(argsPlayerName);
                if (argsPlayer == null){
                    player.sendMessage(ChatColor.RED + "Player is not found.");
                    return true;
                }




                if (args[0].equals("add")){

                    if (argsPlayer.getName().equals(player.getName())){
                        player.sendMessage(ChatColor.RED + "You cannot add yourself as a friend");
                        return true;
                    }


                    Set<String> friendsStrings = friendSystem.dataManager.getYamlFriends(playerUID);


                    if (!friendsStrings.isEmpty()){
                        ArrayList<Player> friends = new ArrayList<>();
                        for (String friendUID: friendsStrings){
                            Player friendPlayer = Bukkit.getPlayer(UUID.fromString(friendUID));
                            if (friendPlayer != null){
                                friends.add(friendPlayer);
                            }
                        }


                        if (friends.contains(argsPlayer)){
                            player.sendMessage(ChatColor.RED + argsPlayer.getDisplayName() + " is already your friend.");
                            return true;
                        }

                    }


                    ArrayList<Player> activeRequests = friendRequests.get(argsPlayer);

                    if (activeRequests == null){
                        activeRequests = new ArrayList<>();
                    }

                    activeRequests.add(player);
                    friendRequests.put(argsPlayer, activeRequests);

                    TextComponent message = new TextComponent();

                    TextComponent acceptMsg = new TextComponent("Accept");
                    acceptMsg.setColor(ChatColor.GREEN);
                    acceptMsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + "accept the friend request")));
                    acceptMsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friends accept " + player.getName()));


                    TextComponent declineMsg = new TextComponent("Decline");
                    declineMsg.setColor(ChatColor.RED);
                    declineMsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "decline the friend request")));
                    declineMsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,  "/friends decline " + player.getName()));


                    TextComponent msg0 = new TextComponent(player.getDisplayName() + " has sent you a friend request [");
                    msg0.setColor(ChatColor.GRAY);

                    TextComponent msg1 = new TextComponent(" | ");
                    msg1.setColor(ChatColor.GRAY);

                    TextComponent msg2 = new TextComponent("]");
                    msg2.setColor(ChatColor.GRAY);

                    message.addExtra(msg0);
                    message.addExtra(acceptMsg);
                    message.addExtra(msg1);
                    message.addExtra(declineMsg);
                    message.addExtra(msg2);

                    argsPlayer.spigot().sendMessage(message);


                }



                if (args[0].equals("accept")){
                    ArrayList<Player> activeRequests = friendRequests.get(player);

                    if (activeRequests == null){
                        activeRequests = new ArrayList<>();
                        player.sendMessage(ChatColor.RED + "You have no active requests");
                        return true;
                    }

                    if (activeRequests.contains(argsPlayer)){
                        String friendUID = argsPlayer.getUniqueId().toString();

                        friendSystem.dataManager.setYamlFriend(playerUID, friendUID, true);
                        friendSystem.dataManager.setYamlFriend(friendUID, playerUID, true);

                        activeRequests.remove(argsPlayer);
                        friendRequests.put(argsPlayer, activeRequests);

                        player.sendMessage(ChatColor.GREEN + "You have accepted your friend request");
                        argsPlayer.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has accepted your friend request");
                    }

                }



                else if (args[0].equals("decline")){
                    ArrayList<Player> activeRequests = friendRequests.get(player);

                    if (activeRequests == null){
                        activeRequests = new ArrayList<>();
                        player.sendMessage(ChatColor.RED + "You have no active requests");
                        return true;
                    }

                    if (activeRequests.contains(argsPlayer)){
                        activeRequests.remove(argsPlayer);
                        friendRequests.put(argsPlayer, activeRequests);
                        argsPlayer.sendMessage(ChatColor.RED + player.getDisplayName() + " has declined your friend request");
                        player.sendMessage(ChatColor.GRAY + "You declined the friend request");
                    }



                }




            }


        }


        return true;
    }


}
