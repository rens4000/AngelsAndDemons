package nl.rensmulder.angelsanddemons;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import nl.rensmulder.angelsanddemons.managers.ConfigManager;

/*
 * This plugins has been created by: rens4000(rensmulder.nl) and Skelic/SafeX.
 */

public class Core extends JavaPlugin {
	
	private ConfigManager configManager;
	
	public static String PREFIX;
	
	@Override
	public void onEnable() {
		configManager = new ConfigManager(this);
		configManager.loadDefaultLang();
		PREFIX = ChatColor.translateAlternateColorCodes('&', configManager.getLang().getString("Prefix")) + ChatColor.WHITE + " "; //Initializes Prefix
	}

}
