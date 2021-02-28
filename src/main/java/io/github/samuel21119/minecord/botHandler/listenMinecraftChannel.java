package io.github.samuel21119.minecord.botHandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static io.github.samuel21119.minecord.botHandler.botHandler.Send;

public class listenMinecraftChannel implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        String player = event.getPlayer().getDisplayName();
        Send(player, msg);
    }
}
