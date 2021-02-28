package io.github.samuel21119.minecord;

import io.github.samuel21119.minecord.botHandler.botHandler;
import io.github.samuel21119.minecord.botHandler.dispatchMinecraftCommand;
import io.github.samuel21119.minecord.botHandler.listenMinecraftChannel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineCord extends JavaPlugin {

    FileConfiguration config = getConfig();
    String token;
    long channelId;
    @Override
    public void onEnable() {
        // Plugin startup logic
        config.addDefault("discord-token", "token");
        config.addDefault("channel-id", 0);
        config.addDefault("console", false);
        config.addDefault("console-channel-id", "");
        config.options().copyDefaults(true);
        saveConfig();
        token = config.getString("discord-token");
        channelId = config.getLong("channel-id");
        if (token.equals("token") || channelId == 0) {
            getLogger().info("Please configure your discord bot token and channel id");
        }else {
            new botHandler(token, channelId, config.getBoolean("console"), config.getLong("console-channel-id"));
            getServer().getPluginManager().registerEvents(new listenMinecraftChannel(), this);
            new dispatchMinecraftCommand(this);
            getLogger().info("DiscordChat running");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        botHandler.Disable();
    }
}
