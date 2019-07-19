package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.OreGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class InfoCommand {

    public static void info(CommandSender cs) {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------");
        sb.append("\n").append(ChatColor.GREEN).append("Name: ").append(ChatColor.AQUA).append(OreGenerator.getInstance().getDescription().getName());
        sb.append("\n").append(ChatColor.GREEN).append("Author: ").append(ChatColor.AQUA).append(OreGenerator.getInstance().getDescription().getAuthors().get(0));
        cs.sendMessage(sb.toString());
    }
}
