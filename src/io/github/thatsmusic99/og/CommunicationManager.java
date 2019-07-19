package io.github.thatsmusic99.og;

import io.github.thatsmusic99.headsplus.api.events.CommunicateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CommunicationManager implements Listener {

    @EventHandler
    public void onCommunicate(CommunicateEvent e) {
        if (e.getPlugin().equalsIgnoreCase("aog")) {
            OreGenerator.getInstance().getLogger().info("Yo! We're gonna clean all the dishes- wait, uh, I mean...");
        }
    }
}
