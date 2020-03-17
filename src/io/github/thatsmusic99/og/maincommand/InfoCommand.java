package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class InfoCommand {

    public static void info(CommandSender cs) {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append("\n").append(ChatColor.GRAY).append("Name").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GREEN).append(OreGenerator.getInstance().getDescription().getName());
        sb.append("\n").append(ChatColor.GRAY).append("Author").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GREEN).append("Thatsmusic99");
        sb.append("\n").append(ChatColor.GRAY).append("Version").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GREEN).append(OreGenerator.getInstance().getDescription().getVersion());
        cs.sendMessage(sb.toString());
    }
}
