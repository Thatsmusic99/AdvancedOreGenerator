package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.BlockListener;
import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.Tier;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reload {

    private static final File configF = new File(OreGenerator.getInstance().getDataFolder(), "config.yml");

    public static void reload(CommandSender cs) {
        try {
            if (!configF.exists()) {
                OreGenerator.getInstance().saveConfig();
                OreGenerator.getInstance().getLogger().info("Config wasn't found, recreated!");
            }
            OreGenerator.getInstance().reloadConfig();
            checkCustoms();
            OreGenerator.getInstance().getBlocks().refreshValues();
            cs.sendMessage(ChatColor.GREEN + "AdvancedOreGenerator " + ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Config has been reloaded!");
        } catch (Exception ex) {
            cs.sendMessage(ChatColor.GREEN + "AdvancedOreGenerator " + ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Failed to reload config!");
            ex.printStackTrace();
        }

    }

    private static void checkCustoms() {
        for (AOGWorld world : AOGWorld.getWorlds()) {
            if (world.getAogSettings().isCustom()) {
                List<Tier> a = new ArrayList<>();
                FileConfiguration fc = OreGenerator.getInstance().getConfig();
                for (String s : fc.getConfigurationSection("custom." + world.getWorldName()).getKeys(false)) {
                    List<Material> c = new ArrayList<>();
                    for (String st : fc.getStringList("custom." + world.getWorldName() + "." + s + ".blocks")) {
                        c.add(Material.valueOf(st));
                    }
                    int chance = fc.getInt("custom." + world.getWorldName() + "." + s + ".chance");
                    int pos = fc.getInt("custom." + world.getWorldName() + "." + s + ".chance");
                    Tier t = new Tier(c, chance, pos, s);
                    a.add(t);
                }
                if (!world.getAogSettings().getTiers().equals(a)) {
                    world.getAogSettings().setTiers(a);
                }
            } else {
                List<Tier> a = new ArrayList<>();
                FileConfiguration fc = OreGenerator.getInstance().getConfig();
                for (String s : fc.getConfigurationSection("tiers").getKeys(false)) {
                    List<Material> c = new ArrayList<>();
                    for (String st : fc.getStringList("tiers." + s + ".blocks")) {
                        c.add(Material.valueOf(st));
                    }
                    int chance = fc.getInt("tiers." + s + ".chance");
                    int pos = fc.getInt("tiers." + s + ".chance");
                    Tier t = new Tier(c, chance, pos, s);
                    a.add(t);
                }
                if (!world.getAogSettings().getTiers().equals(a)) {
                    world.getAogSettings().setTiers(a);
                }
            }
        }
    }
}
