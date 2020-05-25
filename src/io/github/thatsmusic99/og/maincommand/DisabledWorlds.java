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
        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append(ChatColor.GRAY).append("Disabled worlds").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append("Page ").append(ChatColor.GREEN).append("1").append(ChatColor.DARK_GRAY).append("/").append(ChatColor.GREEN).append(list.getTotalPages()).append("\n");

        for (Object str : list.getContentsInPage(1)) {
            sb.append(ChatColor.GREEN).append(str).append("\n");
        }
        return sb.toString();

    }

    public static String dWorlds(int page) throws IllegalArgumentException {
        List<String> dworlds = OreGenerator.getInstance().getConfig().getStringList("disabled-worlds");
        StringBuilder sb = new StringBuilder();
        PagedLists list = new PagedLists(dworlds, 8);
        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append(ChatColor.GRAY).append("Disabled worlds").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append("Page ").append(ChatColor.GREEN).append(page).append(ChatColor.DARK_GRAY).append("/").append(ChatColor.GREEN).append(list.getTotalPages()).append("\n");
        for (Object str : list.getContentsInPage(page)) {
            sb.append(ChatColor.GREEN).append(str).append("\n");
        }
        return sb.toString();
    }
}
