package com.teerut.OreTools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(),this);
        System.out.println(ChatColor.AQUA + "OreTools : onEnable");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.AQUA + "OreTools : onDisable");
        super.onDisable();
    }
}
