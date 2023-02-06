package cg.creamgod45;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class Script extends JavaPlugin {
    public static Logger logger;
    public static Map<String, Boolean> HookingPlugins = new HashMap<>();
    public static Script plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        logger = getServer().getLogger();

        Utils.ConsoleInfoPrint("-> &ePlaceholderAPI 載入...", 10);
        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderAPI != null && placeholderAPI.isEnabled()) {
            HookingPlugins.put(placeholderAPI.getName(), true);
            PlaceholderAPI.isRegistered("test");
            Utils.ConsoleInfoPrint("-> &ePlaceholderAPI 載入... &a完成", 10);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
