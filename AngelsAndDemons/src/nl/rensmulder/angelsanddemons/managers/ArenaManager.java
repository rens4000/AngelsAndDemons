package nl.rensmulder.angelsanddemons.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.Arena;
import nl.rensmulder.angelsanddemons.objects.User;

public class ArenaManager {
	
	private Core core;
	private ConfigManager configManager;
	
	private List<Arena> arenas = new ArrayList<>();
	private UserManager userManager;
	
	public ArenaManager(Core core, ConfigManager configManager, UserManager userManager) {
		this.core = core;
		this.configManager = configManager;
		this.userManager = userManager;
	}
	
	public boolean isPlayerInGame(String name) {
		Player p = Bukkit.getPlayer(name);
		for(Arena a : arenas) {
			if(a.getWaitingPlayers().contains(name))
				return a.getWaitingPlayers().contains(name);
			for(User u : a.getUsers()) {
				return u.getUuid().equals(p.getUniqueId());
			}
		}
		return false;
	}
	
	public Arena getArena(String playerName) {
		Player p = Bukkit.getPlayer(playerName);
		for(Arena a : arenas) {
			if(a.getWaitingPlayers().contains(playerName))
				return a;
			for(User u : a.getUsers()) {
				if(u.getUuid().equals(p.getUniqueId())) return a;
			}
		}
		return null;
	}
	
	public void loadArenas() {
		FileConfiguration data = configManager.getData();
		if(!data.contains("arenas")) {
			core.getLogger().info("Empty data-file detected! No arenas were loaded!");
			return;
		}
		int loaded = 0;
		for(String s : data.getConfigurationSection("arenas").getKeys(false)) {
			loaded++;
			//LOAD ARENA
		}
	}
	
	public void createNewArena(String name) {
		final int min = configManager.getConfig().getInt("settings.default-min-players");
		final int max = configManager.getConfig().getInt("settings.default-max-players");
		Arena a = new Arena(name, min, max, core, userManager, false);
		arenas.add(a);
		FileConfiguration data = configManager.getData();
		data.set("arenas." + name + ".min", min);
		data.set("arenas." + name + ".max", max);
		data.set("arenas." + name + ".enabled", false);
		configManager.saveData();
	}

}
