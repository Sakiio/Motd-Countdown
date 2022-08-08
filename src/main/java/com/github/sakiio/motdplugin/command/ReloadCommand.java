package com.github.sakiio.motdplugin.command;

import com.github.sakiio.motdplugin.MotdPlugin;
import com.github.sakiio.motdplugin.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Project: Motd
 * Date: 26/08/2021 @ 17:54
 * Class: ReloadCommand
 */
public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("breload", "bungee.manager");
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        if (sender.hasPermission(getPermission()))
            return;


        MotdPlugin.getInstance().reloadConfig();
        sender.sendMessage(CC.translate("&3&lConfig has been reload!"));
    }
}
