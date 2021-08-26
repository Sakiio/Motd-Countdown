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

    @EventHandler(priority = 64)
    public void onPlayerPing(ProxyPingEvent event) {
        ServerPing ping = event.getResponse();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("EST"));
            Date date = format.parse(MotdPlugin.getInstance().getConfig().getString("MOTD.TIME-TO-EXPIRE"));
            Date localDate = Calendar.getInstance().getTime();
            long finalR = date.getTime() - localDate.getTime();

            if (MotdPlugin.getInstance().getConfig().getBoolean("MOTD.STATUS")) {
                if (MotdPlugin.getInstance().getConfig().getString("MOTD.MOTD-EDIT") == null){
                    System.out.println("Set Motd in config.yml");
                    return;
                }

                String motd = CC.translate(MotdPlugin.getInstance().getConfig().getString("MOTD.MOTD-EDIT").replace("︱", "\u2503").replace("%ARROW_1", "\u27a5"));
                motd = motd.replace("%NEWLINE%", "\n");
                motd = motd.replace("%D_ARROW%", "\u00BB");
                motd = motd.replace("%TIME%", new DateUtils().getDate(finalR));
                ping.setDescription(motd);
                event.setResponse(ping);
            }
        } catch (ParseException e) {
            MotdPlugin.getInstance().getProxy().getConsole().sendMessage(CC.translate("&cError bad format time"));
        }
    }
}
