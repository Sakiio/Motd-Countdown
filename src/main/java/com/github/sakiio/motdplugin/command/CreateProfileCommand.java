package com.github.sakiio.motdplugin.command;

import com.github.sakiio.motdplugin.manager.ProfileManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CreateProfileCommand extends Command {

    public CreateProfileCommand() {

        super("createmotd", "bungee.create");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

    }
}
