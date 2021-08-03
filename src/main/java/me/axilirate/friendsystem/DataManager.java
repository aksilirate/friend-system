package me.axilirate.friendsystem;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataManager {

    public FriendSystem friendSystem;

    public DataManager(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }





    public void saveYamlFile(File file, YamlConfiguration yaml_file) {
        try {
            yaml_file.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
