package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

public class RunTests {

    public static void runTests(int amount, CommandSender cs, String worldname) {
        HashMap<Material, Integer> occurances = new HashMap<>();
        cs.sendMessage(ChatColor.AQUA + "Running tests... (this could take a while)");
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int in = 0; in < amount; in++) {
                    Random rand = new Random();
                    int result = rand.nextInt(100) + 1;
                    Material m = null;
                    FileConfiguration config = OreGenerator.getInstance().getConfig();
                    if (config.getConfigurationSection("custom." +worldname) != null) {
                        for (int i = 0; i <= config.getConfigurationSection("custom." + worldname).getKeys(false).size(); i++) {
                            for (String key : config.getConfigurationSection("custom." + worldname).getKeys(false)) {
                                if (config.getInt("custom." + worldname + "." + key + ".position") == i) {

                                    if (result <= config.getDouble("custom." + worldname + "." + key + ".chance")) {
                                        if (cs.hasPermission("aog.tiers.use.custom." + worldname + "." + key)) {
                                            int block = rand.nextInt(config.getStringList("custom." + worldname + "." + key + ".blocks").size());
                                            m = Material.valueOf(config.getStringList("custom." + worldname + "." + key + ".blocks").get(block));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i <= config.getConfigurationSection("tiers").getKeys(false).size(); i++) {
                            for (String key : config.getConfigurationSection("tiers").getKeys(false)) {
                                if (config.getInt("tiers." + key + ".position") == i) {
                                    if (result <= config.getDouble("tiers." + key + ".chance")) {
                                        int block = rand.nextInt(config.getStringList("tiers." + key + ".blocks").size());
                                        m = Material.valueOf(config.getStringList("tiers." + key + ".blocks").get(block));
                                    }
                                }
                            }
                        }
                    }
                    if (m == null) {
                        m = Material.COBBLESTONE;
                    }
                    occurances.putIfAbsent(m, 0);
                    int i = occurances.get(m);
                    i++;
                    occurances.put(m, i);
                }
                cs.sendMessage(ChatColor.GREEN + "----------- " + ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "]" + ChatColor.GREEN + " -----------");
                for (Material m : occurances.keySet()) {
                    DecimalFormat format = new DecimalFormat("#.##");
                    cs.sendMessage(m.name() + " - " + occurances.get(m) + " (" + format.format((Double.valueOf(occurances.get(m)) / amount) * 100) + "%)");
                }
            }
        }.runTaskAsynchronously(OreGenerator.getInstance());


    }
}
