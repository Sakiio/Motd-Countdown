package com.github.sakiio.motdplugin.utils;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by DevSakio
 * Project: Nucleus
 * Date: 08/01/2021 @ 12:27
 * Class: ConfigManager
 */
public class ConfigManager {

    public static File startConfig(Plugin plugin, String file) {
        File folder = plugin.getDataFolder();
        if (!folder.exists()) {
            folder.mkdir();
        }
        File resourceFile = new File(folder, file);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResourceAsStream(file);
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resourceFile;
    }
}
