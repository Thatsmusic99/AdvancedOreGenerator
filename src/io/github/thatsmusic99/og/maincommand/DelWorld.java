package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.util.AOGSettings;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DelWorld {

    public static void enable(String worldname, CommandSender cs) {
        AOGWorld w = AOGWorld.getWorld(worldname);
        if (w == null) w = new AOGWorld(worldname, new AOGSettings(worldname));
        if (!w.getAogSettings().isEnabled()) {
            w.getAogSettings().setEnabled(true);
            List<String> b = OreGenerator.getInstance().getConfig().getStringList("disabled-worlds");
            b.remove(w.getWorldName());
            OreGenerator.getInstance().getConfig().set("disabled-worlds", b);
            OreGenerator.getInstance().getConfig().options().copyDefaults(true);
            OreGenerator.getInstance().saveConfig();
            cs.sendMessage(ChatColor.GREEN + "AdvancedOreGenerator " + ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "World " + worldname + " now has the ore generator enabled!");
        } else {
            cs.sendMessage(ChatColor.GREEN + "AdvancedOreGenerator " + ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "World " + worldname + " is already enabled!");
        }
    }
}
