package com.github.sakiio.motdplugin.utils;

import com.github.sakiio.motdplugin.MotdPlugin;

import java.util.concurrent.TimeUnit;

/**
 * Project: Motd
 * Date: 26/08/2021 @ 17:45
 * Class: DateUtils
 */
public class DateUtils {
    public String getDate(long finalL) {
        if (finalL > 0L) {
            StringBuilder builder = new StringBuilder();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(finalL);
            long days = TimeUnit.MILLISECONDS.toDays(finalL);
            long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24);
            long minutes = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
            long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
            if (days > 0L) {
                if (days == 1L) {
                    builder.append(days + "d ");
                } else {
                    builder.append(days + "d ");
                }
                if (hours == 1L) {
                    builder.append(hours + "h ");
                } else {
                    builder.append(hours + "h ");
                }
                if (minutes == 1L) {
                    builder.append(minutes + "m ");
                } else {
                    builder.append(minutes + "m ");
                }
                if (seconds == 1L) {
                    builder.append(second + "s ");
                } else {
                    builder.append(second + "s ");
                }
            } else if (hours > 0L) {
                if (hours == 1L) {
                    builder.append(hours + "h ");
                } else {
                    builder.append(hours + "h ");
                }
                if (minutes == 1L) {
                    builder.append(minutes + "m ");
                } else {
                    builder.append(minutes + "m ");
                }
                if (seconds == 1L) {
                    builder.append(second + "s ");
                } else {
                    builder.append(second + "s ");
                }
            } else if (minutes > 0L) {
                if (minutes == 1L) {
                    builder.append(minutes + "m ");
                } else {
                    builder.append(minutes + "m ");
                }
                if (seconds == 1L) {
                    builder.append(second + "s ");
                } else {
                    builder.append(second + "s ");
                }
            } else if(seconds > 0L) {
                if(seconds == 1L) {
                    builder.append(second + "s ");
                } else {
                    builder.append(second + "s ");
                }
            } else {
                return CC.translate( MotdPlugin.getInstance().getConfig().getString("MOTD.TEXT-WHEN-EXPIRE"));
            }
            return builder.toString();
        } else {
            return CC.translate(MotdPlugin.getInstance().getConfig().getString("MOTD.TEXT-WHEN-EXPIRE"));
        }
    }
}
