package com.github.sakiio.motdplugin.command;

import com.github.sakiio.motdplugin.MotdPlugin;
import com.github.sakiio.motdplugin.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Project: Motd
 * Date: 23/08/2021 @ 13:25
 * Class: MotdEditCommand
 */
public class MotdEditCommand extends Command {

    public MotdEditCommand() {
        super("bmotd");
    }

    public void execute(CommandSender sender, final String[] args) {
        if (sender.hasPermission(getPermission())) {
            sender.sendMessage(CC.translate("&cYou dont have perms"));
            return;
        }
        if (args.length == 0) {
            sender.sendMessage(CC.translate("&cUsage: /bmotd <text> - %NEWLINE% new line"));
        } else {
            final StringBuilder message = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                message.append(args[i]).append(" ");
            }
            MotdPlugin.getInstance().getConfig().set("MOTD.MOTD-EDIT", message.toString());
            MotdPlugin.getInstance().saveConfig();
            sender.sendMessage(CC.translate("&eYou have updated motd to: " + CC.translate(message.toString())));
        }
    }
}
