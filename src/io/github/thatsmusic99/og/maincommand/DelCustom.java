package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.util.AOGSettings;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DelCustom {

    public static void delCustom(String worldname, CommandSender cs) {
        if (OreGenerator.getInstance().getConfig().getConfigurationSection("custom." + worldname) != null) {
            if (OreGenerator.getInstance().getConfig().getStringList("disabled-worlds").contains(worldname)) {
                cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Note: World " + worldname + " has the ore generator disabled!");
            }
            AOGWorld world = AOGWorld.getWorld(worldname);
            if (world == null) world = new AOGWorld(worldname, new AOGSettings(worldname));
            OreGenerator.getInstance().getConfig().set("custom." + worldname, null);
            OreGenerator.getInstance().getConfig().options().copyDefaults(true);
            OreGenerator.getInstance().saveConfig();
            world.getAogSettings().setCustom(false);
            cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.YELLOW + "World " + worldname + " is no longer custom!");

        } else {
            cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "World " + worldname + " doesn't have custom configuration!");
        }
    }
}
