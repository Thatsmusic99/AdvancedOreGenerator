package io.github.thatsmusic99.og;

import mkremins.fanciful.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("aog.notify")) {
            if (OreGenerator.getInstance().getConfig().getBoolean("update-notify")) {
                if (OreGenerator.update != null) {
                    new FancyMessage().text(ChatColor.translateAlternateColorCodes('&', "&aAdvancedOreGenerator&  &7An update for AdvancedOreGenerator has been found!"))
                            .tooltip(ChatColor.translateAlternateColorCodes('&', "&7Current version » &a" + OreGenerator.getInstance().getDescription().getVersion())
                                    + "\n" + ChatColor.translateAlternateColorCodes('&', "&7New version » &a"+ OreGenerator.update[2])
                                    + "\n" + ChatColor.translateAlternateColorCodes('&', "&7Description » &a" + OreGenerator.update[1])).link("https://www.spigotmc.org/resources/advancedoregenerator.51153/").send(e.getPlayer());
                }
            }
        }
    }
}
