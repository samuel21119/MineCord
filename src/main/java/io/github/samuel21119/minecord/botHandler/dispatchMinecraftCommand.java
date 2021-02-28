package io.github.samuel21119.minecord.botHandler;


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class dispatchMinecraftCommand {
    public static Plugin MineCord;
    public dispatchMinecraftCommand(Plugin plugin) {
        MineCord = plugin;
    }
    public static void Run(String cmd) {
        while (cmd.length() > 0 && (cmd.charAt(0) == '/' || cmd.charAt(0) == ' '))
            cmd = cmd.substring(1);
        String finalCmd = cmd;
        try {
            boolean success = Bukkit.getScheduler().callSyncMethod((Plugin) MineCord, new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    return Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), finalCmd);
                }
            } ).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
