package io.github.thatsmusic99.og.util;

import java.util.ArrayList;
import java.util.List;

public class AOGWorld {

    private String w;
    private AOGSettings aogSettings;
    private static List<AOGWorld> worlds = new ArrayList<>();

    public AOGWorld(String world, AOGSettings settings) {
        this.w = world;
        this.aogSettings = settings;
        worlds.add(this);
    }

    public static List<AOGWorld> getWorlds() {
        return worlds;
    }

    public String getWorldName() {
        return w;
    }

    public AOGSettings getAogSettings() {
        return aogSettings;
    }

    public static void setWorlds(List<AOGWorld> ws) {
        worlds = ws;
    }

    public static AOGWorld getWorld(String worldname) {
        for (AOGWorld world : getWorlds()) {
            if (world.getWorldName().equalsIgnoreCase(worldname)) {
                return world;
            }
        }
        return null;
    }
}
