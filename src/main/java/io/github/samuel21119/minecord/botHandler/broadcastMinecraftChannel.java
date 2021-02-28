package io.github.samuel21119.minecord.botHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class broadcastMinecraftChannel {
    public broadcastMinecraftChannel(String player, String msg) {
        String discord = ChatColor.AQUA + "Discord" + ChatColor.WHITE;
        Bukkit.getServer().broadcastMessage("<" + player + "> " + msg + "  [" + discord + "]");
    }
}
