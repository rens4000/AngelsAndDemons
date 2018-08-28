package nl.rensmulder.angelsanddemons.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.Arena;
import nl.rensmulder.angelsanddemons.objects.User;

public class ArenaManager {
	
	private Core core;
	
	private List<Arena> arenas = new ArrayList<>();
	
	public ArenaManager(Core core) {
		this.core = core;
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

}
