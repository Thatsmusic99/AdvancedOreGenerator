package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.PermissionEnums;
import io.github.thatsmusic99.og.util.PagedLists;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpMenu {

    public static String helpNoArgs(CommandSender sender) {
        List<PermissionEnums> perms = new ArrayList<>();
        for (PermissionEnums key : PermissionEnums.values()) {
            if (sender.hasPermission(key.perm)) {
                perms.add(key);
            }
        }
        PagedLists pl = new PagedLists(perms, 5);
        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        sb.append(ChatColor.AQUA).append("Help menu: ").append(ChatColor.GREEN).append("Page ").append(ChatColor.AQUA).append("1").append(ChatColor.GREEN).append("/").append(ChatColor.AQUA).append(pl.getTotalPages()).append("\n");

        for (Object key2 : pl.getContentsInPage(1)) {
            PermissionEnums key = (PermissionEnums) key2;
            sb.append(ChatColor.GREEN).append("/aog ").append(key.cmd).append(" - ").append(ChatColor.AQUA).append(key.desc).append("\n");
        }

        return sb.toString();
    }

    public static String help(CommandSender cs, int page) {
        List<PermissionEnums> perms = new ArrayList<>();
        for (PermissionEnums key : PermissionEnums.values()) {
            if (cs.hasPermission(key.perm)) {
                perms.add(key);
            }
        }
        PagedLists pl = new PagedLists(perms, 5);
        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        sb.append(ChatColor.AQUA).append("Help menu: ").append(ChatColor.GREEN).append("Page ").append(ChatColor.AQUA).append(page).append(ChatColor.GREEN).append("/").append(ChatColor.AQUA).append(pl.getTotalPages()).append("\n");

        for (Object key2 : pl.getContentsInPage(page)) {
            PermissionEnums key = (PermissionEnums) key2;
            sb.append(ChatColor.GREEN).append("/aog ").append(key.cmd).append(" - ").append(ChatColor.AQUA).append(key.desc).append("\n");
        }

        return sb.toString();
    }

}
