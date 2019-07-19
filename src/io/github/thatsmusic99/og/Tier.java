package io.github.thatsmusic99.og;

import org.bukkit.Material;

import java.util.List;

public class Tier {

    List<Material> blocks;
    int chance;
    int position;
    String name;

    public Tier(List<Material> m, int c, int p, String n) {
        blocks = m;
        chance = c;
        position = p;
        name = n;
    }

    public int getChance() {
        return chance;
    }

    public int getPosition() {
        return position;
    }

    public List<Material> getBlocks() {
        return blocks;
    }

    public String getName() {
        return name;
    }

    public void setBlocks(List<Material> blocks) {
        this.blocks = blocks;
    }


    public void setChance(int chance) {
        this.chance = chance;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
