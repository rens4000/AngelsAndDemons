package nl.rensmulder.angelsanddemons;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import nl.rensmulder.angelsanddemons.events.PlayerLeaveEvent;
import nl.rensmulder.angelsanddemons.managers.ArenaManager;
import nl.rensmulder.angelsanddemons.managers.ConfigManager;

/*
 * This plugins has been created by: rens4000(rensmulder.nl) and SKELIC.
 */

public class Core extends JavaPlugin {
	
	private ConfigManager configManager;
	private ArenaManager arenaManager;
	
	public static String PREFIX;
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		configManager = new ConfigManager(this);
		configManager.loadDefaultLang();
		arenaManager = new ArenaManager(this);
		PREFIX = ChatColor.translateAlternateColorCodes('&', configManager.getLang().getString("Prefix")) + ChatColor.WHITE + " "; //Initializes Prefix
		pm.registerEvents(new PlayerLeaveEvent(arenaManager), this); 
		
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----{Angels and Demons Plugin}----");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "      Created by: rens4000    " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "                  SKELIC      " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "      Plugin Status: Enabled     " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----------------------------------");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----{Angels and Demons Plugin}----");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "      Created by: rens4000    " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "                  SKELIC      " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "    Plugin Status: Disabled   " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----------------------------------");
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

	public ArenaManager getArenaManager() {
		return arenaManager;
	}

	public void setArenaManager(ArenaManager arenaManager) {
		this.arenaManager = arenaManager;
	}

	
}
