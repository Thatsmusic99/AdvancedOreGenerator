package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.Tier;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class WorldInfo {

    public static String getWorldInfo(String worldname) {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GREEN).append("----------- ").append(ChatColor.AQUA).append("[").append(ChatColor.GREEN).append("AdvancedOreGenerator").append(ChatColor.AQUA).append("]").append(ChatColor.GREEN).append(" -----------\n");
        if (AOGWorld.getWorld(worldname) != null) {
            AOGWorld w = AOGWorld.getWorld(worldname);
            assert w != null;
            sb.append(ChatColor.GREEN).append("World: ").append(ChatColor.AQUA).append(worldname).append("\n");
            sb.append(ChatColor.GREEN).append("Enabled: ").append(ChatColor.AQUA).append(String.valueOf(w.getAogSettings().isEnabled())).append("\n");
            sb.append(ChatColor.GREEN).append("Custom: ").append(ChatColor.AQUA).append(String.valueOf(w.getAogSettings().isCustom())).append("\n");
            for (Tier t : w.getAogSettings().getTiers()) {
                sb.append(ChatColor.GREEN).append(WordUtils.capitalize(t.getName())).append(" chance | blocks: ").append(ChatColor.AQUA).append(t.getChance()).append(" | ");
                for (Material m : t.getBlocks()) {
                    sb.append(m.name()).append(", ");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("\n");
            }
            return sb.toString();
        }
        return "[" + ChatColor.GREEN + "AdvancedOreGenerator" + ChatColor.AQUA + "] " + ChatColor.RED + "No world data was found!";
    }
}
