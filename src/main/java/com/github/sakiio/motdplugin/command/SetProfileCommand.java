package com.github.sakiio.motdplugin.command;

import com.github.sakiio.motdplugin.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class SetProfileCommand extends Command {
    private String profile;

    public SetProfileCommand() {
        super("motd", "bungee.manager");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage(CC.translate("&cYou dont have perms"));
            return;
        }
        if (strings.length == 0) {
            commandSender.sendMessage(CC.translate("&cUsage: /motd <profile>"));
        } else {
            profile = strings[1];
        }
    }

    public String getProfile(){
        return profile;
    }
}
