package com.github.sakiio.motdplugin.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

/**
 * Project: Motd
 * Date: 23/08/2021 @ 13:18
 * Class: CC
 */
public class CC {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> translate(List<String> message) {
        message.forEach(lines -> {
            ChatColor.translateAlternateColorCodes('&', lines);
        });
        return message;
    }
}
