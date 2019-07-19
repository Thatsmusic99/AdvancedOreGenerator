package io.github.thatsmusic99.og;

import io.github.thatsmusic99.og.maincommand.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command c, String s, String[] args) {
        if (c.getName().equalsIgnoreCase("aog")) {
            if (cs.hasPermission("aog.command")) {
                if (args.length == 0) {
                    if (cs.hasPermission("aog.view.help")) {
                        cs.sendMessage(HelpMenu.helpNoArgs(cs));
                        return true;
                    }

                } else {

                    switch (args[0].toLowerCase()) {
                        case "worlds":
                            if (cs.hasPermission("aog.view.worlds")) {
                                if (args.length > 1) {
                                    if (args[1].matches("^[0-9]+$")) {
                                        try {
                                            cs.sendMessage(AllowedWorlds.allowedWorlds(Integer.parseInt(args[1])));
                                            return true;
                                        } catch (IllegalArgumentException ex) {
                                            cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Invalid page number!");
                                            return true;
                                        }
                                    } else {
                                        cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Invalid arguments! Do /aog worlds [Page #]");
                                        return true;
                                    }
                                } else {
                                    cs.sendMessage(AllowedWorlds.allowedWorldsNoArgs());
                                    return true;
                                }
                            }
                        case "dworlds":
                            if (cs.hasPermission("aog.view.disabled")) {
                                if (args.length > 1) {
                                    if (args[1].matches("^[0-9]+$")) {
                                        try {
                                            cs.sendMessage(DisabledWorlds.dWorlds(Integer.parseInt(args[1])));
                                            return true;
                                        } catch (IllegalArgumentException ex) {
                                            cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Invalid page number!");
                                            return true;
                                        }
                                    } else {
                                        cs.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Invalid arguments! Do /aog worlds [Page #]");
                                        return true;
                                    }
                                } else {
                                    cs.sendMessage(DisabledWorlds.dWorldsNoArgs());
                                    return true;
                                }
                            }
                        case "help":
                            if (cs.hasPermission("aog.view.help")) {
                                if (args.length > 1) {
                                    if (args[1].matches("^[0-9]+$")) {
                                        cs.sendMessage(HelpMenu.help(cs, Integer.parseInt(args[1])));
                                        return true;
                                    }
                                } else {
                                    cs.sendMessage(HelpMenu.helpNoArgs(cs));
                                    return true;
                                }
                            }
                        case "reload":
                            if (cs.hasPermission("aog.reload")) {
                                Reload.reload(cs);
                                return true;
                            }
                        case "addcustom":
                            if (cs.hasPermission("aog.add.custom")) {
                                if (args.length > 1) {
                                    AddCustom.addCustom(args[1], cs);
                                    return true;
                                } else {
                                    cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Not enough arguments! Do /aog addcustom <World name>");
                                    return true;
                                }
                            }
                        case "world":
                            if (cs.hasPermission("aog.view.world")) {
                                if (args.length > 1) {
                                    cs.sendMessage(WorldInfo.getWorldInfo(args[1]));
                                    return true;
                                } else {
                                    cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Not enough arguments! Do /aog world <World name>");
                                    return true;
                                }
                            }
                        case "delcustom":
                            if (cs.hasPermission("aog.del.custom")) {
                                if (args.length > 1) {
                                    DelCustom.delCustom(args[1], cs);
                                    return true;
                                } else {
                                    cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Not enough arguments! Do /aog delcustom <World name>");
                                    return true;
                                }
                            }
                        case "disable":
                            if (cs.hasPermission("aog.disable")) {
                                if (args.length > 1) {
                                    AddWorld.disable(args[1], cs);
                                    return true;
                                } else {
                                    cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Not enough arguments! Do /aog disable <World name>");
                                    return true;
                                }
                            }
                        case "enable":
                            if (cs.hasPermission("aog.enable")) {
                                if (args.length > 1) {
                                    DelWorld.enable(args[1], cs);
                                    return true;
                                } else {
                                    cs.sendMessage("[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "Not enough arguments! Do /aog disable <World name>");
                                    return true;
                                }
                            }
                        case "info":
                            if (cs.hasPermission("aog.info")) {
                                InfoCommand.info(cs);
                                return true;
                            }
                        default:
                            if (args[0].matches("^[0-9]+$")) {
                                if (cs.hasPermission("aog.view.help")) {
                                    cs.sendMessage(HelpMenu.help(cs, Integer.parseInt(args[0])));
                                    return true;
                                }
                            } else if (cs.hasPermission("aog.view.help")) {
                                cs.sendMessage(HelpMenu.helpNoArgs(cs));
                                return true;
                            }

                    }
                }
            }
        }
        return false;
    }
}
