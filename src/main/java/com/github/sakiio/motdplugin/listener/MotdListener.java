package com.github.sakiio.motdplugin.listener;

import com.github.sakiio.motdplugin.MotdPlugin;
import com.github.sakiio.motdplugin.utils.CC;
import com.github.sakiio.motdplugin.utils.DateUtils;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MotdListener implements Listener {
    private final MotdPlugin plugin;
    private static final SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public MotdListener(MotdPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = 64)
    public void onPlayerPing(ProxyPingEvent event) {
        ServerPing ping = event.getResponse();

        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone(plugin.getInstance().getConfig().getString("MOTD.TIME-ZONE")));
            Date date = dateFormat.parse(plugin.getInstance().getConfig().getString("MOTD.TIME-TO-EXPIRE"));
            Date localDate = Calendar.getInstance().getTime();

            long finalR = date.getTime() - localDate.getTime();

            if (plugin.getInstance().getConfig().getBoolean("MOTD.STATUS")) {
                if (plugin.getInstance().getConfig().getString("MOTD.MOTD-EDIT") == null){
                    System.out.println("Error -> Set MOTD-EDIT in config.yml");
                    return;
                }

                String motd = CC.translate(
                        plugin.getInstance().getConfig().getString("MOTD.MOTD-EDIT")
                        .replace("︱", "\u2503")
                        .replace("%ARROW_1", "\u27a5")
                        .replace("%NEWLINE%", "\n")
                        .replace("%D_ARROW%", "\u00BB")
                        .replace("%TIME%", new DateUtils().getDate(finalR)));

                ping.setDescription(motd);
                event.setResponse(ping);
            }
        } catch (ParseException e) {
            System.out.println("Error -> Change the time format in config.yml");
        }
    }
}
