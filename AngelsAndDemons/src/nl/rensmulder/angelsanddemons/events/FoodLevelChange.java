package nl.rensmulder.angelsanddemons.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import nl.rensmulder.angelsanddemons.managers.ArenaManager;

public class FoodLevelChange implements Listener {
	
	private ArenaManager arenaManager;
	
	public FoodLevelChange(ArenaManager arenaManager) {
		this.arenaManager = arenaManager;
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		if(arenaManager.isPlayerInGame(e.getEntity().getName())) {
			e.setCancelled(true);
			e.setFoodLevel(20);
		}
	}

}
