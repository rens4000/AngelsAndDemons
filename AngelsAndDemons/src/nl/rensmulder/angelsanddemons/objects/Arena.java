package nl.rensmulder.angelsanddemons.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.managers.UserManager;
import nl.rensmulder.angelsanddemons.utilities.ChatUtilities;
import nl.rensmulder.angelsanddemons.utilities.GameState;
import nl.rensmulder.angelsanddemons.utilities.Team;

public class Arena {
	
	private int minPlayers, maxPlayers;
	private String name;
	private List<String> waitingPlayers = new ArrayList<String>();
	private List<User> users = new ArrayList<User>();
	private Location lobby, spawnAngels, spawnDemons;
	private int countdown = 30, gameduration = 300;
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
		if(state == GameState.STARTED)
			if(numDemons() == 0) {
				delayedStop();
				ChatUtilities.broadcastUsers(users, "All the demons were too scared to play. The " + ChatColor.AQUA + "Angels" + ChatColor.WHITE + " win!");
			}
		
		Player p = Bukkit.getPlayer(name);
		p.getInventory().clear();
		p.teleport(userManager.getLocations().get(p.getName()));
		p.getInventory().setContents(userManager.getInventories().get(p.getName()));
		p.getInventory().setArmorContents(userManager.getArmor().get(p.getName()));
		if(p.hasPotionEffect(PotionEffectType.SPEED)) {
			p.removePotionEffect(PotionEffectType.SPEED);
		}
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
	
	private boolean canBeEnabled() {
		return lobby != null && spawnAngels != null && spawnDemons != null;
	}
	
	@SuppressWarnings("deprecation")
	private void start() {
		String demonsName = getDemonsName();
		Player p = Bukkit.getPlayer(demonsName);
		List<String> temp = waitingPlayers;
		temp.remove(demonsName);
		User demon = new User(p.getUniqueId(), Team.DEMONS);
		users.add(demon);
		p.sendMessage(Core.PREFIX + ChatColor.WHITE + "You have been made an " + ChatColor.RED + " Demon" + ChatColor.WHITE + ""
				+ ". You have to kill as much demons as you can.");
		p.sendTitle(ChatColor.RED + "DEMON", null);
		for(String s : temp) {
			Player pl = Bukkit.getPlayer(s);
			User u = new User(pl.getUniqueId(), Team.ANGELS);
			users.add(u);
			pl.sendMessage(Core.PREFIX + ChatColor.WHITE + "You have been made an "
					+ "" + ChatColor.AQUA + " Angel" + ChatColor.WHITE + ". Try to keep out of the hands of the demons to win this match!");
			pl.sendTitle(ChatColor.AQUA + "ANGEL", null);
		}
		waitingPlayers.clear();
		state = GameState.STARTED;
		new BukkitRunnable() {

			@Override
			public void run() {
				if(state != GameState.STARTED)
					cancel();
				gameduration--;
				if(gameduration % 50 == 0 || gameduration == 20 || gameduration == 15 || gameduration == 10 || gameduration == 5) {
					ChatUtilities.broadcastUsers(users, ChatColor.RED + "Only " + gameduration + " seconds left until the match ends!");
				}
				if(gameduration == 0) {
					ChatUtilities.broadcastUsers(users, "The " + ChatColor.AQUA + " Angels " + ChatColor.WHITE + " have won this match!");
					ChatUtilities.broadcastTitleUsers(users, ChatColor.AQUA + "The Angels", ChatColor.WHITE + "won the match!");
					delayedStop();
				}
			}

		}.runTaskTimerAsynchronously(core, 0, 20);
	}
	
	public void giveDemonStuff(Player p) {
		giveSword(p);
		giveTeleportCompass(p);
		giveSpeedBoost(p);
	}
	
	private void giveSpeedBoost(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
		
	}
	
	private void giveTeleportCompass(Player p) {
		ItemStack i = new ItemStack(Material.COMPASS);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Teleport Compass");
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.WHITE + "Teleport to a random Player");
		lores.add(ChatColor.DARK_RED + "Cooldown: " + ChatColor.WHITE + " 20 seconds");
		im.setLore(lores);
		i.setItemMeta(im);
		p.getInventory().addItem(i);
	}
	
	private void giveSword(Player p) {
		ItemStack i = new ItemStack(Material.WOOD_SWORD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Angel slayer");
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.WHITE + "Murder an Angel");
		lores.add(ChatColor.DARK_RED + "Cooldown: " + ChatColor.WHITE + " None except after teleport(5s)");
		im.setLore(lores);
		i.setItemMeta(im);
		p.getInventory().addItem(i);
	}

	private void delayedStop() {
		state = GameState.RESETTING;
		new BukkitRunnable() {

			@Override
			public void run() {
				stop();
			}
			
		}.runTaskLater(core, 100);
	}
	
	public void demonsWin() {
		ChatUtilities.broadcastTitleUsers(users, ChatColor.RED + "The Demons", ChatColor.WHITE + "won the match!");
	}
	
	private void stop() {
		for(User u : users) {
			leave(Bukkit.getPlayer(u.getUuid()).getName());
		}
		reset();
	}
	
	private void reset() {
		users.clear();
		countdown = 30;
		gameduration = 300;
		state = GameState.WAITING;
	}

	public User getUser(String name) {
		Player p = Bukkit.getPlayer(name);
		for(User u : users) {
			if(u.getUuid().equals(p.getUniqueId())) return u;
		}
		return null;
	}
	
	//TODO: RESET VOID
	
	//GETTERS AND SETTERS
	
	private int numDemons() {
		int i = 0;
		for(User u : users) {
			if(u.getTeam() == Team.DEMONS) i++;
		}
		return i;
	}
	
	private String getDemonsName() {
		Random r = new Random();
		return waitingPlayers.get(r.nextInt(waitingPlayers.size()-1));
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
