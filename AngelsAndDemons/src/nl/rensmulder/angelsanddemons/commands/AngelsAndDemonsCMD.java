package nl.rensmulder.angelsanddemons.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import nl.rensmulder.angelsanddemons.Core;

public class AngelsAndDemonsCMD implements CommandExecutor {
	
	/* Command list */
	public List<DefaultCommand> commands = new ArrayList<DefaultCommand>();
	
	/* Core instance */
	private Core core;
	
	/* Initializing vars and adding commands to the commands list */
	public AngelsAndDemonsCMD(Core core) {
		this.core = core;
		commands.add(new CreateCMD("create", "Create an arena", "create <name>"));
		commands.add(new SetSpawn("setspawn", "Set the spawn of an arena spawn", "setspawn <arena> <angels/demons/lobby>"));
		commands.add(new JoinCMD("join", "Join an arena", "join <arena>"));
		commands.add(new LeaveCMD("leave", "Leave the arena you're in", "leave"));
		commands.add(new ToggleCMD("toggle", "Enable/Disable an arena", "toggle <arena>"));
	}

	/* The command itself */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/* Checking if the sub-command is nothing */
		if(args.length == 0) {
			//TODO: CONNER MAAK LEUK WELKOM DINGETJE
			return false;
		}
		/* Checking if the sub command is 1 of the commands in the commandlist */
		for(DefaultCommand c : commands) {
			if(args[0].equalsIgnoreCase(c.getName())) {
				c.run(args, sender, this);
				return false;
			}
		} 
		/* Sends a nice help message */
		if(hasPermission(sender)) {
			help(sender);
		} else {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That was not a valid sub-command");
		}
		
		return false;
	}
	
	/* Tool to see if players have a certain permission */
	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("angelsanddemons.admin");
	}
	
	/* Tells all the commands */
	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.AQUA + "               Help command               ");
		sender.sendMessage(ChatColor.AQUA + "=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=");
		for(DefaultCommand c : commands) {
			sender.sendMessage(ChatColor.WHITE + "/angelsanddemons " + c.getUsage() + ChatColor.YELLOW + " - " + ChatColor.AQUA + c.getDescription() + ".");
		}
		sender.sendMessage(ChatColor.AQUA + "=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=");
	}
	
	/* Getters */
	
	public Core getCore() {
		return core;
	}

}
