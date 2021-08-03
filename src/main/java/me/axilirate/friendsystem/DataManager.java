package me.axilirate.friendsystem;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class DataManager {

    public FriendSystem friendSystem;

    public DataManager(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }



    public void setYamlFriendTimeStamp(String playerUID, String friendUID, Double TimeStamp) {
        File file = new File(friendSystem.getDataFolder(), "/player-data/" + playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        yaml_file.set("friends." + friendUID, TimeStamp);

        saveYamlFile(file, yaml_file);
    }



    public Set<String> getYamlFriends(String playerUID){
        File file = new File(friendSystem.getDataFolder(), "/player-data/" + playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        return yaml_file.getKeys(false);
    }



    public Double getYamlFriendTimeStamp(String playerUID, String friendUID) {
        File file = new File(friendSystem.getDataFolder(), "/player-data/" + playerUID + ".yml");
        YamlConfiguration yaml_file = YamlConfiguration.loadConfiguration(file);

        return (Double) yaml_file.get("friends." + friendUID);

    }





    public void saveYamlFile(File file, YamlConfiguration yaml_file) {
        try {
            yaml_file.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
