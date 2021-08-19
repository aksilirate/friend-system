package me.axilirate.friendsystem;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DataManager {

    final public FriendSystem friendSystem;

    public DataManager(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }


    public void setYamlFriend(String playerUID, String friendUID, Boolean areFriends) {
        File file = new File(friendSystem.getDataFolder() + "/player-data", playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);
        yaml_file.set("friends." + friendUID + ".are-friends", areFriends);

        saveYamlFile(file, yaml_file);
    }


    public boolean getYamlFriend(String playerUID, String friendUID) {
        File file = new File(friendSystem.getDataFolder() + "/player-data", playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        if (yaml_file.get("friends." + friendUID + ".are-friends") == null) {
            return false;
        }

        return yaml_file.getBoolean("friends." + friendUID + ".are-friends");
    }


    public void setYamlFriendGiftTime(String playerUID, String friendUID, Long time) {
        File file = new File(friendSystem.getDataFolder() + "/player-data", playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        yaml_file.set("friends." + friendUID + ".gift-time", time);

        saveYamlFile(file, yaml_file);
    }


    public Set<String> getYamlFriends(String playerUID) {
        File file = new File(friendSystem.getDataFolder() + "/player-data", playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        if (yaml_file.getConfigurationSection("friends") != null) {

            Set<String> friends = yaml_file.getConfigurationSection("friends").getKeys(false);

            friends.removeIf(friend -> !getYamlFriend(playerUID, friend));

            return friends;

        } else {
            return new HashSet<>();
        }


    }


    public long getYamlFriendGiftTime(String playerUID, String friendUID) {
        File file = new File(friendSystem.getDataFolder() + "/player-data", playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        if (yaml_file.get("friends." + friendUID + ".gift-time") == null) {
            return 0;
        }


        return yaml_file.getLong("friends." + friendUID + ".gift-time");

    }


    public void saveYamlFile(File file, YamlConfiguration yaml_file) {
        try {
            yaml_file.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
