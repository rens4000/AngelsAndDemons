package nl.rensmulder.angelsanddemons.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.managers.UserManager;
import nl.rensmulder.angelsanddemons.utilities.ChatUtilities;
import nl.rensmulder.angelsanddemons.utilities.GameState;

public class Arena {
	
	private int minPlayers, maxPlayers;
	private String name;
	private List<String> waitingPlayers = new ArrayList<String>();
	private List<User> users = new ArrayList<User>();
	private Location lobby, spawnAngels, spawnDemons;
	private int countdown = 30;
	private Core core;
	private GameState state;
	private UserManager userManager;
	private boolean enabled;
	
	public Arena(String name, int minPlayers, int maxPlayers, Core core, UserManager userManager, boolean enabled) {
		this.core = core;
		this.name = name;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		state = GameState.WAITING;
		this.userManager = userManager;
		this.enabled = enabled;
	}
	
	public void join(String playername) {
		Player p = Bukkit.getPlayer(playername);
		waitingPlayers.add(playername);
		p.setGameMode(GameMode.SURVIVAL);
		userManager.getInventories().put(playername, p.getInventory().getContents());
		userManager.getArmor().put(playername, p.getInventory().getArmorContents());
		userManager.getLocations().put(playername, p.getLocation());
		p.getInventory().clear();
		p.teleport(lobby);
		startCountdown();
	}
	
	private void startCountdown() {
		if(hasEnoughPlayers()) {
			ChatUtilities.broadcast(waitingPlayers, "Enough players have joined. Countdown has begun!");
			state = GameState.STARTING;
			countdown();
		}
	}

	public void leave(String name) {
		
	}
	
	public void countdown() {
		new BukkitRunnable() {

			@Override
			public void run() {
				if(state != GameState.STARTING) {
					cancel();
					return;
				}
				countdown--;
				if(!hasEnoughPlayers()) {
					ChatUtilities.broadcast(waitingPlayers, ChatColor.RED + "Countdown has been canceled due to a lack of players in the lobby.");
					state = GameState.WAITING;
					countdown = 30;
					cancel();
				}
				switch(countdown) {
					case 30:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 20:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" +  countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 15:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 10:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 5:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 4:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 3:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 2:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 1:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "" + countdown + ChatColor.WHITE + " seconds left until the game starts!");
						break;
					case 0:
						ChatUtilities.broadcast(waitingPlayers, ChatColor.AQUA + "The game " + ChatColor.RED + " has begun");
						state = GameState.STARTED;
						start();
						cancel();
						break;
				}
			}
			
		}.runTaskTimerAsynchronously(core, 0, 20);
	}
	
	private void start() {
		//TODO: Wat er gebeurt als de game start.
	}
	
	private void stop() {
		//TODO: Wat er gebeurt als de game stopt.
	}
	
	private boolean hasEnoughPlayers() {
		return minPlayers <= waitingPlayers.size();
	}
	
	public boolean hasTooManyPlayers() {
		return maxPlayers >= waitingPlayers.size();
	}
	
	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Location getLobby() {
		return lobby;
	}

	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}

	public Location getSpawnAngels() {
		return spawnAngels;
	}

	public void setSpawnAngels(Location spawnAngels) {
		this.spawnAngels = spawnAngels;
	}

	public Location getSpawnDemons() {
		return spawnDemons;
	}

	public void setSpawnDemons(Location spawnDemons) {
		this.spawnDemons = spawnDemons;
	}

	public String getName() {
		return name;
	}

	public List<String> getWaitingPlayers() {
		return waitingPlayers;
	}

	public List<User> getUsers() {
		return users;
	}

	public int getCountdown() {
		return countdown;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
