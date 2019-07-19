package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AddWorld {

    public static void disable(String worldname, CommandSender cs) {
        if (AOGWorld.getWorld(worldname) != null) {
            AOGWorld w = AOGWorld.getWorld(worldname);
            assert w != null;
            if (w.getAogSettings().isEnabled()) {
                w.getAogSettings().setEnabled(false);
                List<String> b = OreGenerator.getInstance().getConfig().getStringList("disabled-worlds");
                b.add(w.getWorldName());
                OreGenerator.getInstance().getConfig().set("disabled-worlds", b);
                OreGenerator.getInstance().getConfig().options().copyDefaults(true);
                OreGenerator.getInstance().saveConfig();
                cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.GREEN + "World " + worldname + " now has the ore generator disabled!");
            } else {
                cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "World " + worldname + " is already disabled!");
            }
        } else {
            cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "World " + worldname + " wasn't found!");
        }
    }
}
