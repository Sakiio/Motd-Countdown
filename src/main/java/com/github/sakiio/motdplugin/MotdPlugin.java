package com.github.sakiio.motdplugin;

import com.github.sakiio.motdplugin.command.MotdEditCommand;
import com.github.sakiio.motdplugin.command.ReloadCommand;
import com.github.sakiio.motdplugin.listener.MotdListener;
import com.github.sakiio.motdplugin.utils.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public final class MotdPlugin extends Plugin {
    private static MotdPlugin instance;
    private static Configuration config;

    @Override
    public void onEnable() {
        this.onConfig();
        this.reloadConfig();

        System.out.println(Calendar.getInstance().getTime() + "\n" + "this is a vps time");

        getProxy().getPluginManager().registerListener(this, new MotdListener());
        getProxy().getPluginManager().registerCommand(this, new MotdEditCommand());
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand());
    }

    @Override
    public void onDisable() { }

    public void onConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(ConfigManager.startConfig(this, "config.yml"));
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(getConfig(), new File(this.getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to save configuration", e);
        }
    }

    public void reloadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to load configuration", e);
        }
    }

    public static MotdPlugin getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }
}
