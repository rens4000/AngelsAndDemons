package nl.rensmulder.angelsanddemons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import nl.rensmulder.angelsanddemons.managers.ArenaManager;
import nl.rensmulder.angelsanddemons.objects.Arena;
import nl.rensmulder.angelsanddemons.objects.User;
import nl.rensmulder.angelsanddemons.utilities.Team;
import nl.rensmulder.angelsanddemons.Core;

public class PlayerRespawnEvent implements Listener {
	
	private ArenaManager arenaManager;
	private Core core;
	
	public PlayerRespawnEvent(ArenaManager arenaManager, Core core) {
		this.arenaManager = arenaManager;
		this.core = core;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (arenaManager.isPlayerInGame(e.getEntity().getName())) {
			Arena a = arenaManager.getArena(e.getEntity().getName());
			User u = a.getUser(e.getEntity().getName());
			Player p = e.getEntity();
			
			new BukkitRunnable() {

				@Override
				public void run() {
					if (p.isDead()) {
						p.teleport(a.getSpawnDemons());
						p.getInventory().clear();
						p.setHealth(20);
						p.setFoodLevel(20);
						
						u.setTeam(Team.DEMONS);
						a.giveDemonStuff(p);
						p.teleport(a.getSpawnDemons());
					}
				}
				
			}.runTaskLater(core, 5L);
			
		}
	}
	
}
