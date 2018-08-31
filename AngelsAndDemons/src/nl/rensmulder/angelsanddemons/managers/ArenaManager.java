package nl.rensmulder.angelsanddemons.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
	
	public Arena getArenaByPlayer(String playerName) {
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
	
	public Arena getArena(String arena) {
		for(Arena a : arenas) {
			if(a.getName().equals(arena))
				return a;
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
			int min = data.getInt("arenas." + s + ".min");
			int max = data.getInt("arenas." + s + ".max");
			boolean enabled = data.getBoolean("arenas." + s + ".enabled");
			Arena a = new Arena(s, min, max, core, userManager, enabled);
			if(data.contains("arenas." + s + ".lobby")) {
				int x = data.getInt("arenas." + s + ".lobby.x");
				int y = data.getInt("arenas." + s + ".lobby.y");
				int z = data.getInt("arenas." + s + ".lobby.z");
				String world = data.getString("arenas." + s + ".lobby.world");
				a.setLobby(new Location(Bukkit.getWorld(world),x,y,z));
			}
			if(data.contains("arenas." + s + ".angels")) {
				int x = data.getInt("arenas." + s + ".angels.x");
				int y = data.getInt("arenas." + s + ".angels.y");
				int z = data.getInt("arenas." + s + ".angels.z");
				String world = data.getString("arenas." + s + ".angels.world");
				a.setSpawnAngels(new Location(Bukkit.getWorld(world),x,y,z));
			}
			if(data.contains("arenas." + s + ".demons")) {
				int x = data.getInt("arenas." + s + ".demons.x");
				int y = data.getInt("arenas." + s + ".demons.y");
				int z = data.getInt("arenas." + s + ".demons.z");
				String world = data.getString("arenas." + s + ".demons.world");
				a.setSpawnDemons(new Location(Bukkit.getWorld(world),x,y,z));
			}
			arenas.add(a);
		}
		core.getLogger().info(loaded + " arenas were loaded!");
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
