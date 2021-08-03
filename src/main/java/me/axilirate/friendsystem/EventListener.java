package me.axilirate.friendsystem;

import org.bukkit.event.Listener;

public class EventListener implements Listener {

    public FriendSystem friendSystem;

    public EventListener(FriendSystem friendSystem) {
        this.friendSystem = friendSystem;
    }

}
