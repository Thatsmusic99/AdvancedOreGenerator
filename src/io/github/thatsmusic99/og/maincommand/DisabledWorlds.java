package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import io.github.thatsmusic99.og.util.PagedLists;
import org.bukkit.ChatColor;

import java.util.List;

public class DisabledWorlds {

    public static String dWorldsNoArgs() {

        List<String> dworlds = OreGenerator.getInstance().getConfig().getStringList("disabled-worlds");
        StringBuilder sb = new StringBuilder();
        PagedLists list = new PagedLists(dworlds, 8);
        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        sb.append(ChatColor.AQUA).append("Disabled worlds: ").append(ChatColor.GREEN).append("Page ").append(ChatColor.AQUA).append("1").append(ChatColor.GREEN).append("/").append(ChatColor.AQUA).append(list.getTotalPages()).append("\n");

        for (Object str : list.getContentsInPage(1)) {
            sb.append(ChatColor.GREEN).append("").append(str).append("\n");
        }
        return sb.toString();

    }

    public static String dWorlds(int page) throws IllegalArgumentException {
        List<String> dworlds = OreGenerator.getInstance().getConfig().getStringList("disabled-worlds");
        StringBuilder sb = new StringBuilder();
        PagedLists list = new PagedLists(dworlds, 8);
        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        sb.append(ChatColor.AQUA).append("Disabled worlds: ").append(ChatColor.GREEN).append("Page ").append(ChatColor.AQUA).append(page).append(ChatColor.GREEN).append("/").append(ChatColor.AQUA).append(list.getTotalPages()).append("\n");
        for (Object str : list.getContentsInPage(page)) {
            sb.append(ChatColor.GREEN).append("").append(str).append("\n");
        }
        return sb.toString();
    }
}
