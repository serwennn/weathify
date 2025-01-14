package com.zelevar.weathify;

import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Weathify extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String subCommand = "";
            if (args.length > 0) subCommand = args[0];

            switch (command.getName().toLowerCase()) {
                case "ptime":
                    switch (subCommand.toLowerCase()) {
                        case "day":
                            player.setPlayerTime(1000L, false);
                            player.sendMessage(format("messages.time.day"));
                            break;
                        case "night":
                            player.setPlayerTime(13000L, false);
                            player.sendMessage(format("messages.time.night"));
                            break;
                        case "reset":
                            player.resetPlayerTime();
                            player.sendMessage(format("messages.time.reset"));
                            break;
                        default:
                            return false;
                    }

                    break;
                case "pweather":
                    switch (subCommand.toLowerCase()) {
                        case "clear":
                            player.setPlayerWeather(WeatherType.CLEAR);
                            player.sendMessage(format("messages.weather.clear"));
                            break;
                        case "rain":
                            player.setPlayerWeather(WeatherType.DOWNFALL);
                            player.sendMessage(format("messages.weather.rain"));
                            break;
                        case "reset":
                            player.resetPlayerWeather();
                            player.sendMessage(format("messages.weather.reset"));
                            break;
                        default:
                            return false;
                    }

                    break;
            }
        } else {
            sender.sendMessage(format("messages.errors.non-player"));
        }
        
        return true;
    }

    public String format(String path) {
        String prefix = getConfig().getString("format.prefix");
        String separator = getConfig().getString("format.separator");
        String message = prefix + separator + getConfig().getString(path);

        return message.replace('&', '§');
    }
}
