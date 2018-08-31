package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.Arena;

public class SetSpawn extends DefaultCommand {

	public SetSpawn(String name, String description, String usage) {
		super(name, description, usage);
	}

	@Override
	public void run(String[] args, CommandSender sender, AngelsAndDemonsCMD cmd) {
		if(!cmd.hasPermission(sender)) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "You don't have permission to execute that command.");
			return;
		}
		if(args.length != 3) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "/angelsanddemons " + getUsage());
			return;
		}
		if(cmd.getCore().getArenaManager().getArena(args[1]) == null) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena doesn't exist.");
			return;
		}
		if(!args[2].equals("angels") && !args[2].equals("demons") && !args[2].equals("lobby")) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "/angelsanddemons " + getUsage());
			return;
		}
		Player p = (Player) sender;
		if(args[2].equals("angels")) {
			Arena a = cmd.getCore().getArenaManager().getArena(args[1]);
			a.setSpawnAngels(p.getLocation());
			FileConfiguration data = cmd.getCore().getConfigManager().getData();
			data.set("arenas." + a.getName() + ".angels.world", p.getLocation().getWorld().getName());
			data.set("arenas." + a.getName() + ".angels.x", p.getLocation().getBlockX());
			data.set("arenas." + a.getName() + ".angels.y", p.getLocation().getBlockY());
			data.set("arenas." + a.getName() + ".angels.z", p.getLocation().getBlockZ());
			cmd.getCore().getConfigManager().saveData();
			sender.sendMessage(Core.PREFIX + ChatColor.GREEN + "The angels spawn of arena: " + a.getName() + " has been set!");
			return;
		}
		if(args[2].equals("demons")) {
			Arena a = cmd.getCore().getArenaManager().getArena(args[1]);
			a.setSpawnDemons(p.getLocation());
			FileConfiguration data = cmd.getCore().getConfigManager().getData();
			data.set("arenas." + a.getName() + ".demons.world", p.getLocation().getWorld().getName());
			data.set("arenas." + a.getName() + ".demons.x", p.getLocation().getBlockX());
			data.set("arenas." + a.getName() + ".demons.y", p.getLocation().getBlockY());
			data.set("arenas." + a.getName() + ".demons.z", p.getLocation().getBlockZ());
			cmd.getCore().getConfigManager().saveData();
			sender.sendMessage(Core.PREFIX + ChatColor.GREEN + "The demons spawn of arena: " + a.getName() + " has been set!");
			return;
		}
		if(args[2].equals("lobby")) {
			Arena a = cmd.getCore().getArenaManager().getArena(args[1]);
			a.setLobby(p.getLocation());
			FileConfiguration data = cmd.getCore().getConfigManager().getData();
			data.set("arenas." + a.getName() + ".lobby.world", p.getLocation().getWorld().getName());
			data.set("arenas." + a.getName() + ".lobby.x", p.getLocation().getBlockX());
			data.set("arenas." + a.getName() + ".lobby.y", p.getLocation().getBlockY());
			data.set("arenas." + a.getName() + ".lobby.z", p.getLocation().getBlockZ());
			cmd.getCore().getConfigManager().saveData();
			sender.sendMessage(Core.PREFIX + ChatColor.GREEN + "The lobby of arena: " + a.getName() + " has been set!");
			return;
		}
	}

}
