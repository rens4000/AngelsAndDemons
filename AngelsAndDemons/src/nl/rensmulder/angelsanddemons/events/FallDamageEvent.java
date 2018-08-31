package nl.rensmulder.angelsanddemons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import nl.rensmulder.angelsanddemons.managers.ArenaManager;

public class FallDamageEvent implements Listener {
	
	private ArenaManager arenaManager;
	
	public FallDamageEvent(ArenaManager arenaManager) {
		this.arenaManager = arenaManager;
	}

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
    	if(e.getEntity() instanceof Player){
    		if(arenaManager.isPlayerInGame(e.getEntity().getName())) {
    			if(e.getCause() == DamageCause.FALL)
    				e.setCancelled(true);
    		}
    	}
    }

}
