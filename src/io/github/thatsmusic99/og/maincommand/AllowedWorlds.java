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
        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append(ChatColor.GRAY).append("Enabled worlds").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append("Page ").append(ChatColor.GREEN).append("1").append(ChatColor.DARK_GRAY).append("/").append(ChatColor.GREEN).append(list.getTotalPages()).append("\n");

        for (Object str : list.getContentsInPage(1)) {
            sb.append(ChatColor.GRAY).append(((AOGWorld)str).getWorldName()).append("\n");
        }
        return sb.toString();
    }

    public static String allowedWorlds(int page) throws IllegalArgumentException {

        StringBuilder sb = new StringBuilder();
        PagedLists list = new PagedLists(setup(), 8);
        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append(ChatColor.GRAY).append("Enabled worlds").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append("Page ").append(ChatColor.GREEN).append(page).append(ChatColor.DARK_GRAY).append("/").append(ChatColor.GREEN).append(list.getTotalPages()).append("\n");

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
