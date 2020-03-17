package io.github.thatsmusic99.og.maincommand;

import io.github.thatsmusic99.og.Tier;
import io.github.thatsmusic99.og.util.AOGWorld;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class WorldInfo {

    public static String getWorldInfo(String worldname) {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GRAY).append("━━━━━━━━━━━━ ").append(ChatColor.DARK_GRAY).append("❰ ").append(ChatColor.GREEN).append(ChatColor.BOLD).append("AdvancedOreGenerator").append(ChatColor.DARK_GRAY).append(" ❱").append(ChatColor.GRAY).append(" ━━━━━━━━━━━━\n");
        if (AOGWorld.getWorld(worldname) != null) {
            AOGWorld w = AOGWorld.getWorld(worldname);
            assert w != null;
            sb.append(ChatColor.GRAY).append("World").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GREEN).append(worldname).append("\n");
            sb.append(ChatColor.GRAY).append("Enabled ").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GREEN).append(w.getAogSettings().isEnabled()).append("\n");
            sb.append(ChatColor.GRAY).append("Custom ").append(ChatColor.DARK_GRAY).append(" » ").append(ChatColor.GREEN).append(w.getAogSettings().isCustom()).append("\n");
            for (Tier t : w.getAogSettings().getTiers()) {
                sb.append(ChatColor.GRAY).append(WordUtils.capitalize(t.getName())).append(" chance | blocks: ").append(ChatColor.GREEN).append(t.getChance()).append(" | ");
                for (Material m : t.getBlocks()) {
                    sb.append(m.name()).append(", ");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("\n");
            }
            return sb.toString();
        }
        return ChatColor.GREEN + "AdvancedOreGenerator " + ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "No world data was found!";
    }
}
