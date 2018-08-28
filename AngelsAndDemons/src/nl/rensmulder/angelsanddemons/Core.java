package nl.rensmulder.angelsanddemons;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import nl.rensmulder.angelsanddemons.events.PlayerLeaveEvent;
import nl.rensmulder.angelsanddemons.managers.ArenaManager;
import nl.rensmulder.angelsanddemons.managers.ConfigManager;

/*
 * This plugins has been created by: rens4000(rensmulder.nl) and Skelic/SafeX.
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
