package nl.rensmulder.angelsanddemons;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import nl.rensmulder.angelsanddemons.commands.AngelsAndDemonsCMD;
import nl.rensmulder.angelsanddemons.events.FallDamageEvent;
import nl.rensmulder.angelsanddemons.events.FoodLevelChange;
import nl.rensmulder.angelsanddemons.events.PlayerLeaveEvent;
import nl.rensmulder.angelsanddemons.events.PlayerRespawnEvent;
import nl.rensmulder.angelsanddemons.managers.ArenaManager;
import nl.rensmulder.angelsanddemons.managers.ConfigManager;
import nl.rensmulder.angelsanddemons.managers.UserManager;

/*
 * This plugins has been created by: rens4000(rensmulder.nl) and SKELIC.
 */

public class Core extends JavaPlugin {
	
	private ConfigManager configManager;
	private ArenaManager arenaManager;
	private UserManager userManager;
	
	public static String PREFIX;
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		
		/* Initialization */
		configManager = new ConfigManager(this);
		configManager.loadDefaultLang();
		userManager = new UserManager();
		arenaManager = new ArenaManager(this, configManager, userManager);
		angelsAndDemonsCMD = new AngelsAndDemonsCMD(this);
				
		/* Prefix initialization */
		PREFIX = ChatColor.translateAlternateColorCodes('&', configManager.getLang().getString("Prefix")) + ChatColor.WHITE + " "; 
		
		/* Registering events */
		pm.registerEvents(new PlayerLeaveEvent(arenaManager), this); 
		pm.registerEvents(new PlayerRespawnEvent(arenaManager, this), this);
		pm.registerEvents(new FoodLevelChange(arenaManager), this);
		pm.registerEvents(new FallDamageEvent(arenaManager), this);
		
		/* Registering commands */
		getCommand("angelsanddemons").setExecutor(angelsAndDemonsCMD);
		
		/* Loading the arenas */
		arenaManager.loadArenas();
		
		/* Saying message in config */
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----{Angels and Demons Plugin}----");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "      Created by: rens4000    " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "                  SKELIC      " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "      Plugin Status: Enabled     " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----------------------------------");
	}
	
	
	@Override
	public void onDisable() {
		/* Saying message in config */
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----{Angels and Demons Plugin}----");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "      Created by: rens4000    " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "                  SKELIC      " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "|" + ChatColor.RED + "    Plugin Status: Disabled   " + ChatColor.DARK_RED + "|");
		Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.DARK_RED + "-----------------------------------");
	}
	
	/* Getters */

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
