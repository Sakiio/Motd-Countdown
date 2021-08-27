package com.github.sakiio.motdplugin.utils;

import net.md_5.bungee.api.ChatColor;


/**
 * Project: Motd
 * Date: 23/08/2021 @ 13:18
 * Class: CC
 */
public class CC {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
