package io.github.thatsmusic99.og.util;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.Tier;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class AOGSettings {

    private List<Tier> tiers;
    private boolean e;
    private boolean custom;

    public AOGSettings(String str) {
        FileConfiguration config = OreGenerator.getInstance().getConfig();
        boolean custom = false;
        boolean enabled = false;
        List<Tier> a = new ArrayList<>();
        try {
            if (config.getConfigurationSection("custom." + str) != null) {
                custom = true;
                enabled = true;
                for (String sto : config.getConfigurationSection("custom." + str).getKeys(false)) {
                    List<Material> c = new ArrayList<>();
                    for (String st : config.getStringList("custom." + str + "." + sto + ".blocks")) {
                        c.add(Material.valueOf(st));
                    }
                    int chance = config.getInt("custom." + str + "." + sto + ".chance");
                    int pos = config.getInt("custom." + str + "." + sto + ".position");
                    Tier t = new Tier(c, chance, pos, sto);
                    a.add(t);

                }
            }
        } catch (Exception ignored) {

        }
        if (!custom) {
            enabled = true;
            a = OreGenerator.getInstance().getDefaultTiers();
        }

        if (config.getStringList("disabled-worlds").contains(str)) {
            enabled = false;
        }
        e = enabled;
        tiers = a;
        this.custom = custom;
    }
    public AOGSettings(List<Tier> t, boolean enabled, boolean custom) {
        tiers = t;
        e = enabled;
        this.custom = custom;
    }

    public boolean isCustom() {
        return custom;
    }

    public boolean isEnabled() {
        return e;
    }

    public List<Tier> getTiers() {
        return tiers;
    }
    public void setCustom(boolean b) {
        custom = b;
    }

    public void setEnabled(boolean b) {
        e = b;
    }

    public void setTiers(List<Tier> tiers) {
        this.tiers = tiers;
    }
}
