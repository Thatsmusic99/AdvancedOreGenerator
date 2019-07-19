package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.util.AOGWorld;
import io.github.thatsmusic99.og.util.PagedLists;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class AllowedWorlds {

    public static String allowedWorldsNoArgs(){

        StringBuilder sb = new StringBuilder();
        PagedLists list = new PagedLists(setup(), 8);
        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        sb.append(ChatColor.AQUA).append("Enabled worlds: ").append(ChatColor.GREEN).append("Page ").append(ChatColor.AQUA).append("1").append(ChatColor.GREEN).append("/").append(ChatColor.AQUA).append(list.getTotalPages()).append("\n");

        for (Object str : list.getContentsInPage(1)) {
            sb.append(ChatColor.GREEN).append(((AOGWorld)str).getWorldName()).append("\n");
        }
        return sb.toString();
    }

    public static String allowedWorlds(int page) throws IllegalArgumentException {

        StringBuilder sb = new StringBuilder();
        PagedLists list = new PagedLists(setup(), 8);
        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        sb.append(ChatColor.AQUA).append("Enabled worlds: ").append(ChatColor.GREEN).append("Page ").append(ChatColor.AQUA).append(page).append(ChatColor.GREEN).append("/").append(ChatColor.AQUA).append(list.getTotalPages()).append("\n");

        for (Object str : list.getContentsInPage(page)) {
            sb.append(ChatColor.GREEN).append(((AOGWorld)str).getWorldName()).append("\n");
        }
        return sb.toString();



    }

    private static List<AOGWorld> setup() {
        List<AOGWorld> worlds = new ArrayList<>(AOGWorld.getWorlds());

        List<String> dworlds = OreGenerator.getInstance().getConfig().getStringList("disabled-worlds");

        worlds.removeIf(world -> dworlds.contains(world.getWorldName()));
        return worlds;
    }
}
