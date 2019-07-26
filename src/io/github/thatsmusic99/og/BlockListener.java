package io.github.thatsmusic99.og;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.HashMap;
import java.util.Random;

public class BlockListener implements Listener {

    private static HashMap<Location, Player> hl = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (OreGenerator.getInstance().getConfig().getBoolean("og-enabled")) {
            if (OreGenerator.getInstance().getConfig().getStringList("disabled-worlds").contains(e.getBlock().getWorld().getName())) return;
            hl.put(e.getBlock().getLocation(), e.getPlayer());
        }
    }

    @EventHandler
    public void onBlockEvent(BlockFormEvent e) {
        try {
            if (OreGenerator.getInstance().getServer().getVersion().contains("1.8")) return;
            if (e.getBlock().getType() == Material.LAVA) {
                if (OreGenerator.getInstance().getConfig().getBoolean("og-enabled")) {
                    if (OreGenerator.getInstance().getConfig().getStringList("disabled-worlds").contains(e.getBlock().getWorld().getName())) return;
                    e(e.getBlock().getLocation(), e.getBlock(), e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @EventHandler
    public void onBlockBreak(BlockFromToEvent e) {
        try {
            if (!OreGenerator.getInstance().getServer().getVersion().contains("1.8")) return;
            if (e.getBlock().getType() == Material.LAVA) {
                if (OreGenerator.getInstance().getConfig().getBoolean("og-enabled")) {
                    if (OreGenerator.getInstance().getConfig().getStringList("disabled-worlds").contains(e.getBlock().getWorld().getName())) return;
                    e(e.getBlock().getLocation(), e.getToBlock(), e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void e(Location a, Block b, BlockEvent e) {
        Player p = null;
        if (OreGenerator.getInstance().getConfig().getBoolean("requires-permission")) {
            if (!hl.containsKey(a)) return;
            if (!hl.get(a).hasPermission("aog.use-generator")) {
                hl.remove(a);
                return;
            }
            p = hl.get(a);
            hl.remove(a);
        }

        Material m = null;
        Location l = null;
        FileConfiguration config = OreGenerator.getInstance().getConfig();
        Random rand = new Random();
        int result = rand.nextInt(100) + 1;
        String worldName = e.getBlock().getWorld().getName();
        if (config.getConfigurationSection("custom." +worldName) != null) {
            for (int i = 0; i <= config.getConfigurationSection("custom." + worldName).getKeys(false).size(); i++) {
                for (String key : config.getConfigurationSection("custom." + worldName).getKeys(false)) {
                    if (config.getInt("custom." + worldName + "." + key + ".position") == i) {

                        if (result <= config.getDouble("custom." + worldName + "." + key + ".chance")) {
                            if (p == null || p.hasPermission("aog.tiers.use.custom." + worldName + "." + key)) {
                                int block = rand.nextInt(config.getStringList("custom." + worldName + "." + key + ".blocks").size());
                                l = b.getLocation();
                                m = Material.valueOf(config.getStringList("custom." + worldName + "." + key + ".blocks").get(block));
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
                            if (p == null || p.hasPermission("aog.tiers.use." + key)) {
                                int block = rand.nextInt(config.getStringList("tiers." + key + ".blocks").size());
                                l = b.getLocation();
                                m = Material.valueOf(config.getStringList("tiers." + key + ".blocks").get(block));
                            }
                        }
                    }
                }
            }
        }

        if (m != null && l != null) {
            ((Cancellable) e).setCancelled(true);
            l.getBlock().setType(m);

        }
    }

    private boolean isLava(Material m) {
        try {
            return m == Material.valueOf("STATIONARY_LAVA");
        } catch (Exception e) {
            return m == Material.LAVA;
        }
    }

}
