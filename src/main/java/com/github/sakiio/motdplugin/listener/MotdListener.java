package com.github.sakiio.motdplugin.listener;

import com.github.sakiio.motdplugin.MotdPlugin;
import com.github.sakiio.motdplugin.command.SetProfileCommand;
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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public MotdListener(MotdPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = 64)
    public void onPlayerPing(ProxyPingEvent event) {
        ServerPing ping = event.getResponse();
        String profile = new SetProfileCommand().getProfile();

        try {

            dateFormat.setTimeZone(TimeZone.getTimeZone(plugin.getInstance().getConfig().getString("motd.time-zone")));
            Date date = dateFormat.parse(plugin.getInstance().getConfig().getString("profile." + profile + ".time-to-expire"));
            Date localDate = Calendar.getInstance().getTime();

            if(date == localDate){
                ping.setDescription(plugin.getInstance().getConfig().getString("profile." + profile + ".text-when-expire"));
                event.setResponse(ping);
                return;
            }

            long finalR = date.getTime() - localDate.getTime();

            String motd = CC.translate(
                    plugin.getInstance().getConfig().getString("profile." + profile + ".message")
                            .replace("︱", "\u2503")
                            .replace("%ARROW_1", "\u27a5")
                            .replace("%NEWLINE%", "\n")
                            .replace("%D_ARROW%", "\u00BB")
                            .replace("%TIME%", new DateUtils().getDate(finalR)));

            ping.setDescription(motd);
            event.setResponse(ping);

        } catch (ParseException e) {
            System.out.println("Error -> Change the time format in config.yml");
        }
    }
}
