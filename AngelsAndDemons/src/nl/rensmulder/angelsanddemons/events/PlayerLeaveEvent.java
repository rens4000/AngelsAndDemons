package nl.rensmulder.angelsanddemons.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import nl.rensmulder.angelsanddemons.managers.ArenaManager;

public class PlayerLeaveEvent implements Listener {
	
	private ArenaManager arenaManager;
	
	public PlayerLeaveEvent(ArenaManager arenaManager) {
		this.arenaManager = arenaManager;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if(arenaManager.isPlayerInGame(e.getPlayer().getName())) {
			arenaManager.getArena(e.getPlayer().getName()).leave(e.getPlayer().getName());
		}
	}

}
