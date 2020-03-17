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

        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append(ChatColor.GRAY).append("Help Menu").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append("Page ").append(ChatColor.GREEN).append("1").append(ChatColor.DARK_GRAY).append("/").append(ChatColor.GREEN).append(pl.getTotalPages()).append("\n");

        for (Object key2 : pl.getContentsInPage(1)) {
            PermissionEnums key = (PermissionEnums) key2;
            sb.append(ChatColor.GREEN).append("/aog ").append(key.cmd).append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append(key.desc).append("\n");
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

        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        sb.append(ChatColor.GRAY).append("Help Menu").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append("Page ").append(ChatColor.GREEN).append(page).append(ChatColor.DARK_GRAY).append("/").append(ChatColor.GREEN).append(pl.getTotalPages()).append("\n");

        for (Object key2 : pl.getContentsInPage(page)) {
            PermissionEnums key = (PermissionEnums) key2;
            sb.append(ChatColor.GREEN).append("/aog ").append(key.cmd).append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GRAY).append(key.desc).append("\n");
        }

        return sb.toString();
    }

}
