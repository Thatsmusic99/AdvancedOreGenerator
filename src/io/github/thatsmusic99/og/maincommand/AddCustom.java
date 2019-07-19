package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.util.AOGSettings;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AddCustom {

    public static void addCustom(String worldname, CommandSender cs) {
        FileConfiguration fc = OreGenerator.getInstance().getConfig();
        if (fc.getConfigurationSection("custom." + worldname) == null) {
            if (fc.getStringList("disabled-worlds").contains(worldname)) {
                cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Note: World " + worldname + " has the ore generator disabled!");
            }
            AOGWorld w = AOGWorld.getWorld(worldname);
            if (w == null) w = new AOGWorld(worldname, new AOGSettings(worldname));
            fc.addDefault("custom." + worldname + ".common.blocks", new ArrayList<>(Arrays.asList("COAL_ORE", "IRON_ORE")));
            fc.addDefault("custom." + worldname + ".common.chance", 30);
            fc.addDefault("custom." + worldname + ".common.position", 1);
            fc.addDefault("custom." + worldname + ".medium.blocks", new ArrayList<>(Arrays.asList("REDSTONE_ORE", "LAPIS_ORE")));
            fc.addDefault("custom." + worldname + ".medium.chance", 20);
            fc.addDefault("custom." + worldname + ".medium.position", 2);
            fc.addDefault("custom." + worldname + ".rare.blocks", new ArrayList<>(Collections.singleton("GOLD_ORE")));
            fc.addDefault("custom." + worldname + ".rare.chance", 10);
            fc.addDefault("custom." + worldname + ".rare.position", 3);
            fc.addDefault("custom." + worldname + ".srare.blocks", new ArrayList<>(Arrays.asList("DIAMOND_ORE", "EMERALD_ORE")));
            fc.addDefault("custom." + worldname + ".srare.chance", 5);
            fc.addDefault("custom." + worldname + ".srare.position", 4);
            fc.options().copyDefaults(true);
            OreGenerator.getInstance().saveConfig();
            w.getAogSettings().setCustom(true);
            cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.GREEN + "World " + worldname + " now has custom configuration!");

        } else {
            cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "World " + worldname + " already has custom configuration!");
        }
    }
}
