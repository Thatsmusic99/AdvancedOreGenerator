package io.github.thatsmusic99.og;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import io.github.thatsmusic99.og.hooks.*;
import io.github.thatsmusic99.og.util.AOGSettings;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OreGenerator extends JavaPlugin {

    private static OreGenerator instance;
    public List<String> worlds = new ArrayList<>();
    static Object[] update = null;
    private final static List<Hook> availableHooks = new ArrayList<>(Arrays.asList(new ASkyBlock(), new FabledSkyBlock(), new uSkyBlock(), new SuperiorSkyBlock()));
    public BlockListener blocks;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        if (getServer().getPluginManager().getPlugin("HeadsPlus") != null) {
            getServer().getPluginManager().registerEvents(new CommunicationManager(), this);
        }
        getCommand("aog").setExecutor(new MainCommand());
        instance = this;
        setupConfig(instance);
        getServer().getPluginManager().registerEvents(blocks = new BlockListener(), this);
        if (getConfig().getBoolean("update-checker")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        update = UpdateChecker.getUpdate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (update != null) {
                        getLogger().info("An update has been found!");
                        getLogger().info("Current version: " + getDescription().getVersion());
                        getLogger().info("New version: " + update[2]);
                        if (update[1].toString().length() > 50) {
                            update[1] = update[1].toString().subSequence(0, 50) + "... (Check Spigot for more information)";
                        }
                        getLogger().info("Description: " + update[1]);
                        getLogger().info("Download link: https://www.spigotmc.org/resources/advancedoregenerator.51153/");
                    } else {
                        getLogger().info("Plugin is up to date!");
                    }
                }
            }.runTaskAsynchronously(this);
        }
    }

    private void setupConfig(Plugin p) {
        try {
            if (p.getDataFolder() == null) {
                p.getDataFolder().mkdirs();
            }
            List<String> worlds = new ArrayList<>();
            try {
                getMultiverseCore();
                FileConfiguration fc = YamlConfiguration.loadConfiguration(new File(getMultiverseCore().getDataFolder(), "worlds.yml"));
                for (String s : fc.getConfigurationSection("worlds").getKeys(false)) {
                    worlds.add(s);
                    this.worlds.add(s);
                }
            } catch (RuntimeException ex) {

                for (World world : Bukkit.getWorlds()) {
                    worlds.add(world.getName());
                    this.worlds.add(world.getName());
                }
            }

            List<String> list = new ArrayList<>(Arrays.asList("COAL_ORE", "IRON_ORE"));
            List<String> list2 = new ArrayList<>(Arrays.asList("REDSTONE_ORE", "LAPIS_ORE"));
            List<String> list3 = new ArrayList<>(Collections.singleton("GOLD_ORE"));
            List<String> list4 = new ArrayList<>(Arrays.asList("DIAMOND_ORE", "EMERALD_ORE"));



            if (getServer().getPluginManager().isPluginEnabled("ASkyBlock")) {
                try {
                    getLogger().info("ASkyBlock found!");
                    worlds.remove(ASkyBlockAPI.getInstance().getIslandWorld().getName());
                    worlds.remove(ASkyBlockAPI.getInstance().getNetherWorld().getName());
                } catch (Exception ignored) {

                }

            }

            if (getServer().getPluginManager().isPluginEnabled("uSkyBlock")) {
                getLogger().info("uSkyBlock found!");
                uSkyBlockAPI usb = (uSkyBlockAPI) getServer().getPluginManager().getPlugin("uSkyBlock");
                try {
                    worlds.remove(usb.getIslandInfo(Bukkit.getPlayer(usb.getTopTen().get(0).getLeaderName())).getWarpLocation().getWorld().getName());
                } catch (IndexOutOfBoundsException e) {
                    getLogger().info("Couldn't add the world uSkyBlock uses. Make sure you've used the plugin at least once!");
                } catch (Exception ignored) {

                }
            }

            if (getServer().getPluginManager().isPluginEnabled("AcidIsland")) {
                try {
                    getLogger().info("AcidIsland found!");
                    worlds.remove(com.wasteofplastic.acidisland.ASkyBlockAPI.getInstance().getIslandWorld().getName());
                    worlds.remove(com.wasteofplastic.acidisland.ASkyBlockAPI.getInstance().getNetherWorld().getName());
                } catch (Exception ignored) {

                }

            }
            if (getConfig().get("disabled-worlds") == null) {
                getConfig().addDefault("tiers.common.blocks", list);
                getConfig().addDefault("tiers.common.chance", 30);
                getConfig().addDefault("tiers.common.position", 1);
                getConfig().addDefault("tiers.medium.blocks", list2);
                getConfig().addDefault("tiers.medium.chance", 20);
                getConfig().addDefault("tiers.medium.position", 2);
                getConfig().addDefault("tiers.rare.blocks", list3);
                getConfig().addDefault("tiers.rare.chance", 10);
                getConfig().addDefault("tiers.rare.position", 3);
                getConfig().addDefault("tiers.srare.blocks", list4);
                getConfig().addDefault("tiers.srare.chance", 5);
                getConfig().addDefault("tiers.srare.position", 4);
            }
            getConfig().addDefault("og-enabled", true);
            getConfig().addDefault("requires-permission", true);
            getConfig().addDefault("disabled-worlds", worlds);
            getConfig().addDefault("update-checker", true);
            getConfig().addDefault("update-notifier", true);
            getConfig().options().copyDefaults(true);
            Metrics m = new Metrics(OreGenerator.getInstance());
            saveConfig();
            setupWorlds();
            m.addCustomChart(new Metrics.SingleLineChart("worlds_managed", () -> AOGWorld.getWorlds().size()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static OreGenerator getInstance() {
        return instance;
    }

    private MultiverseCore getMultiverseCore() {
        Plugin plugin = getServer().getPluginManager().getPlugin("Multiverse-Core");

        if (plugin instanceof MultiverseCore) {
            return (MultiverseCore) plugin;
        }

        throw new RuntimeException("Multiverse not found!");
    }

    private void setupWorlds() {
        for (String str : worlds) {
            AOGSettings o = new AOGSettings(str);
            new AOGWorld(str, o);
        }

    }

    public List<Tier> getDefaultTiers() {
        List<Tier> a = new ArrayList<>();
        for (String sto : getConfig().getConfigurationSection("tiers").getKeys(false)) {
            List<Material> c = new ArrayList<>();
            for (String st : getConfig().getStringList("tiers." + sto + ".blocks")) {
                c.add(Material.valueOf(st));
            }
            int chance = getConfig().getInt("tiers." + sto + ".chance");
            int pos = getConfig().getInt("tiers." + sto + ".position");
            Tier t = new Tier(c, chance, pos, sto);
            a.add(t);
        }
        return a;
    }

    public BlockListener getBlocks() {
        return blocks;
    }
}
